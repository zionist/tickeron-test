package com.tickeron.test.config;

import com.tickeron.test.common.exceptions.AssertionErrorWithContextParamsException;
import com.tickeron.test.web.functional.CustomEmbedderMonitor;
import com.tickeron.test.web.functional.CustomStoryReporter;
import com.tickeron.test.web.functional.FuncEmbedder;
import com.tickeron.test.web.functional.steps.*;
import com.tickeron.test.web.functional.steps.service.PortfolioSteps;
import com.tickeron.test.web.functional.steps.service.ServiceStepsBasic;
import org.jbehave.core.embedder.NullEmbedderMonitor;
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
    PortfolioSteps portfolioSteps() {
        return new PortfolioSteps();
    }

    @Bean
    public SeleniumSteps seleniumSteps() {
        return new SeleniumSteps();
    }

    @Bean
    public FuncEmbedder funcEmbedder() {
        return new FuncEmbedder(new CustomEmbedderMonitor());
    }

    @Bean
    public ServiceStepsBasic serviceStepsBasic() {
        return new ServiceStepsBasic();
    }

    @Bean
    public ParamsAndVariablesSteps commonSteps() {
        return new ParamsAndVariablesSteps();
    }

    @Bean
    public CustomStoryReporter customStoryReporter() { return new CustomStoryReporter(); }


}
