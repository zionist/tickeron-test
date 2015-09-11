package com.tickeron.test.web.functional.steps;

import org.jbehave.core.annotations.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;

/**
 * Created by slaviann on 11.09.15.
 * We need switch different WebDrivers in runtime
 */
public class SeleniumSteps {

    public WebDriver webDriver;

    @Given("Will use FireFoxWebDriver")
    public void setFireFoxWebDriver() {
        this.webDriver = new FirefoxDriver();
    }

    @Given("Will use ChromWebDriver")
    public void setChromWebDriver() {
        this.webDriver = new ChromeDriver();
    }
}
