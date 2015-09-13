package com.tickeron.test.web.functional.steps.service;

import com.tickeron.test.web.functional.steps.ParamsAndVariablesSteps;
import com.tickeron.test.web.functional.steps.SeleniumSteps;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Created by slaviann on 11.09.15.
 * Basic class for all service related logic.
 * This class can have child classes for logical units (Login, Portfolio, User Management, Community ... etc)
 */
public class ServiceStepsBasic {

    @Autowired
    Environment environment;

    @Autowired
    SeleniumSteps seleniumSteps;

    @Autowired
    ParamsAndVariablesSteps paramsAndVariablesSteps;

    public WebDriver getWebDriver() {
        return seleniumSteps.getWebDriver();
    }

    protected void sleepBigTimeout() {
        try {
            Thread.sleep(environment.getProperty("sleep.timeout.big", Integer.class) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    protected void sleepSmallTimeout() {
        try {
            Thread.sleep(environment.getProperty("sleep.timeout.small", Integer.class) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }




}
