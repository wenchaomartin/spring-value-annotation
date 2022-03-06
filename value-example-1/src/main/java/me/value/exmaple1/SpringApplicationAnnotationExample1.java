package me.value.exmaple1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author songwenchao
 */
@SpringBootApplication
public class SpringApplicationAnnotationExample1 extends SpringBootServletInitializer {

    @Value("${friend.ages}")
    List<Long> friendAges;

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringApplicationAnnotationExample1.class, args);
        SpringApplicationAnnotationExample1 valueExample1 = applicationContext.getBean(SpringApplicationAnnotationExample1.class);
        System.out.println(valueExample1.friendAges);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.out.println(friendAges);
        return application.sources(SpringApplicationAnnotationExample1.class);
    }


    @org.springframework.stereotype.Controller
    public class Controller {
        @RequestMapping(value = "/")
        @ResponseBody
        public List<Long> demo() {
            return friendAges;
        }
    }


}
