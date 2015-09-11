package com.tickeron.test.web.functional.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * Created by slaviann on 11.09.15.
 * We need switch different WebDrivers in runtime
 */
public class SeleniumSteps {

    public WebDriver webDriver;

    @Autowired
    Environment environment;

    @Given("I will use FireFoxWebDriver")
    public void setFireFoxWebDriver() {
        webDriver = new FirefoxDriver();
        webDriver.manage().deleteAllCookies();
    }

    @Given("I will use ChromWebDriver")
    public void setChromWebDriver() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", environment.getProperty("chrome.driver"));
        webDriver = new ChromeDriver();
        webDriver.manage().deleteAllCookies();
    }

    public void sleep() {
        try {
            Thread.sleep(environment.getProperty("sleep.timeout", Integer.class) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Then("WebDriver is stopped")
    public void stopDriver() {
        webDriver.close();
    }
}
