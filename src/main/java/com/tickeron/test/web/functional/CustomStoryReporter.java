package com.tickeron.test.web.functional;

import com.tickeron.test.web.functional.steps.ParamsAndVariablesSteps;
import org.jbehave.core.model.GivenStories;
import org.jbehave.core.reporters.NullStoryReporter;
import org.jbehave.core.reporters.StoryReporter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by slaviann on 23.10.15.
 */
public class CustomStoryReporter  extends NullStoryReporter implements StoryReporter {

    @Autowired
    private ParamsAndVariablesSteps paramsAndVariablesSteps;

    @Override
    public void successful(String step) {
    }

    @Override
    public void failed(String step, Throwable cause) {
    }

    @Override
    public void givenStories(GivenStories givenStories) {
    }

    @Override
    public void beforeScenario(String title) {
        super.beforeScenario(title);
        System.out.println(String.format("Scenario params: %s", paramsAndVariablesSteps.getTestParamsStorage()));
    }

}
