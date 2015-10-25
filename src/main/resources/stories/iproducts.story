Story: Iproducts

Narrative:
In order to work with Iproducts
As a users with advisor, beginer, expert and intermidiates roles
I want to use Iproducts page

Meta:
@web

Scenario:
Setup params. Set user to advisor
Meta:
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
And I see newsletters page list with css selector span.ng-binding is: NO DATA FOUND
!-- Create newsletter page
When I click on Create newsletter link with css selector .cx-btn-create-new
And I wait big timeout
Then I see page header is with css selector span.cx-iproduct-item:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h1:nth-child(1) is: Create Newsletter
When I type newsletter1 into newsletter title input with css selector #Name
And I upload file cat.jpeg using input element with xpath //input[@type='file']
And I wait small timeout
And I click on save button with css selector span.pull-right > button:nth-child(2)
And I wait big timeout
Then I see page header with css selector .cx-subtitle is: newsletter1

Scenario:
Edit newsletters page
Meta:
@name Edit newsletters page
When I click on Edit newsletter link with css selector button.cx-right-nav-btn:nth-child(1)
And I wait big timeout
Then I see page header is with css selector .cx-ma-less-bottom-margin is: Edit Newsletter
And I see second page header is with css selector .cx-subtitle is: newsletter1
When I type newsletter1_edited into newsletter title input with css selector #Name
!-- Download file check only on edit page
When I download newsletter file from <a> element with css selector .col-sm-offset-3 > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat.jpeg
!-- And I upload file cat2.jpeg using input element with xpath //input[@type='file']
!-- And I wait small timeout
!-- And I click on save button with css selector span.pull-right > button:nth-child(2)
!-- And I wait big timeout
!-- Then I see page header with css selector .cx-subtitle is: newsletter1_edited
Given Do nothing
