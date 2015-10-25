Story: Portfolio scenarios

Meta:
@portfolio
@web

Narrative:
In order to work with Portfolio
As a users with advisor, beginer, expert and intermidiates roles
I want to use portfolio menu tab and wizards

Scenario:
Setup params. Set user to advisor
Meta:
Given Set test param username value from property param.advisor.username
Given Set test param password value from property param.advisor.password

Scenario: Create portfolio as advisor user using starts page wizard
Meta:
@name Create portfolio as advisor user using starts page wizard
GivenStories:
    stories/login.story#{name:Login with email and password}
Given Do nothing



