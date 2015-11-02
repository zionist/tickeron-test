Story: Iproducts newsletter

Scenario:
Setup params. Set user to advisor
Given Set test param username value from property param.advisor.username
Given Set test param password value from property param.advisor.password

Scenario:
Publish newsletter with issue
Meta:
@name Publish newsletter with issue
GivenStories:
    stories/iproduct/newsletter/unpublished.story#{name:Create newsletter},
    stories/iproduct/newsletter/unpublished.story#{name:Create newsletter issue for unpublished newsletter}
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
Edit published newsletter
Meta:
@name Edit published newsletter
!-- Open published newsletter using top menu
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
And I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I click on published tab with css selector ul.nav:nth-child(3) > li:nth-child(2) > a:nth-child(1)
And I wait until service ready
And I click on newsletter1 link with css selector .cx-grid-title
And I wait until service ready
!-- Click edit link. Change title. Upload new image. Save newsletter
And I click on edit link in issue with css selector .cx-nowrap > button:nth-child(1)
And I wait until service ready
And I type newsletter1_edited into title input with css selector #Name
And I upload file cat2.jpeg using input element with xpath //input[@type='file']
And I click on Save button with css selector span.pull-right > button:nth-child(2)
!-- Check title is updated
And I wait until service ready
Then I see newsletter titile with css selector .cx-subtitle is: newsletter1_edited
!-- Change monthly fee
When I click on edit fee icon with css selector .panel-body > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > span:nth-child(2) > button:nth-child(1)
And I will wait until fee field element with css selector input.form-control:nth-child(2) will be visible
And I type 3.00 into fee field with css selector input.form-control:nth-child(2)
And I click on Save fee button with css selector div.col-sm-2:nth-child(4) > div:nth-child(1) > div:nth-child(3) > span:nth-child(1) > button:nth-child(2)
!-- Check fee is changed
Then I see fee value with css selector h3.ng-scope is: $3.00
!-- Check description is changed
When I click on edit description icon with css selector button.btn-link
And I type description edited into descripton textarea with css selector textarea.form-control
And I click on Save description button with css selector .pull-left > button:nth-child(2)
Then I see description with css selector div.col-sm-11:nth-child(2) > span:nth-child(1) is: description edited
!-- Download file .Check it
When I click on Edit link with css selector .cx-nowrap > button:nth-child(1)
And I wait until service ready
When I download newsletter file from <a> element with css selector .col-sm-offset-3 > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat2.jpeg

Scenario:
Add issue to published newsletter
Meta:
@name Add issue to published newsletter
!-- Open published newsletter using top menu
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
And I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I click on published tab with css selector ul.nav:nth-child(3) > li:nth-child(2) > a:nth-child(1)
And I wait until service ready
And I click on newsletter1 link with css selector .cx-grid-title
And I wait until service ready
!-- Open published newsletter using top menu
When I click on add new issue button with css selector button.pull-right
Then I see issue page header with css selector h2.ng-scope is: New issue
When I type issue2 into issue title input with css selector input.ng-valid-maxlength
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
!-- Check issue was created, ticker is in the issue. Download issue file, check it
Then I see issue tittle in issues list with css selector .cx-grid-title is: issue2
Then I see page header with css selector .cx-subtitle is: newsletter1_edited
And I see tickers table field with css selector .cx-navigation-list-item > div:nth-child(3) > span:nth-child(1) > span:nth-child(1) > span:nth-child(1) is: A
When I download newsletter issue file from <a> element with css selector a.cx-action-icon-button
Then Downloaded file is sample.pdf

Scenario:
Edit unpublished issue in published newsletter
Meta:
@name Edit unpublished issue in published newsletter
!-- Edit issue. Change title, remove ticker
When I click on edit issue link with css selector button.fa-pencil
Then I see header with css selector h2.ng-scope is: Edit issue
When I type issue2_edited into issue titile input with css selector input.ng-valid-maxlength
And I upload file sample2.pdf using input element with xpath //input[@type='file']
And I click on ticker trash icon with css selector td.text-center:nth-child(8) > a:nth-child(1)
And I click on save button with css selector div.row:nth-child(9) > div:nth-child(1) > span:nth-child(1) > button:nth-child(2)
!-- Check issue was edited. Download issue file, check it
And I wait until service ready
Then I see issue tittle in issues list with css selector .cx-grid-title is: issue2_edited
When I download newsletter issue file from <a> element with css selector a.cx-action-icon-button
Then Downloaded file is sample2.pdf
!-- Click on edit. Check there is no tickers added
When I click on edit issue link with css selector button.fa-pencil
Then I see tickers list with css selector div.row:nth-child(8) > div:nth-child(1) > span:nth-child(1) > span:nth-child(2) > div:nth-child(1) is: NO TICKERS SELECTED YET

