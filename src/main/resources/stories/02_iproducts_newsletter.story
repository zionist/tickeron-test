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
    stories/01_login.story#{name:Login with email and password}
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
Add newsletter note
Meta:
@name Add newsletter note
!-- Open newsletter page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on newsletter1 link with css selector .col-sm-8 > a:nth-child(1)
!-- Write note
And I wait until service ready
When I click on Private notes link with css selector a.cx-right-nav-btn
And I wait small timeout
And I type test into notes text area with css selector .cke_wysiwyg_div
And I click on Add notes button with css selector input.btn-primary:nth-child(1)
And I wait small timeout
!-- Check note is in the top window
Then I see message in top window with css selector pre.ng-binding is: test
When I click on X icon with css selector .cx-chat-close-icon
And I wait small timeout

Scenario:
Create newsletter issue
Meta:
@name Create newsletter issue
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
And I upload file cat.jpeg using input element with xpath //input[@type='file']
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
Then Downloaded file is cat.jpeg


Scenario:
Publish newsletter
Meta:
@name Publish newsletter
!-- Open newsletter page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on newsletter1 link with css selector .col-sm-8 > a:nth-child(1)
!-- Click on publish button
And I wait until service ready
When I click on publish button with css selector button.cx-right-nav-btn:nth-child(3)
!-- Type monthly fee and description in publish form. Add one issue for publish
And I wait until service ready
Then I see page header with css selector .cx-ma-less-bottom-margin is: Publish Newsletter
And I see second page header with css selector .cx-subtitle is: newsletter1
When I type newsletter description into description textarea with css selector textarea.form-control
And I type 2.0 into mounthly fee input with css selector input.form-control
And I click on select first issue to publush with css selector span.fa:nth-child(1)
!-- Preview newsletter for publish. Check issue added, description and fee
And I click on Preview link with css selector .cx-right-nav-btn
And I wait until service ready
Then I see in product detail message header with css selector .col-sm-7 > h3:nth-child(1) is: newsletter1
And I see in product detail message description with css selector .cx-ma-description-container > span:nth-child(1) is:  newsletter description
And I see in product detail message mounthly free with css selector h3.ng-scope is: $2.00
And I see recent issue titile with css selector .col-sm-6 > div:nth-child(1) > div:nth-child(2) > span:nth-child(1) is: issue1
When I click on publish link with css selector .cx-right-nav-btn
And I wait until service ready
!-- Check newsletter with issue is published. Check description, fee, issues count
Then I see newsletter title in newsletter with css selector .cx-grid-title is: newsletter1
And I see newsletter description with css selector span.ng-binding:nth-child(2) is: newsletter description
And I see newsletter ticker in newsletter with css selector .label is: A
And I see issues count in newsletter with css selector div.col-sm-1:nth-child(4) is: 1
And I see Fee in newsletter with css selector div.text-center:nth-child(6) is: $2.00

Scenario:
Unpublish newsletter
Meta:
@name Unpublish newsletter
!-- Click unpublish icon on published newsletter in the published newsletters list
When I click on unpublish button with css selector button.btn-link
And I will wait until confirmation dialog element with css selector div.in:nth-child(1) > div:nth-child(1) will be visible
And I click on Yes button with css selector button.ng-binding:nth-child(1)
And I wait until service ready
!-- Check newsletter not in published list
Then I see published newsletters list text with css selector span.ng-binding is: NO DATA FOUND
!-- Check newsletter appears in unpublished list
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on newsletter1 link with css selector .col-sm-8 > a:nth-child(1)
And I wait until service ready
Then I see newsletter title with css selector .cx-subtitle is: newsletter1
And I see newsletter text with css selector div.col-sm-10:nth-child(2) > span:nth-child(1) contains: Created on
And I see newsletter link with css selector a.ng-isolate-scope:nth-child(3) > span:nth-child(1) is: View sample issue

Scenario:
Edit newsletter issue
Meta:
@name Edit newsletter issue
!-- Open edit newsletter issue page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on newsletter1 link with css selector .col-sm-8 > a:nth-child(1)
And I wait until service ready
When I click on edit link  with css selector button.fa-pencil
Then I see second page header with css selector h2.ng-scope is: Edit issue
!-- Edit newsletter issue. Change title, upload new file, remove ticker
When I type issue2 into issue title with css selector input.ng-valid-maxlength
And I click on ticker trash icon with css selector i.fa-trash-o
And I upload file cat2.jpeg using input element with xpath //input[@type='file']
When I click on save button with css selector span.pull-right:nth-child(1) > button:nth-child(2)
!-- Check newsletter issue was edited. Check ticker was removed. Download issue file, check it
And I wait until service ready
Then I see issue tittle in issues list with css selector .cx-grid-title is: issue2
Then I see page header with css selector .cx-subtitle is: newsletter1
When I download newsletter issue file from <a> element with css selector a.cx-action-icon-button
Then Downloaded file is cat2.jpeg
When I click on edit link  with css selector button.fa-pencil
Then I see tickers list with css selector div.row:nth-child(8) > div:nth-child(1) > span:nth-child(1) > span:nth-child(2) > div:nth-child(1) is: NO TICKERS SELECTED YET

Scenario:
Remove newsletter issue
Meta:
@name Remove newsletter issue
!-- Open edit newsletter issue page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on newsletter1 link with css selector .col-sm-8 > a:nth-child(1)
And I wait until service ready
!-- Remove newsletter issue
And I click on edit link  with css selector button.fa-pencil
And I click on issue trash icon with css selector .fa-trash-o
And I will wait until confirmation dialog element with css selector div.in:nth-child(1) > div:nth-child(1) will be visible
And I click on Yes button in confirmation dialog with css selector button.ng-binding:nth-child(1)
And I wait until service ready
!-- Check newsletter issues list is empty
Then I see issues list in newsletter with css selector div.ng-isolate-scope:nth-child(3) > span:nth-child(3) > div:nth-child(1) is: NEWSLETTER CONTAINS NO ISSUES

Scenario:
Edit newsletter
Meta:
@name Edit newsletter
!-- Open newsletter using top menu
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on newsletter1 link with css selector .col-sm-8 > a:nth-child(1)
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
!-- Remove newsletter
When I click on Remove link with css selector button.cx-right-nav-btn:nth-child(2)
And I wait until service ready
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

Scenario: Work with newsletters as expert user
GivenStories:
    stories/02_iproducts_newsletter.story#{name:Create newsletter},
    stories/02_iproducts_newsletter.story#{name:Add newsletter note},
    stories/02_iproducts_newsletter.story#{name:Create newsletter issue},
    stories/02_iproducts_newsletter.story#{name:Publish newsletter},
    stories/02_iproducts_newsletter.story#{name:Unpublish newsletter},
    stories/02_iproducts_newsletter.story#{name:Edit newsletter issue},
    stories/02_iproducts_newsletter.story#{name:Remove newsletter issue},
    stories/02_iproducts_newsletter.story#{name:Edit newsletter},
    stories/02_iproducts_newsletter.story#{name:Remove newsletter}
Given Do nothing
