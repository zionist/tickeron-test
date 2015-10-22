Meta:
@web

Narrative:
In order to work with Iproducts
As a users with advisor, beginer, expert and intermidiates roles
I want to use Iproducts page

Scenario: scenario description
Given: Do nothing

Scenario:
Setup params. Set user to expert
Given Set test param username value from property param.expert.username
Given Set test param password value from property param.expert.password

Scenario:
Login with email and password as Advisor User
Meta:
@name Login with email and password
Given Browser ready
When I click on login link with css selector .cx-marketing-menu-login-button
And I type %{username} into login form email input with css selector #email
And I type %{password} into login form password input with css selector #password
And I click on login button with css selector input.btn
Then I see welcome div with css selector .jspPane > div:nth-child(1) contains:
Welcome to Tickeron. Please select one of the options to the right; and we will teach you how to use the system and guide you through basic steps.
