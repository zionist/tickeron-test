package com.tickeron.test.web.functional.steps;

import org.jbehave.core.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import static org.junit.Assert.*;

/**
 * Created by slaviann on 11.09.15.
 * Class for all service related logic
 */
public class ServiceSteps {

    @Autowired
    Environment environment;

    @Autowired
    SeleniumSteps seleniumSteps;

    public WebDriver getWebDriver() {
        return seleniumSteps.webDriver;
    }

    public void sleep() {
        seleniumSteps.sleep();
    }

    @When("I make login with user $user and password $password")
    @Alias("I make login with username <username> and password <password>")
    public void login(@Named("username") String username, @Named("password") String password) throws InterruptedException {
        getWebDriver().get(environment.getProperty("server.url"));
        sleep();
        getWebDriver().findElement(By.cssSelector("body > div.cx-marketing-menu.ng-isolate-scope > div.cx-marketing-menu-fixed > div > div > div.col-xs-1 > button")).click();
        sleep();
        getWebDriver().findElement(By.cssSelector("#email")).sendKeys(username);
        getWebDriver().findElement(By.cssSelector("#password")).sendKeys(password);
        getWebDriver().findElement(By.cssSelector("body > div.modal.fade.ng-isolate-scope.cx-marketing-modal-window.in > div > div > div > div.modal-body > form > div.form-group.text-center > div > input")).click();
    }

    @Then("The login is successful")
    public void CheckLoginIsSuccessfull() {
        sleep();
        WebElement welcomeDiv = getWebDriver().findElement(By.cssSelector("body > div.base > div > div > " +
                 "div > div > div > div > div > div > div.col-sm-4 > div:nth-child(2) " +
                 "> div > div > div > div > div.cx-scroll-panel-scroller.cx-basic-starts-answered-question-container__scroll-panel " +
                 "> div > div > div"));
        assertEquals(welcomeDiv.getText(), "Welcome to Tickeron. Please select one of the options to the right; " +
                "and we will teach you how to use the system and guide you through basic steps.");
    }

}
