Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal


Scenario:
Edit newsletter
Meta:
@name Edit newsletter
!-- Open previously created newsletter using top menu
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
And I click on save button with css selector span.pull-right > button:nth-child(2)
!-- Check newsletter was edited
And I wait until service ready
Then I see page header with css selector .cx-subtitle is: newsletter1_edited
!-- Go to Edit page again. Download new file which was uploaded on edit. Check it equals uploaded file
When I click on Edit newsletter link with css selector button.cx-right-nav-btn:nth-child(1)
And I wait until service ready
Then I see page header with css selector .cx-ma-less-bottom-margin is: Edit Newsletter
And I see second page header with css selector .cx-subtitle is: newsletter1_edited
When I download newsletter file from <a> element with css selector .col-sm-offset-3 > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat2.jpeg



Scenario:
Create newsletter issue
Meta:
@name Create newsletter issue
When I click on add new issue button with css selector button.pull-right
Then I see issue page header with css selector h2.ng-scope is: New issue
When I type issue1 into issue title input with css selector input.ng-valid-maxlength
And I upload file cat.jpeg using input element with xpath //input[@type='file']
!-- Wait intil tickers list will be loaded
And I click on ticker selection input with css selector #filterInput
And I will wait until ticker element element with css selector #chb34108 will be visible
!-- Tickers list appears. Lets start move to first ticker element
And I start recording action
And I will move in action to A ticker checkbox element with css selector #chb34108
And I will click in action on A ticker checkbox element with css selector #chb34108
And I will move in action to Add button element with css selector div.col-sm-4 > button:nth-child(1)
And I will click in action on Add button element with css selector div.col-sm-4 > button:nth-child(1)
Then I perform previously recorded action
!-- Wait until tickers list will be not visible
When I wait big timeout
When I click on save button with css selector span.pull-right:nth-child(1) > button:nth-child(2)
And I wait until service ready
Then I see issue tittle in issues list with css selector .cx-grid-title is: issue1
Then I see page header with css selector .cx-subtitle is: newsletter1
!-- Download issue file
When I download newsletter issue file from <a> element with css selector a.cx-action-icon-button
Then Downloaded file is cat.jpeg



Scenario:
Remove newsletter issue
Meta:
@name Remove newsletter issue
When I click on cancel button with css selector button.btn-default
And I click on edit link  with css selector button.fa-pencil
And I click on issue trash icon with css selector .fa-trash-o
And I wait small timeout
And I click on Yes button in confirmation dialog with css selector button.ng-binding:nth-child(1)
And I wait until service ready
Then I see issues list in newsletter with css selector div.ng-isolate-scope:nth-child(3) > span:nth-child(3) > div:nth-child(1) is: NEWSLETTER CONTAINS NO ISSUES


Scenario:
Setup params. Set user to expert
Meta:
Given Set test param username value from property param.expert.username
Given Set test param password value from property param.expert.password

Scenario:
Publish newsletter
Meta:
@name Publish newsletter
When I click on publish button with css selector button.cx-right-nav-btn:nth-child(3)
And I wait until service ready
Then I see page header with css selector .cx-ma-less-bottom-margin is: Publish Newsletter
And I see second page header with css selector .cx-subtitle is: newsletter1
When I type newsletter description into description textarea with css selector textarea.form-control
And I type 2.0 into mounthly fee input with css selector input.form-control
And I click on select first issue to publush with css selector span.fa:nth-child(1)
And I click on Preview link with css selector .cx-right-nav-btn
And I wait until service ready
Then I see in product detail message header with css selector .col-sm-7 > h3:nth-child(1) is: newsletter1
And I see in product detail message description with css selector .cx-ma-description-container > span:nth-child(1) is:  newsletter description
And I see in product detail message mounthly free with css selector h3.ng-scope is: $2.00
And I see recent issue titile with css selector .col-sm-6 > div:nth-child(1) > div:nth-child(2) > span:nth-child(1) is: newsletter1
When I click on publish link with css selector .cx-right-nav-btn
And I wait until service ready

Scenario: Work with newsletters as expert user
GivenStories:
    stories/02_iproducts_newsletter.story#{name:Create newsletter},
    stories/02_iproducts_newsletter.story#{name:Create newsletter issue},
    stories/02_iproducts_newsletter.story#{name:Edit newsletter issue},
    stories/02_iproducts_newsletter.story#{name:Remove newsletter issue},
    stories/02_iproducts_newsletter.story#{name:Edit newsletter},
    stories/02_iproducts_newsletter.story#{name:Remove newsletter}
Given Do nothing

