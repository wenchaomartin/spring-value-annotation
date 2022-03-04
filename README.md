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
这个例子  却不能 converter 成功 而且还报错了

`Failed to convert value of type 'java.lang.String' to required type 'java.util.List'; nested exception is java.lang.NumberFormatException: For input string: "12,22,33"`

我觉得很诧异 


### question

- example1 能成功转化， example2 则不能

- question2 是为啥  要加@PropertySource("classpath:application.properties") 这个注解， example1则会默认读到 application.properties 中属性值



