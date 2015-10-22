Story: Login scenarios

Meta:
@web

Scenario:
Setup params. Set user to advisor
Given Set test param username value from property param.advisor.username
Given Set test param password value from property param.advisor.password

Scenario:
Login with email and password
Meta:
@name Login with email and password
Given Browser ready
When I click on login link with css selector .cx-marketing-menu-login-button
And I type %{username} into login form email input with css selector #email
And I type %{password} into login form password input with css selector #password
And I click on login button with css selector input.btn
Then I see welcome div with css selector .jspPane > div:nth-child(1) contains:
Welcome to Tickeron. Please1 select one of the options to the right; and we will teach you how to use the system and guide you through basic steps.

Scenario:
Setup params. Set user to expert
Given Set test param username value from property param.expert.username
Given Set test param password value from property param.expert.password

Scenario:
Login with email and password as Expert User
GivenStories:
    stories/login.story#{name:Login with email and password}
Given Do nothing

Scenario:
Setup params. Set user to beginner
Given Set test param username value from property param.beginner.username
Given Set test param password value from property param.beginner.password

Scenario:
Login with email and password as Beginner User
GivenStories:
    stories/login.story#{name:Login with email and password}
Given Do nothing

Scenario:
Setup params. Set user to beginner
Given Set test param username value from property param.intermediate.username
Given Set test param password value from property param.intermediate.password

Scenario:
Login with email and password as Beginner User
GivenStories:
    stories/login.story#{name:Login with email and password}
Given Do nothing
