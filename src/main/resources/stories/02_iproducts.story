Story: Iproducts

Scenario:
Setup params. Set user to advisor
Meta:
Given Set test param username value from property param.advisor.username
Given Set test param password value from property param.advisor.password

Scenario:
Create newsletter
Meta:
@name Create newsletter
GivenStories:
    stories/01_login.story#{name:Login with email and password}
!-- Open newsletter
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
Then I see newsletter page header with css selector h1.ng-binding is: Newsletters
And I see newsletters page list with css selector span.ng-binding is: NO DATA FOUND
!-- Create newsletter
When I click on Create newsletter link with css selector .cx-btn-create-new
And I wait until service ready
Then I see page header with css selector span.cx-iproduct-item:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h1:nth-child(1) is: Create Newsletter
When I type newsletter1 into newsletter title input with css selector #Name
And I upload file cat.jpeg using input element with xpath //input[@type='file']
And I wait until service ready
And I click on save button with css selector span.pull-right > button:nth-child(2)
And I wait until service ready

Scenario:
Create newsletter issue
Meta:
@name Create newsletter issue
When I click on add new issue button with css selector button.pull-right
Then I see issue page header with css selector h2.ng-scope is: New issue
When I type issue1 into issue title input with css selector input.ng-valid-maxlength
And I upload file cat.jpeg using input element with xpath //input[@type='file']
And I wait small timeout
And I click on ticker selection input with css selector #filterInput
And I start recording action
And I will move in action to A ticker checkbox element with css selector #chb34108
And I will click in action on A ticker checkbox element with css selector #chb34108
Then I perform previously recorded action
When I click on Add button with css selector div.col-sm-4 > button:nth-child(1)
When I click on save button with css selector span.pull-right:nth-child(1) > button:nth-child(2)
And I wait until service ready
Then I see issue tittle in issues list with css selector .cx-grid-title is: issue1
Then I see page header with css selector .cx-subtitle is: newsletter1
!-- Download issue file
When I download newsletter issue file from <a> element with css selector a.cx-action-icon-button
Then Downloaded file is cat.jpeg
Given Do nothing

