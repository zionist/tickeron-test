Story: Portfolio scenarios

Meta:
@portfolio
@web

Narrative:
In order to work with Portfolio
As a users with advisor, beginer, expert and intermidiates roles
I want to use portfolio menu tab and wizards

Scenario: Create portfolio as advisor user using starts page wizard
Meta:
@name Create portfolio as advisor user using starts page wizard
GivenStories:
    stories/login.story#{name:Login with email and password as advisor user}
When I create basic portfolio AdvisorUserPortfolio1 using starts page wizard
Then I see Portfolio AdvisorUserPortfolio1 in portfolio list

Scenario: Delete portfolio as advisor user
Meta:
@name Delete portfolio as advisor user
GivenStories:
    stories/login.story#{name:Login with email and password as advisor user}
Given I see Portfolio AdvisorUserPortfolio1 in portfolio list
When I delete portfolio AdvisorUserPortfolio1
Then I do not see Portfolio AdvisorUserPortfolio1 in portfolio list




