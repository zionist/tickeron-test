package com.tickeron.test.web.functional.steps.service;

import com.tickeron.test.common.exceptions.AssertionErrorWithContextParamsException;
import com.tickeron.test.common.exceptions.PropertyNotFoundException;
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
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    //@Given("I wait big timeout")
    @When("I wait big timeout")
    public void sleepBigTimeout() {
        try {
            Thread.sleep(environment.getProperty("sleep.timeout.big", Integer.class) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    //@Given("I wait small timeout")
    @When("I wait small timeout")
    public void sleepSmallTimeout() {
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
        getWebDriver().findElement(By.cssSelector(selector)).click();
    }

    @When("I click element with link text $linkText")
    public void clickOnElementByLinkText(String linkText) {
        getWebDriver().findElement(By.linkText(linkText)).click();
    }

    @When("I type $string into $description with css selector $selector")
    // description only for humans
    public void typeIntoElementByCssSelector(String input, String description, String selector) {
        typeIntoElementByCssSelector(input, selector);

    }

    /*
    Uploads file from classpath via selenium
    Must be used for all files upload
     */
    public void uploadFileToWebElement(WebElement webElement, String fileName) {
        String imageDirString = environment.getProperty("image.path", "");
        if (imageDirString.isEmpty()) {
            throw new PropertyNotFoundException("image.path");
        }
        File imageDir = null;
        try {
            imageDir = new ClassPathResource(imageDirString).getFile();
            if (!imageDir.isDirectory()) fail(String.format("Image %s is not a directory", imageDirString));
        } catch (IOException e) {
            fail(String.format("Please check directory %s exists", imageDirString));
        }
        File imageFile  = new File(Paths.get(imageDir.getAbsolutePath(), fileName).toString());
        if(!imageFile.exists()) {
            fail(String.format("Please check file %s exists", imageFile.getAbsolutePath()));
        }
        webElement.sendKeys(imageFile.getAbsolutePath());
    }

    @When("I will upload file $fileName using input element with css selector $selector")
    public void uploadFileFromPathUsingCssSelector(String fileName, String selector) {
        uploadFileToWebElement(getWebDriver().findElement(By.cssSelector(selector)), fileName);
    }

    @When("I will upload file $fileName using input element with xpath $xpath")
    public void uploadFileFromPathUsingLinkText(String fileName, String xpath) {
        uploadFileToWebElement(getWebDriver().findElement(By.xpath(xpath)), fileName);
    }

    @When("I type $string into element with css selector $selector")
    public void typeIntoElementByCssSelector(String input, String selector) {
        input = substituteParamsAndVariables(input);
        getWebDriver().findElement(By.cssSelector(selector)).sendKeys(input);
    }

    @Then("I see $description with css selector $selector is: $text")
    // description only for humans
    public void checkElementContainsText(String desription, String selector, String text) {
        checkElementContainsText(selector, text);
    }

    @Then("I see element with css selector $selector contains: $text")
    public void checkElementContainsText(String selector, String text) {
        WebElement element = getWebDriver().findElement(By.cssSelector(selector));
        try {
            assertEquals(element.getText(), text);
        } catch (AssertionError e) {
            throw new AssertionErrorWithContextParamsException(e, paramsAndVariablesSteps.getTestParamsStorage());
        }
    }
}
