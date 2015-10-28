package com.tickeron.test.web.functional;

import com.tickeron.test.web.functional.steps.ParamsAndVariablesSteps;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.model.GivenStories;
import org.jbehave.core.model.Story;
import org.jbehave.core.model.StoryDuration;
import org.jbehave.core.reporters.ANSIConsoleOutput;
import org.jbehave.core.reporters.StoryReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * Created by slaviann on 23.10.15.
 */
public class CustomAnsiStoryReporter extends ANSIConsoleOutput {
    private static Logger log = LoggerFactory.getLogger(CustomAnsiStoryReporter.class);

    @Autowired
    FuncEmbedder funcEmbedder;

    @Autowired
    Environment environment;

    @Autowired
    private ParamsAndVariablesSteps paramsAndVariablesSteps;

    @Override
    /**
     * TODO: refactor
     */
    public void failed(String step, Throwable cause) {
        super.failed(step, cause);
        funcEmbedder.setFailed();
        if(environment.getProperty("stop.onfail", Boolean.class)) {
            System.exit(1);
            }
    }

    @Override
    public void beforeScenario(String title) {
        //print(format("Scenario:", "{0} {1}\n", title, paramsAndVariablesSteps.getTestParamsStorage().toString()));
        print(format("beforeScenario", "{0} {1}\n", "Scenario:", String.format("%s. Params are: %s", title, paramsAndVariablesSteps.getTestParamsStorage().toString())));
    }

}
