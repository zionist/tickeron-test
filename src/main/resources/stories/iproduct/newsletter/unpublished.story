Story: Iproducts newsletter

Scenario:
Setup params. Set user to advisor
Given Set test param username value from property param.advisor.username
Given Set test param password value from property param.advisor.password

Scenario:
Create newsletter
Meta:
@name Create newsletter
GivenStories:
    stories/login/login.story#{name:Login with email and password}
!-- Open newsletters page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
!-- Open newsletters page. Check there are no created newsletters
Then I see newsletter page header with css selector h1.ng-binding is: Newsletters
And I see newsletters page list with css selector span.ng-binding is: NO DATA FOUND
!-- Create newsletter with image file
When I click on Create newsletter link with css selector .cx-btn-create-new
And I wait until service ready
Then I see page header with css selector span.cx-iproduct-item:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h1:nth-child(1) is: Create Newsletter
When I type newsletter1 into newsletter title input with css selector #Name
And I upload file cat.jpeg using input element with xpath //input[@type='file']
And I click on save button with css selector span.pull-right > button:nth-child(2)
And I wait until service ready
!-- Check newsletter was created
Then I see newsletter tittle with css selector .cx-subtitle is: newsletter1
And I see newsletter text with css selector div.col-sm-10:nth-child(2) > span:nth-child(1) contains: Created on
And I see newsletter link with css selector a.ng-isolate-scope:nth-child(3) > span:nth-child(1) is: View sample issue
!-- Download newsletter file on edit page. Check it equals uploaded file
When I click on Edit newsletter link with css selector button.cx-right-nav-btn:nth-child(1)
And I wait until service ready
When I download newsletter file from <a> element with css selector .col-sm-offset-3 > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat.jpeg

Scenario:
Add note to unbublished newsletter
Meta:
@name Add note to unbublished newsletter
!-- Open newsletter page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on newsletter1 link with css selector .col-sm-8 > a:nth-child(1)
!-- Write note
And I wait until service ready
When I click on Private notes link with css selector a.cx-right-nav-btn
When I will wait until edotor windows element with css selector .cke_wysiwyg_div will be visible
And I click on editor window with css selector .cke_wysiwyg_div
And I wait small timeout
And I type test into notes text area with css selector .cke_wysiwyg_div
And I wait small timeout
And I click on Add notes button with css selector input.btn-primary:nth-child(1)
When I will wait until ticker element element with css selector pre.ng-binding will be visible
!-- Check note is in the top window
Then I see message in top window with css selector pre.ng-binding is: test
When I click on X icon with css selector .cx-chat-close-icon
And I wait small timeout

Scenario:
Create newsletter issue for unpublished newsletter
Meta:
@name Create newsletter issue for unpublished newsletter
!-- Open previously created newsletter using top menu
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on newsletter1 link with css selector .col-sm-8 > a:nth-child(1)
And I wait until service ready
!-- Add new newsletter issue with ticker and image file
When I click on add new issue button with css selector button.pull-right
Then I see issue page header with css selector h2.ng-scope is: New issue
When I type issue1 into issue title input with css selector input.ng-valid-maxlength
And I upload file sample.pdf using input element with xpath //input[@type='file']
!-- # Wait until tickers list will be loaded
And I click on ticker selection input with css selector #filterInput
And I will wait until ticker element element with css selector #chb34108 will be visible
!-- # Tickers list appears. Add ticker
And I start recording action
And I will move in action to A ticker checkbox element with css selector #chb34108
And I will click in action on A ticker checkbox element with css selector #chb34108
And I will move in action to Add button element with css selector div.col-sm-4 > button:nth-child(1)
And I will click in action on Add button element with css selector div.col-sm-4 > button:nth-child(1)
Then I perform previously recorded action
!-- # Wait until tickers list will be not visible
When I wait big timeout
!-- # Save newsletter
When I click on save button with css selector span.pull-right:nth-child(1) > button:nth-child(2)
And I wait until service ready
Then I see issue tittle in issues list with css selector .cx-grid-title is: issue1
Then I see page header with css selector .cx-subtitle is: newsletter1
!-- Check issue was created, ticker is in the issue. Download issue file, check it
Then I see ussue title with css selector .cx-grid-title is: issue1
And I see tickers table field with css selector .cx-navigation-list-item > div:nth-child(2) > div:nth-child(2) > span:nth-child(1) > span:nth-child(1) > span:nth-child(1) is: A
When I download newsletter issue file from <a> element with css selector a.cx-action-icon-button
Then Downloaded file is sample.pdf


