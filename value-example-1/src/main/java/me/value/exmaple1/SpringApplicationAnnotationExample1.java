package me.value.exmaple1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * @author songwenchao
 */
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
