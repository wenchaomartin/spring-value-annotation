package me.value.exmaple2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @author songwenchao
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class SpringApplicationAnnotationExample2 {

    @Value("${friend.ages}")
    private List<Long> friendAges;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CustomBeanFactoryConfiguration.class);
        context.register(SpringApplicationAnnotationExample2.class);
        context.refresh();
        SpringApplicationAnnotationExample2 valueExample1 = context.getBean(SpringApplicationAnnotationExample2.class);
        System.out.println(valueExample1.friendAges);

    }


}