Scenario:
Edit issue in unpublished newsletter
Meta:
@name Edit unpublished issue in published newsletter
!-- Open edit newsletter issue page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on newsletter1 link with css selector .cx-grid-title
And I wait until service ready
And I click on edit issue1 link with css selector button.fa-pencil
!-- Edit issue. Change title, remove ticker
Then I see header with css selector h2.ng-scope is: Edit issue
When I type issue1_edited into issue titile input with css selector input.ng-valid-maxlength
And I upload file sample2.pdf using input element with xpath //input[@type='file']
And I click on ticker trash icon with css selector td.text-center:nth-child(8) > a:nth-child(1)
And I click on save button with css selector div.row:nth-child(9) > div:nth-child(1) > span:nth-child(1) > button:nth-child(2)
!-- Check issue was edited. Download issue file, check it
And I wait until service ready
Then I see issue tittle in issues list with css selector .cx-grid-title is: issue1_edited
When I download newsletter issue file from <a> element with css selector a.cx-action-icon-button
Then Downloaded file is sample2.pdf
!-- Click on edit. Check there is no tickers added
When I click on edit issue link with css selector button.fa-pencil
Then I see tickers list with css selector div.row:nth-child(8) > div:nth-child(1) > span:nth-child(1) > span:nth-child(2) > div:nth-child(1) is: NO TICKERS SELECTED YET


Scenario:
Remove issue in unpublished newsletter
Meta:
@name Remove issue in unpublished newsletter
!-- Open edit newsletter issue page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on newsletter1_edited link with css selector .col-sm-8 > a:nth-child(1)
And I wait until service ready
!-- Remove newsletter issue
And I click on remove issue icon with css selector .fa-trash-o
And I will wait until confirmation dialog element with css selector div.in:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) will be visible
And I click on Yes button with css selector button.ng-binding:nth-child(1)
And I wait until service ready
!-- Check newsletter issues list is empty
Then I see issues list in newsletter with css selector div.ng-isolate-scope:nth-child(3) > span:nth-child(3) > div:nth-child(1) is: NEWSLETTER CONTAINS NO ISSUES

Scenario:
Edit unpublished newsletter
Meta:
@name Edit unpublished newsletter
!-- Open newsletter using top menu
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on newsletter1_edited link with css selector .col-sm-8 > a:nth-child(1)
And I wait until service ready
!-- Edit newsletter. Change title and upload new file
When I click on Edit newsletter link with css selector button.cx-right-nav-btn:nth-child(1)
And I wait until service ready
Then I see page header is with css selector .cx-ma-less-bottom-margin is: Edit Newsletter
And I see second page header is with css selector .cx-subtitle is: newsletter1
When I type newsletter1_edited into newsletter title input with css selector #Name
When I upload file cat2.jpeg using input element with xpath //input[@type='file']
And I wait until service ready
And I click on save button with css selector span.pull-right > button:nth-child(2)
!-- Go to Edit page again check newsletter updated. Download new file
And I wait until service ready
Then I see page header with css selector .cx-subtitle is: newsletter1_edited
When I click on Edit newsletter link with css selector button.cx-right-nav-btn:nth-child(1)
And I wait until service ready
Then I see page header with css selector .cx-ma-less-bottom-margin is: Edit Newsletter
And I see second page header with css selector .cx-subtitle is: newsletter1_edited
When I download newsletter file from <a> element with css selector .col-sm-offset-3 > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat2.jpeg
When I click on cancel button with css selector span.pull-right > button:nth-child(1)
And I wait until service ready

Scenario:
Remove newsletter
Meta:
@name Remove newsletter
!-- Open newsletters using top menu
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
!-- Remove newsletter
And I click on newletter trash icon with css selector button.btn-link
And I will wait until confirmation dialog element with css selector div.in:nth-child(1) > div:nth-child(1) will be visible
And I click on "Yes" button in confirmation dialog window with css selector button.ng-binding:nth-child(1)
And I wait until service ready
!-- Check newsletter was removed
Then I see newsletter page header with css selector h1.ng-binding is: Newsletters
And I see newsletters page list with css selector span.ng-binding is: NO DATA FOUND
Given Do nothing

Scenario:
Setup params. Set user to expert
Given Set test param username value from property param.expert.username
Given Set test param password value from property param.expert.password

Scenario: Work with unpublished newsletters as expert user
GivenStories:
    stories/iproduct/newsletter/unpublished.story#{name:Create newsletter},
    stories/iproduct/newsletter/unpublished.story#{name:Add note to unbublished newsletter},
    stories/iproduct/newsletter/unpublished.story#{name:Create newsletter issue for unpublished newsletter},
    stories/iproduct/newsletter/unpublished.story#{name:Edit issue in unpublished newsletter},
    stories/iproduct/newsletter/unpublished.story#{name:Remove issue in unpublished newsletter},
    stories/iproduct/newsletter/unpublished.story#{name:Edit unpublished newsletter},
    stories/iproduct/newsletter/unpublished.story#{name:Remove newsletter}
Given Do nothing
