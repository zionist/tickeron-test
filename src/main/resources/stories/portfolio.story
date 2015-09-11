Story: Work with portfolio

Meta:
@portfolio

Scenario:
Set test params 1
Given I will use FireFoxWebDriver
And I make login with user 4@portfoliodirect.com and password 123456789
Then WebDriver is stopped

Scenario:
Set test params 2
Given I will use ChromWebDriver
And I make login with user 4@portfoliodirect.com and password 123456789
Then WebDriver is stopped
