package com.tickeron.test.web.functional.steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

/**
 * Created by slaviann on 13.09.15.
 */
public class LoginSteps extends  ServiceStepsBasic {

    @When("I make login with user $user and password $password")
    public void login(String username, String password) throws InterruptedException {
        getWebDriver().get(environment.getProperty("server.url"));
        sleepBigTimeout();
        // Login button
        getWebDriver().findElement(By.cssSelector(".cx-marketing-menu-login-button")).click();
        sleepSmallTimeout();
        getWebDriver().findElement(By.cssSelector("#email")).sendKeys(username);
        getWebDriver().findElement(By.cssSelector("#password")).sendKeys(password);
        getWebDriver().findElement(By.cssSelector("input.btn")).click();
    }

    @Then("The login is successful")
    public void CheckLoginIsSuccessfull() {
        sleepBigTimeout();
        // Check welcome div. Must be displayed
        WebElement welcomeDiv = getWebDriver().findElement(By.cssSelector(".jspPane > div:nth-child(1)"));
        assertEquals(welcomeDiv.getText(), "Welcome to Tickeron. Please select one of the options to the right; " +
                "and we will teach you how to use the system and guide you through basic steps.");
    }
}
