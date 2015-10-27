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
    FuncEmbedder funcEmbedder;

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
    }

    @Override
    public void givenStories(GivenStories givenStories) {
    }

    @Override
    /**
     * TODO: refactor
     */
    public void beforeScenario(String title) {
        super.beforeScenario(title);
        System.out.println(String.format("Scenario params: %s", paramsAndVariablesSteps.getTestParamsStorage()));
    }

}
