package com.tickeron.test.web.functional;

import org.jbehave.core.model.GivenStories;
import org.jbehave.core.reporters.NullStoryReporter;
import org.jbehave.core.reporters.StoryReporter;

/**
 * Created by slaviann on 23.10.15.
 */
public class CustomStoryReporter  extends NullStoryReporter implements StoryReporter {

    @Override
    public void successful(String step) {
    }

    @Override
    public void failed(String step, Throwable cause) {
    }

    @Override
    public void givenStories(GivenStories givenStories) {
    }

}
