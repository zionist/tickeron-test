package com.tickeron.test.web.functional.steps.service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tickeron.test.common.exceptions.AssertionErrorWithContextParamsException;
import com.tickeron.test.common.exceptions.PropertyNotFoundException;
import com.tickeron.test.web.functional.steps.ParamsAndVariablesSteps;
import com.tickeron.test.web.functional.steps.SeleniumSteps;
import org.hamcrest.CoreMatchers;
import org.jbehave.core.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
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
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by slaviann on 11.09.15.
 * Basic class for all service related logic.
 * This class can have child classes for logical units (Login, Portfolio, User Management, Community ... etc)
 */
public class ServiceStepsBasic {

    private static Logger log = LoggerFactory.getLogger(ServiceStepsBasic.class);

    private Optional<String> md5String = Optional.empty();
    private Optional<Actions> actions = Optional.empty();

    @Autowired
    Environment environment;

    @Autowired
    SeleniumSteps seleniumSteps;

    @Autowired
    ParamsAndVariablesSteps paramsAndVariablesSteps;

    protected WebDriver getWebDriver() {
        return seleniumSteps.getWebDriver();
    }

    protected String substituteParamsAndVariables(String input) {
        return paramsAndVariablesSteps.substituteParamsAndVariables(input);
    }

    @When("I wait until service ready")
    public void waitUntilBrowserReady() throws InterruptedException {
        try {
            // Wait until server will show progress bar
            Thread.sleep(environment.getProperty("wait.timeout.small", Integer.class) * 1000);
            WebDriverWait wait = new WebDriverWait(getWebDriver(), environment.getProperty("wait.timeout.big", Integer.class));
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
        catch (java.lang.InterruptedException e) {
            log.error(e.toString());
        }
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
        waitUntilElementIsVisibleByCss(description, selector);
        getWebDriver().findElement(By.cssSelector(selector)).click();
    }

    @When("I type $string into $description with css selector $selector")
    // description only for humans
    public void typeIntoElementByCssSelector(String input, String description, String selector) {
        waitUntilElementIsVisibleByCss(description, selector);
        input = substituteParamsAndVariables(input);
        getWebDriver().findElement(By.cssSelector(selector)).clear();
        getWebDriver().findElement(By.cssSelector(selector)).sendKeys(input);
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
    private void uploadFileToWebElement(WebElement webElement, String fileName) throws InterruptedException {
        webElement.sendKeys(getLocalFile(fileName).getAbsolutePath());
        Thread.sleep(environment.getProperty("sleep.timeout.small", Integer.class) * 1000);
    }


    /**
     * Download via via okhttp client
     * Count new digestString and save it to the class property
     * @param url file url
     */

    private void downloadFile(String url) {
        log.debug(String.format("File download using okhttpclient started"));
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


        Request request = builder.build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            InputStream inputStream = response.body().byteStream();
            md5String = Optional.of(MD5StringFromInputStream(inputStream));
        } catch (IOException e ) {
            fail(String.format("Can't download file %s", url));
            e.printStackTrace();
        }
        log.debug(String.format("File download finished. md5sum of downloaded file is %s", md5String));

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
    }

    @Then("I see $description with css selector $selector is: $text")
    // description only for humans
    public void checkElementTextByCssSelectorIs(String desription, String selector, String text) {
        waitUntilElementIsVisibleByCss(desription, selector);
        waitUntilElementContentDisplayedAndTextIs(By.cssSelector(selector), text);
    }

    @Then("I see $description with css selector $selector is empty")
    // description only for humans
    public void checkElementTextByCssSelectorIsEmpty(String desription, String selector) {
        waitUntilElementIsVisibleByCss(desription, selector);
        waitUntilElementContentDisplayedAndTextIs(By.cssSelector(selector), "");
    }

    @Then("I see $description with css selector $selector contains: $text")
    // description only for humans
    public void checkElementTextByCssSelectorContains(String desription, String selector, String text) {
        waitUntilElementIsVisibleByCss(desription, selector);
        waitUntilElementContentDisplayedAndTextContains(By.cssSelector(selector), text);
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

    /**
     * Wait until text of element will contain content string
     * Then do assert equals check
     * @param by
     * @param content
     */
    private void waitUntilElementContentDisplayedAndTextIs(By by, String content) {
        log.debug(String.format("Waiting for element %s will contains text %s", by.toString(), content));
        WebElement webElement = getWebDriver().findElement(by);
        try {
            new WebDriverWait(getWebDriver(), environment.getProperty("wait.timeout.big", Integer.class))
                    .until(ExpectedConditions.textToBePresentInElement(webElement, content));
        }
        catch (WebDriverException e) {
            //log.warn(String.format("element %s does not contains %s. Can't perform any action with it", by.toString(), content), paramsAndVariablesSteps.getTestParamsStorage());
            try {
                assertEquals(content, getWebDriver().findElement(by).getText());
            } catch (AssertionError a) {
                throw new AssertionErrorWithContextParamsException(a, paramsAndVariablesSteps.getTestParamsStorage());
            }
        }
        log.debug(String.format("Waiting for element %s contains text %s finished successfully", by.toString(), content));
    }

    /**
     * Wait until text of element will contain content string
     * Then do assert contains check
     * @param by
     * @param content
     */
    private void waitUntilElementContentDisplayedAndTextContains(By by, String content) {
        log.debug(String.format("Waiting for element %s will contains text %s", by.toString(), content));
        WebElement webElement = getWebDriver().findElement(by);
        try {
            new WebDriverWait(getWebDriver(), environment.getProperty("wait.timeout.big", Integer.class))
                    .until(ExpectedConditions.textToBePresentInElement(webElement, content));
        }
        catch (WebDriverException e) {
            //log.warn(String.format("element %s does not contains %s. Can't perform any action with it", by.toString(), content), paramsAndVariablesSteps.getTestParamsStorage());
            try {
                assertThat(getWebDriver().findElement(by).getText(), CoreMatchers.containsString(content));
            } catch (AssertionError a) {
                throw new AssertionErrorWithContextParamsException(a, paramsAndVariablesSteps.getTestParamsStorage());
            }
        }
        log.debug(String.format("Waiting for element %s contains text %s finished successfully", by.toString(), content));

    }

    private void waitUntilElementIsVisible(By by) {
        log.debug(String.format("Waiting for element %s will be visible", by.toString()));
        try {
            new WebDriverWait(getWebDriver(), environment.getProperty("wait.timeout.big", Integer.class))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        }
        catch (WebDriverException e) {
            throw new AssertionErrorWithContextParamsException(String.format("element with css %s is not visible. Can't perform any action with it", by.toString()), paramsAndVariablesSteps.getTestParamsStorage());
        }
        log.debug(String.format("Waiting for element %s finished successfully", by.toString()));

    }

    @When("I will wait until $description element with css selector $selector will be visible")
    public void waitUntilElementIsVisibleByCss(String description, String selector) {
        waitUntilElementIsVisible(By.cssSelector(selector));
    }


}
