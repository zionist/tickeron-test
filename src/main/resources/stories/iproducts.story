Story: Iproducts

Narrative:
In order to work with Iproducts
As a users with advisor, beginer, expert and intermidiates roles
I want to use Iproducts page

Meta:
@web

Scenario: scenario description
Given: Do nothing

Scenario:
Setup params. Set user to advisor
Meta:
@basic
Given Set test param username value from property param.advisor.username
Given Set test param password value from property param.advisor.password

Scenario:
Create newsletter
Meta:
@name Create newsletter
GivenStories:
    stories/login.story#{name:Login with email and password}
Given Do nothing
