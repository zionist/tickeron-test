package com.tickeron.test.config;

import com.tickeron.test.web.functional.FuncEmbedder;
import com.tickeron.test.web.functional.steps.CommonSteps;
import com.tickeron.test.web.functional.steps.SeleniumSteps;
import com.tickeron.test.web.functional.steps.ServiceSteps;
import org.openqa.selenium.WebDriver;
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
    public ServiceSteps serviceSteps() {
        return new ServiceSteps();
    }

    @Bean
    public SeleniumSteps seleniumSteps() {
        return new SeleniumSteps();
    }

    @Bean
    public FuncEmbedder funcEmbedder() {
        return new FuncEmbedder();
    }

    @Bean
    public CommonSteps commonSteps() {
        return new CommonSteps();
    }

}
