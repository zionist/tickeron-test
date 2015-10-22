package com.tickeron.test.web.functional.steps.service;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import static org.junit.Assert.*;

/**
 * Created by slaviann on 13.09.15.
 */
public class PortfolioSteps extends ServiceStepsBasic {

    @When("I create basic portfolio $portfolioName using starts page wizard")
    public void basicPortfolio(String portfolioName) {
        clickOnElementByCssSelector("selector");
        // ..
        // ..
        // ..
    }

    @Then("I see Portfolio $portfolioName in portfolio list")
    @Given("I see Portfolio $portfolioName in portfolio list")
    public void checkPortfolioCreated(String portfolioName) {
        portfolioName = paramsAndVariablesSteps.substituteParamsAndVariables(portfolioName);
        // Portfolio tab
        sleepBigTimeout();
        getWebDriver().findElement(By.cssSelector("div.main_menu_item:nth-child(2) > div:nth-child(1) > a:nth-child(1)")).click();
        // Your portfolios
        sleepBigTimeout();
        getWebDriver().findElement(By.cssSelector(".open > ul:nth-child(3) > li:nth-child(2) > a:nth-child(2)")).click();
        // Sort portfolios according date. Click on "Created on" twice
        sleepBigTimeout();
        getWebDriver().findElement(By.cssSelector("div.col-sm-2:nth-child(5) > div:nth-child(1)")).click();
        sleepBigTimeout();
        getWebDriver().findElement(By.cssSelector("div.col-sm-2:nth-child(5) > div:nth-child(1)")).click();
        // Created portfolio must be first menu element. Check it
        sleepBigTimeout();
        // If list is empty return EmptyString. We have no any portfolio
        assertEquals(portfolioName, getWebDriver().findElement(By.cssSelector(".cx-navigation-list-first-item > div:nth-child(3) " +
                "> a:nth-child(1) > span:nth-child(1)")).getText());
    }

    @When("I delete portfolio $portfolioName")
    public void deletePortolio(String portfolioName) {
        portfolioName = paramsAndVariablesSteps.substituteParamsAndVariables(portfolioName);
        // Click trash icon
        getWebDriver().findElement(By.cssSelector(".cx-navigation-list-first-item > div:nth-child(8) > a:nth-child(1)")).click();
        // Do you really want to delete portfolio? -> Yes
        sleepSmallTimeout();
        getWebDriver().findElement(By.cssSelector("button.ng-binding:nth-child(1)")).click();
    }

    @Then("I do not see Portfolio $portfolioName in portfolio list")
    public void checkNoPortfolioExists(String portfolioName) {
        portfolioName = paramsAndVariablesSteps.substituteParamsAndVariables(portfolioName);
        sleepBigTimeout();
        String pagePortfolioName = "";
        try {
            pagePortfolioName = getWebDriver().findElement(By.cssSelector(".cx-navigation-list-first-item > div:nth-child(3) " +
                    "> a:nth-child(1) > span:nth-child(1)")).getText();
        // If list is empty return EmptyString. We have no any portfolio
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }
        assertNotEquals(pagePortfolioName, portfolioName);

    }

}
