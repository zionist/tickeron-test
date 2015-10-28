Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

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
Edit issue in unpublished newsletter
Meta:
@name Edit issue in unpublished newsletter
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
Remove issue in unpublished newsletter
Meta:
@name Remove issue in unpublished newsletter
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
And I wait big timeout
!-- And I wait until service ready
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
    stories/02_iproducts_newsletter.story#{name:Add note to unbublished newsletter},
    stories/02_iproducts_newsletter.story#{name:Create newsletter issue for unpublished newsletter},
    stories/02_iproducts_newsletter.story#{name:Publish newsletter},
    stories/02_iproducts_newsletter.story#{name:Unpublish newsletter},
    stories/02_iproducts_newsletter.story#{name:Edit issue in unpublished newsletter},
    stories/02_iproducts_newsletter.story#{name:Remove issue in unpublished newsletter},
    stories/02_iproducts_newsletter.story#{name:Edit unpublished newsletter},
    stories/02_iproducts_newsletter.story#{name:Remove newsletter}
Given Do nothing
