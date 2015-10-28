package com.tickeron.test.web.functional;

import com.tickeron.test.web.functional.steps.ParamsAndVariablesSteps;
import org.jbehave.core.model.GivenStories;
import org.jbehave.core.reporters.NullStoryReporter;
import org.jbehave.core.reporters.StoryReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import static org.junit.Assert.*;

/**
 * Created by slaviann on 23.10.15.
 */
public class CustomStoryReporter  extends NullStoryReporter implements StoryReporter {
    private static Logger log = LoggerFactory.getLogger(CustomStoryReporter.class);

    @Autowired
    FuncEmbedder funcEmbedder;

    @Autowired
    Environment environment;

    @Autowired
    private ParamsAndVariablesSteps paramsAndVariablesSteps;

    @Override
    public void successful(String step) {
    }

    @Override
    /**
     * TODO: refactor
     */
    public void failed(String step, Throwable cause) {
        funcEmbedder.setFailed();
        if(environment.getProperty("stop.onfail", Boolean.class)) {
            log.warn("Option stop.onfail set. Will exit from test");
            //fail("Option stop.onfail set. Will exit from test");
            System.exit(1);}
    }

    @Override
    public void givenStories(GivenStories givenStories) {
    }

    @Override
    /**
     * TODO: refactor. Make work on Windows
     */
    public void beforeScenario(String title) {
        super.beforeScenario(title);
        log.info(String.format("Scenario params: %s", paramsAndVariablesSteps.getTestParamsStorage()));
        //System.out.println(String.format("Scenario params: %s", paramsAndVariablesSteps.getTestParamsStorage()));
    }

}