Scenario:
Edit published issue in published newsletter
Meta:
@name Edit published issue in published newsletter
!-- Change title. Change file. Delete ticker
When I click on published issues tab with css selector .nav-tabs > li:nth-child(2) > a:nth-child(1)
!-- Change title. Change file. Delete ticker
And I click on edit icon with css selector button.fa-pencil
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
Publish issue in published newsletter
Meta:
@name Publish issue in published newsletter
!-- Publish issue
When I click on Unpublished issues tab element with css selector .nav-tabs > li:nth-child(1) > a:nth-child(1)
And I click on share icon element with css selector .fa-check-circle-o
And I will wait until publish confirmation dialog element with css selector div.in:nth-child(1) > div:nth-child(1) will be visible
And I click on Yes button with css selector button.ng-binding:nth-child(1)
And I wait until service ready
!-- Check issue is published. Download file
Then I see issue titile with css selector div.cx-navigation-list-item:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1) > span:nth-child(1) is: issue2_edited
And I see published issue tab with css selector li.active:nth-child(2) > a:nth-child(1) is: Published Issues
When I download issue file file from <a> element with css selector div.cx-navigation-list-item:nth-child(2) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is sample2.pdf

Scenario:
Unpublish published newsletter issue
Meta:
@name Unpublish published newsletter issue
!-- Unpublish issue
When I click on unpublish icon with css selector div.cx-navigation-list-item:nth-child(2) > div:nth-child(6) > button:nth-child(1)
And I will wait until unpublish confirmation dialog element with css selector div.in:nth-child(1) > div:nth-child(1) will be visible
And I click on Yes button with css selector button.ng-binding:nth-child(1)
And I wait until service ready
!-- Check issue was unpublished. Download file
Then I see issue titile with css selector .cx-grid-title is: issue2_edited
And I see Unpublished issues tab with css selector .nav-tabs > li:nth-child(1) > a:nth-child(1) is: Unpublished Issues
When I download issue file from <a> element with css selector a.btn:nth-child(1)
Then Downloaded file is sample2.pdf

Scenario:
Remove newsletter issue from published newsletter
Meta:
@name Remove newsletter issue from published newsletter
!-- Remove unpublished issue
When I click on unpublished issue trash icon with css selector .fa-trash-o
And I will wait until remove confirmation dialog element with css selector div.in:nth-child(1) > div:nth-child(1) will be visible
And I click on Yes button with css selector button.ng-binding:nth-child(1)
And I wait until service ready
!-- Check issue was removed
Then I see unpublished issues list with css selector span.ng-isolate-scope:nth-child(2) > span:nth-child(3) > span:nth-child(2) > div:nth-child(1) is: NEWSLETTER CONTAINS NO UNPUBLISHED ISSUES

Scenario:
Unpublish newsletter
Meta:
@name Unpublish newsletter
!-- Open newsletter page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on newsletters menu item with css selector .open > ul:nth-child(3) > li:nth-child(1) > a:nth-child(2)
And I wait until service ready
And I click on published tab link with css selector ul.nav:nth-child(3) > li:nth-child(2) > a:nth-child(1)
And I wait until service ready
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
Then I see newsletter title with css selector .cx-subtitle is: newsletter1_edited
And I see newsletter text with css selector div.col-sm-10:nth-child(2) > span:nth-child(1) contains: Created on
And I see newsletter link with css selector a.ng-isolate-scope:nth-child(3) > span:nth-child(1) is: View sample issue
!-- Download newsletter file on edit page. Check it equals uploaded file
When I click on Edit newsletter link with css selector button.cx-right-nav-btn:nth-child(1)
And I wait until service ready
When I download newsletter file from <a> element with css selector .col-sm-offset-3 > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat2.jpeg

Scenario:
Remove newsletter
Meta:
@name remove newsletter
GivenStories:
    stories/iproduct/newsletter/unpublished.story#{name:Remove newsletter}
Given Do nothing

Scenario:
Setup params. Set user to expert
Given Set test param username value from property param.expert.username
Given Set test param password value from property param.expert.password

Scenario: Work with published newsletters as expert user
GivenStories:
    stories/iproduct/newsletter/published.story#{name:Publish newsletter with issue},
    stories/iproduct/newsletter/published.story#{name:Edit published newsletter},
    stories/iproduct/newsletter/published.story#{name:Add issue to published newsletter},
    stories/iproduct/newsletter/published.story#{name:Edit unpublished issue in published newsletter},
    stories/iproduct/newsletter/published.story#{name:Edit published issue in published newsletter},
    stories/iproduct/newsletter/published.story#{name:Publish issue in published newsletter},
    stories/iproduct/newsletter/published.story#{name:Unpublish published newsletter issue},
    stories/iproduct/newsletter/published.story#{name:Remove newsletter issue from published newsletter},
    stories/iproduct/newsletter/published.story#{name:Unpublish newsletter},
    stories/iproduct/newsletter/published.story#{name:Remove newsletter}
Given Do nothing
