package com.tickeron.test.web.functional.steps.service;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tickeron.test.common.exceptions.AssertionErrorWithContextParamsException;
import com.tickeron.test.common.exceptions.Formatter;
import com.tickeron.test.common.exceptions.PropertyNotFoundException;
import com.tickeron.test.web.functional.steps.ParamsAndVariablesSteps;
import com.tickeron.test.web.functional.steps.SeleniumSteps;
import org.hamcrest.CoreMatchers;
import org.jbehave.core.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.*;

import java.io.*;
import java.math.BigInteger;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by slaviann on 11.09.15.
 * Basic class for all service related logic.
 * This class can have child classes for logical units (Login, Portfolio, User Management, Community ... etc)
 */
public class ServiceStepsBasic {

    private Optional<String> md5String = Optional.empty();
    private Optional<Actions> actions = Optional.empty();

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

    @When("I wait until service ready")
    public void waitUntilBrowserReady() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), environment.getProperty("wait.timeout.big", Integer.class));
        Thread.sleep(environment.getProperty("wait.timeout.small", Integer.class) * 1000);
        wait.until(
                new ExpectedCondition<Boolean>() {
                    String currentValue = "";

                    @Override
                    public Boolean apply(WebDriver driver) {
                        currentValue = driver.findElement(By.cssSelector("html")).getAttribute("class");
                        return !currentValue.contains("nprogress-busy");
                    }

                    @Override
                    public String toString() {
                        return String.format("attribute %s to be \"%s\". Current attribute values : \"%s\"", "class", "nprogress-busy", currentValue);
                }
            }


        );
    }


    @Given("I wait big timeout")
    @When("I wait big timeout")
    public void sleepBigTimeout() {
        try {
            Thread.sleep(environment.getProperty("sleep.timeout.big", Integer.class) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Given("I wait small timeout")
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

    }

    @When("I click on $description with css selector $selector")
    // description only for humans
    public void clickOnElementByCssSelector(String description, String selector) {
        clickOnElementByCssSelector(selector);
    }

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

    private File getLocalFile(String fileName) {
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
        return imageFile;
    }


    /**
     * Uploads file from classpath via selenium
     * Must be used for all files upload
     * @param webElement
     * @param fileName
     */
    public void uploadFileToWebElement(WebElement webElement, String fileName) throws InterruptedException {
        webElement.sendKeys(getLocalFile(fileName).getAbsolutePath());
        Thread.sleep(environment.getProperty("sleep.timeout.small", Integer.class) * 1000);
    }


    /**
     * Download via via okhttp client
     * Count new digestString and save it to the class property
     * @param url file url
     */

    public void downloadFile(String url) {
        md5String = Optional.empty();

        OkHttpClient okHttpClient = new OkHttpClient();
        CookieManager cookieManager = new CookieManager();

        // Let's accept all cookies
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        okHttpClient.setCookieHandler(cookieManager);

        Request.Builder builder = new Request.Builder();
        builder = builder.url(url);

        // Copy all cookies from browser.
        for(org.openqa.selenium.Cookie cookie : getWebDriver().manage().getCookies()) {
            builder = builder.addHeader("Cookie", String.format("%s=%s", cookie.getName(), cookie.getValue()));
        }
        // Set user agent same as in browser
        //builder.removeHeader("User-Agent");
        //builder.addHeader("User-Agent", environment.getProperty("user.agent"));
        //builder = builder.header("test", "testValue");
        Request request = builder.build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            InputStream inputStream = response.body().byteStream();
            md5String = Optional.of(MD5StringFromInputStream(inputStream));
        } catch (IOException e ) {
            fail(String.format("Can't download file %s", url));
            e.printStackTrace();
        }

    }

    /**
     * Counts MD5 human readable string from InputStream object
     * @param inputStream
     * @return human readable MD5 string
     */
    private String MD5StringFromInputStream(InputStream inputStream) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            byte[] dataBytes = new byte[1024];
            int read;
            while ((read = inputStream.read(dataBytes)) != -1) {
                messageDigest.update(dataBytes, 0, read);

            }
        } catch (NoSuchAlgorithmException | IOException e) {
            fail("Can't read from input stream");
            e.printStackTrace();
            return "";
        }
        return new BigInteger(1, messageDigest.digest()).toString(16);
    }


    @Then("Downloaded file is $fileName")
    public void compareDownloadedFileChecksumWithLocalFile(String fileName) throws IOException, NoSuchAlgorithmException {
        InputStream inputStream = new FileInputStream(getLocalFile(fileName).getAbsolutePath());
        assertEquals(String.format("Downloaded file is not %s. Please see md5 checksum", fileName),
                 MD5StringFromInputStream(inputStream), md5String.get());
        md5String = Optional.empty();

    }


    @When("I download $description file from <a> element with css selector $selector")
    public void downloadFileFromAElementWithCssSelector(String description, String selector) {
        WebElement element = getWebDriver().findElement(By.cssSelector(selector));
        downloadFile(element.getAttribute("href"));
    }

    @When("I will upload file $fileName using input element with css selector $selector")
    public void uploadFileFromPathUsingCssSelector(String fileName, String selector) throws InterruptedException {
        uploadFileToWebElement(getWebDriver().findElement(By.cssSelector(selector)), fileName);
    }

    @When("I upload file $fileName using input element with xpath $xpath")
    public void uploadFileFromPathUsingLinkText(String fileName, String xpath) throws InterruptedException {
        uploadFileToWebElement(getWebDriver().findElement(By.xpath(xpath)), fileName);
        Thread.sleep(environment.getProperty("wait.timeout.small", Integer.class));
    }

    @When("I type $string into element with css selector $selector")
    public void typeIntoElementByCssSelector(String input, String selector) {
        input = substituteParamsAndVariables(input);
        getWebDriver().findElement(By.cssSelector(selector)).clear();
        getWebDriver().findElement(By.cssSelector(selector)).sendKeys(input);
    }

    @Then("I see $description with css selector $selector is: $text")
    // description only for humans
    public void checkElementTextByCssSelectorIs(String desription, String selector, String text) {
        WebElement element = getWebDriver().findElement(By.cssSelector(selector));
        try {
            assertEquals(text, element.getText());
        } catch (AssertionError e) {
            throw new AssertionErrorWithContextParamsException(e, paramsAndVariablesSteps.getTestParamsStorage());
        }
    }

    @Then("I see $description with css selector $selector contains: $text")
    // description only for humans
    public void checkElementTextBySccSelectorContains(String desription, String selector, String text) {
        WebElement element = getWebDriver().findElement(By.cssSelector(selector));
        try {
            assertThat(element.getText(), CoreMatchers.containsString(text));
        } catch (AssertionError e) {
            throw new AssertionErrorWithContextParamsException(e, paramsAndVariablesSteps.getTestParamsStorage());
        }
    }

    @When("I start recording action")
    public void startActionRecord() throws InterruptedException {
        actions = Optional.of(new Actions(getWebDriver()));

    }
    @When("I will move in action to $description element with css selector $selector")
    public void ActionsMoveToElemetByCssSelector(String description, String selector) {
        actions.ifPresent(a -> {
            a.moveToElement(getWebDriver().findElement(By.cssSelector(selector)));
        });
    }

    @When("I will click in action on $description element with css selector $selector")
    public void ActionsClickOnElementByCssSelector(String description, String selector) {
        actions.ifPresent(a -> {
            a.click(getWebDriver().findElement(By.cssSelector(selector)));
        });
    }

    @Then("I perform previously recorded action")
    public void performAction() {
        actions.ifPresent(a -> {
            a.perform();
        });
    }

    @When("I will wait until $description element with css selector $selector will be visible")
    public void WaitUntilElementIsVisibleByCss(String description, String selector) {
        new WebDriverWait(getWebDriver(), environment.getProperty("wait.timeout.big", Integer.class))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
    }

}
