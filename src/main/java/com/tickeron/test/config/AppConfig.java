package com.tickeron.test.config;

import com.tickeron.test.web.functional.FuncEmbedder;
import com.tickeron.test.web.functional.steps.*;
import com.tickeron.test.web.functional.steps.service.LoginSteps;
import com.tickeron.test.web.functional.steps.service.PortfolioSteps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by slaviann on 11.09.15.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Autowired
    Environment env;

    //@Bean
    //public ServiceStepsBasic serviceSteps() {
    //    return new ServiceStepsBasic();
    //}

    @Bean
    public LoginSteps loginSteps() {
        return new LoginSteps();
    }

    @Bean
    PortfolioSteps portfolioSteps() {
        return new PortfolioSteps();
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
