package com.tickeron.test.web.functional.steps;

import org.jbehave.core.annotations.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

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


    @Given("I make login with user $user and password $password")
    public void login(String username, String password) throws InterruptedException {
        getWebDriver().get(environment.getProperty("server.url"));
        sleep();
        getWebDriver().findElement(By.cssSelector("body > div.cx-marketing-menu.ng-isolate-scope > div.cx-marketing-menu-fixed > div > div > div.col-xs-1 > button")).click();
        sleep();
        getWebDriver().findElement(By.cssSelector("#email")).sendKeys(username);
        getWebDriver().findElement(By.cssSelector("#password")).sendKeys(password);
        getWebDriver().findElement(By.cssSelector("body > div.modal.fade.ng-isolate-scope.cx-marketing-modal-window.in > div > div > div > div.modal-body > form > div.form-group.text-center > div > input")).click();
    }

}
