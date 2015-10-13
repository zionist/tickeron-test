package com.tickeron.test.web.functional.steps.service;

import com.tickeron.test.web.functional.steps.ParamsAndVariablesSteps;
import com.tickeron.test.web.functional.steps.SeleniumSteps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import static org.junit.Assert.*;

import java.util.Optional;

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

    protected String substituteParamsAndVariables(String input) {
        return paramsAndVariablesSteps.substituteParamsAndVariables(input);
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

    @Given("I wait for $milliseconds milliseconds")
    @Then("I wait for $milliseconds milliseconds")
    public void Wait(Integer milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    @Given("Do nothing")
    @Then("Do nothing")
    public void doNothing() {
        //System.out.println("# Do nothing");
    }

    @When("I click on $description with css selector $selector")
    // description only for humans
    public void clickOnElementByCssSelector(String description, String selector) {
        clickOnElementByCssSelector(selector);
    }

    @When("I click element with css selector $selector")
    public void clickOnElementByCssSelector(String selector) {
        sleepSmallTimeout();
        getWebDriver().findElement(By.cssSelector(selector)).click();
    }

    @When("I type $string into $description with css selector $selector")
    // description only for humans
    public void typeIntoElementByCssSelector(String input, String description, String selector) {
        typeIntoElementByCssSelector(input, selector);

    }

    @When("I type $string into element with css selector $selector")
    public void typeIntoElementByCssSelector(String input, String selector) {
        sleepBigTimeout();
        input = substituteParamsAndVariables(input);
        getWebDriver().findElement(By.cssSelector(selector)).sendKeys(input);
    }

    @Then("I see $description with css selector $selector contains: $text")
    // description only for humans
    public void checkElementContainsText(String desription, String selector, String text) {
        checkElementContainsText(selector, text);
    }

    @Then("I see element with css selector $selector contains: $text")
    public void checkElementContainsText(String selector, String text) {
        sleepBigTimeout();
        WebElement element = getWebDriver().findElement(By.cssSelector(selector));
        assertEquals(element.getText(), text);

    }
}
