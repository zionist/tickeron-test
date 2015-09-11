package com.tickeron.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

/**
 * Created by slaviann on 11.09.15.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    @Scope("prototype")
    public String testBean() {
        return "TestBeanCreated" + env.getProperty("testprop");
    }

}
