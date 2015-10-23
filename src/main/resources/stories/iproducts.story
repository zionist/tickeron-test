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
Create newsletters page
Meta:
@name Create newsletters page
GivenStories:
    stories/login.story#{name:Login with email and password}
!-- Open newsletter page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait big timeout
Then I see newsletter page header with css selector h1.ng-binding is: Newsletters
And I see newsletter page list with css selector span.ng-binding is: NO DATA FOUND
!-- Create newsletter page
When I click on Create newsletter link with css selector .cx-btn-create-new
And I wait big timeout
Then I see page header is with css selector span.cx-iproduct-item:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h1:nth-child(1) is: Create Newsletter
When I type newsletter1 into newsletter titile input with css selector #Name
And I will upload file with path /tmp/cat.jpeg using input element with xpath //input[@type='file']
And I click on save button with css selector span.pull-right > button:nth-child(2)
