package com.tickeron.test.web.functional.steps;

import com.tickeron.test.web.functional.steps.service.ServiceStepsBasic;
import org.jbehave.core.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Created by slaviann on 11.09.15.
 */
public class SeleniumSteps {

    private static Logger log = LoggerFactory.getLogger(SeleniumSteps.class);
    public Optional<WebDriver> webDriver = Optional.empty();

    @Autowired
    private Environment environment;

    @Autowired
    private ParamsAndVariablesSteps paramsAndVariablesSteps;

    private void resetBrowser() {
        webDriver.ifPresent(d -> d.manage().deleteAllCookies());
    }


    private void setFireFoxWebDriver() {
      webDriver = Optional.of(new FirefoxDriver());
    }

    public Optional<Object> executeJs(String jsString) {
        webDriver.orElseThrow(() -> new RuntimeException("Now web driver configured"));
        if (webDriver.get() instanceof JavascriptExecutor) {
            JavascriptExecutor js;
            js = (JavascriptExecutor) webDriver.get();
            //js.executeScript("return document.getElementById('someId');");
            return Optional.ofNullable(js.executeScript(jsString));
        }
        return Optional.empty();
    }

    private void setChromWebDriver() {
        System.setProperty("webdriver.chrome.driver", environment.getProperty("chrome.driver"));
        webDriver = Optional.of(new ChromeDriver());

    }


    @Given("Browser ready")
    public void setUpWebDriver() throws InterruptedException {
        //String browser = paramsAndVariablesSteps.getTestParamsStorage().get("browser");

        // Do not create new browser use old one. Just reset cookies
        if (webDriver.isPresent()) {
            resetBrowser();
            getWebDriver().get(environment.getProperty("server.url"));
            Thread.sleep(5 * 1000);
            return;
        }

        String browser = environment.getProperty("browser");
        if (browser.equals("Firefox")) setFireFoxWebDriver();
        else if (browser.equals("Chrome")) setChromWebDriver();
        else throw new RuntimeException(String.format("Wrong browser name - %s", browser));
        getWebDriver().get(environment.getProperty("server.url"));
        Thread.sleep(5 * 1000);
        getWebDriver().manage().window().maximize();
    }


    public WebDriver getWebDriver() {
        return webDriver.orElseThrow(() -> new RuntimeException("There is no WebDriver"));
    }

    @AfterStory
    // Close browser if close.browser.after.story is true
    public void AfterStory() {
        if(webDriver.isPresent() && environment.getProperty("close.browser.after.story", Boolean.class)) {
            webDriver.get().quit();
            webDriver = Optional.empty();
        }
    }



}
