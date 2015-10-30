Story: Login scenarios

Narrative:
In order to work with Login
As a users with advisor, beginer, expert and intermidiates roles
I want to use Login Form

Scenario:
Setup params. Set user to advisor
Meta:
@basic
Given Set test param username value from property param.advisor.username
And Set test param password value from property param.advisor.password


Scenario:
Login with email and password
Meta:
@name Login with email and password
@basic
Given Browser ready
When I click on login link with css selector .cx-marketing-menu-login-button
And I wait small timeout
And I type %{username} into login form email input with css selector #email
And I type %{password} into login form password input with css selector #password
And I click on login button with css selector input.btn
And I wait until service ready
Then I see welcome div with css selector .jspPane > div:nth-child(1) is:
Welcome to Tickeron. Please select one of the options to the right; and we will teach you how to use the system and guide you through basic steps.

Scenario:
Setup params. Set user to expert
Given Set test param username value from property param.expert.username
Given Set test param password value from property param.expert.password

Scenario:
Login with email and password as Expert User
GivenStories:
    stories/login/01_login.story#{name:Login with email and password}
Given Do nothing

Scenario:
Setup params. Set user to beginner
Given Set test param username value from property param.beginner.username
Given Set test param password value from property param.beginner.password

Scenario:
Login with email and password as Beginner User
GivenStories:
    stories/login/01_login.story#{name:Login with email and password}
Given Do nothing

Scenario:
Setup params. Set user to intermediate
Given Set test param username value from property param.intermediate.username
Given Set test param password value from property param.intermediate.password

Scenario:
Login with email and password as Intermediate User
GivenStories:
    stories/login/01_login.story#{name:Login with email and password}
Given Do nothing
