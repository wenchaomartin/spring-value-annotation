package me.value.exmaple2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanFactoryConfiguration {
    @Bean
    public CustomBeanFactoryModify customBeanFactoryModify() {
        return new CustomBeanFactoryModify();
    }
}
