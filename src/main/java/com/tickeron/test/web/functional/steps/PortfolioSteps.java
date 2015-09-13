package com.tickeron.test.web.functional.steps;

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
    public void basicPortfolioCreateUsingStartsPageWizard(String portfolioName) {
        sleepBigTimeout();
        // What would you like to do today? -> Create New Portfolio
        getWebDriver().findElement(By.id("answercreate")).click();
        sleepSmallTimeout();
        // Please enter a title for your portfolio -> $portfolioName
        getWebDriver().findElement(By.id("prtName")).sendKeys(portfolioName);
        sleepSmallTimeout();
        getWebDriver().findElement(By.cssSelector("a.btn")).click();
        sleepSmallTimeout();
        // What is your risk tolerance level? -> moderate
        getWebDriver().findElement(By.id("answermoderate")).click();
        sleepSmallTimeout();
        // How soon do you plan to withdraw funds? -> 5 - 15 years
        getWebDriver().findElement(By.id("answer5_15")).click();
        sleepSmallTimeout();
        // Outside of your portfolio, do you have cash reserves for the next twelve months? -> ues
        getWebDriver().findElement(By.id("answeryes")).click();
        sleepSmallTimeout();
        // What is the market value of your portfolio? -> 0-25 k$
        getWebDriver().findElement(By.id("answer0_25K")).click();
        sleepSmallTimeout();
        // Do you have a brokerage account for this portfolio? -> No
        getWebDriver().findElement(By.id("answerno")).click();
        sleepSmallTimeout();
        // Do you plan to add money to your portfolio? -> No
        getWebDriver().findElement(By.id("answerno")).click();
        sleepSmallTimeout();
        // How often do you plan to reallocate your portfolio? ->  Quarterly or less frequently
        getWebDriver().findElement(By.id("answerquarterly_less")).click();
        sleepSmallTimeout();
        // Your Tickeron allocation strategy is -> Ok
        getWebDriver().findElement(By.cssSelector("a.btn")).click();
        // Do you know the tickers or names of the positions in your portfolio? -> No
        sleepSmallTimeout();
        getWebDriver().findElement(By.id("answerno")).click();
        // Do you need help finding the tickers or names of the positions for your portfolio? -> No
        sleepSmallTimeout();
        getWebDriver().findElement(By.id("answerno")).click();
    }

    private String findFirstPortfolioInPortfoliosList() {
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
        // If list is empty return EmptyString
        try {
            return getWebDriver().findElement(By.cssSelector(".cx-navigation-list-first-item > div:nth-child(3) " +
                    "> a:nth-child(1) > span:nth-child(1)")).getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return "";
        }
    }

    @Then("I see Portfolio $portfolioName in portfolio list")
    @Given("I see Portfolio $portfolioName in portfolio list")
    public void checkPortfolioCreated(String portfolioName) {
        assertEquals(portfolioName, findFirstPortfolioInPortfoliosList());
    }

    @When("I delete portfolio $portfolioName")
    public void deletePortolio(String portfolioName) {
        // Click trash icon
        getWebDriver().findElement(By.cssSelector(".cx-navigation-list-first-item > div:nth-child(8) > a:nth-child(1)")).click();
        // Do you really want to delete portfolio? -> Yes
        sleepSmallTimeout();
        getWebDriver().findElement(By.cssSelector("button.ng-binding:nth-child(1)")).click();
    }

    @Then("I do not see Portfolio $portfolioName in portfolio list")
    public void checkNoPortfolioExists(String portfolioName) {
        assertNotEquals(portfolioName, findFirstPortfolioInPortfoliosList());

    }

}
