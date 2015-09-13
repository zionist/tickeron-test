package com.tickeron.test.web.functional.steps;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
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

    @Autowired
    CommonSteps commonSteps;

    @Given("I will use FireFoxWebDriver")
    public void setFireFoxWebDriver() {
        webDriver = new FirefoxDriver();
        webDriver.manage().deleteAllCookies();
    }

    @Given("I will use ChromWebDriver")
    public void setChromWebDriver() {
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

    @Given("Browser ready")
    public void setUpWebDriver() {
        //String browser = commonSteps.getTestParamsStorage().get("browser");
        String browser = environment.getProperty("default.browser");
        if (browser.equals("Firefox")) setFireFoxWebDriver();
        else if (browser.equals("Chrome")) setChromWebDriver();
        // TODO: create exceptions
        else throw new RuntimeException(String.format("Wrong browser name - %s", browser));
    }

    @Then("Browser is stopped")
    public void stopDriver() {
        webDriver.close();
    }

    @AfterScenario
    public void tearDown() {
        if (webDriver != null) webDriver.quit();
    }


}
