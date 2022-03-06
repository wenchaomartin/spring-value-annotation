# @Value 相关的问题

## 有两个关于@Value 的注解的例子， 对 @Value 不是很理解

### example1

```java

@SpringBootApplication
public class SpringApplicationAnnotationExample1 {

    @Value("${friend.ages}")
    private List<Long> friendAges;

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringApplicationAnnotationExample1.class, args);
        SpringApplicationAnnotationExample1 valueExample1 = applicationContext.getBean(SpringApplicationAnnotationExample1.class);
        System.out.println(valueExample1.friendAges);

    }

}
```

这个例子 能将 String 类型的 friend.ages="12,22,33" converter List<Long> 的 [12,22,33]

### example2

```java

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class SpringApplicationAnnotationExample2 {

    @Value("${friend.ages}")
    private List<Long> friendAges;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringApplicationAnnotationExample2.class);
        context.refresh();
        SpringApplicationAnnotationExample2 valueExample1 = context.getBean(SpringApplicationAnnotationExample2.class);
        System.out.println(valueExample1.friendAges);

    }

}
```

这个例子 却不能 converter 成功 而且还报错了

`Failed to convert value of type 'java.lang.String' to required type 'java.util.List'; nested exception is java.lang.NumberFormatException: For input string: "12,22,33"`

我觉得很诧异

### question

- example1 能成功转化， example2 则不能

- question2 是为啥 要加@PropertySource("classpath:application.properties") 这个注解， example1则会默认读到 application.properties 中属性值

### 回答 问题

- example1能转换成功是 example2不能

    - example1的应用上下文 ConfigurableListableBeanFactory example2 则是 AnnotationConfigApplicationContext
    - 核心点是因为 ConfigurableListableBeanFactory 有这样的一个方法  
      context.getBeanFactory().setConversionService(context.getEnvironment().getConversionService());
      [代码演示](imgs/1646580908.png)

    - 给example2注册ApplicationConversionService 我用的BeanPostProcessor实现

```java
  public class CustomBeanFactoryModify implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.setConversionService(new ApplicationConversionService());
    }
}
```

### 问题分析

我对比了两个应用上线文 

[这样代码返回了有差异](imgs/根源的错误.png)


