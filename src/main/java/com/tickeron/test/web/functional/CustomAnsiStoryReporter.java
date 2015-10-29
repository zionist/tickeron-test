package com.tickeron.test.web.functional;

import com.thoughtworks.selenium.webdriven.SeleniumMutator;
import com.tickeron.test.web.functional.steps.ParamsAndVariablesSteps;
import com.tickeron.test.web.functional.steps.SeleniumSteps;
import org.apache.commons.io.FileUtils;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.model.GivenStories;
import org.jbehave.core.model.Story;
import org.jbehave.core.model.StoryDuration;
import org.jbehave.core.reporters.ANSIConsoleOutput;
import org.jbehave.core.reporters.StoryReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private SeleniumSteps seleniumSteps;

    private String scenarioName;

    @Override
    public void failed(String step, Throwable cause) {
        super.failed(step, cause);
        if (environment.getProperty("make.screenshot.onfail", Boolean.class)) {
            File scrFile = ((TakesScreenshot)seleniumSteps.getWebDriver()).getScreenshotAs(OutputType.FILE);
            try {
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date now = new Date();
                String strDate = sdfDate.format(now);
                File screenShotFile = new File(Paths.get(environment.getProperty("screenshot.path"), scenarioName + strDate +  ".png").toAbsolutePath().toString());
                log.debug("Will save screenshot file " + screenShotFile.getAbsolutePath());
                FileUtils.copyFile(scrFile, screenShotFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        funcEmbedder.setFailed();
        if(environment.getProperty("stop.onfail", Boolean.class)) {
            System.exit(1);
            }
    }

    @Override
    public void beforeScenario(String title) {
        //print(format("Scenario:", "{0} {1}\n", title, paramsAndVariablesSteps.getTestParamsStorage().toString()));
        scenarioName = String.format("%s. Params are: %s", title, paramsAndVariablesSteps.getTestParamsStorage().toString());
        print(format("beforeScenario", "{0} {1}\n", "Scenario:", scenarioName));
    }

}
