Story: Login scenarios

Meta:
@login
@web

Narrative:
In order to work with Service Login
As a users with advisor, beginer, expert and intermidiates roles
I want to use login form

Scenario:
Setup params for Login scenarios
GivenStories:
    stories/_set_params.story
Given Do nothing

Scenario:
Login with email and password as advisor user
Meta:
@name Login with email and password as advisor user
Given Browser ready
When I click on login link with css selector .cx-marketing-menu-login-button
And I type %{username} into login form email input with css selector #email
And I type %{password} into login form password input with css selector #password
And I click on login button with css selector input.btn
Then I see welcome div with css selector .jspPane > div:nth-child(1) contains:
Welcome to Tickeron. Please select one of the options to the right; and we will teach you how to use the system and guide you through basic steps.



