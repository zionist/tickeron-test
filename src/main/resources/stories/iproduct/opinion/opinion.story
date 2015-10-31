Story: Iproducts opinion

Scenario:
Setup params. Set user to advisor
Given Set test param username value from property param.advisor.username
Given Set test param password value from property param.advisor.password

Scenario:
Create opinion
Meta:
@name Create opinion
GivenStories:
    stories/login/login.story#{name:Login with email and password}
!-- Open opinions page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on opinions menu item with css selector .open > ul:nth-child(3) > li:nth-child(2) > a:nth-child(2)
And I wait until service ready
!-- Open opinions page. Check there are no created opinions
Then I see opinion page header with css selector h1.ng-binding is: Opinions
And I see opinions page list with css selector span.ng-binding is: NO DATA FOUND
!-- Create opinion with image file
When I click on Create opinion link with css selector .cx-btn-create-new
And I wait until service ready
Then I see page header with css selector span.cx-iproduct-item:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h1:nth-child(1) is: Create Opinion
When I type opinion1 into opinion title input with css selector input.ng-valid-maxlength
And I upload file cat.jpeg using input element with xpath //input[@type='file']
!-- # Wait until tickers list will be loaded
And I click on ticker selection input with css selector #filterInput
And I will wait until ticker element element with css selector #chb34108 will be visible
!-- # Tickers list appears. Add two tickers
And I start recording action
And I will move in action to A ticker checkbox element with css selector #chb34108
And I will click in action on A ticker checkbox element with css selector #chb34108
And I will move in action to A ticker checkbox element with css selector #chb33569
And I will click in action on A ticker checkbox element with css selector #chb33569
And I will move in action to Add button element with css selector div.col-sm-4 > button:nth-child(1)
And I will click in action on Add button element with css selector div.col-sm-4 > button:nth-child(1)
Then I perform previously recorded action
!-- Save opinion
When I wait big timeout
And I click on save button with css selector span.pull-right > button:nth-child(2)
And I wait until service ready
!-- Check opinion was created
Then I see opinion tittle with css selector .cx-subtitle is: opinion1
And I see page header with css selector .cx-ma-less-bottom-margin is: Personal Opinion
And I see first ticker with css selector span.text-center > span:nth-child(1) is: A
And I see second ticker with css selector span.text-center > span:nth-child(2) > span:nth-child(1) is: AA
When I download opinion file file from <a> element with css selector .cx-upload-line > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat.jpeg

Scenario:
Add note to opinion
Meta:
@name Add note to opinion
!-- Open opinion page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on opinions menu item with css selector .open > ul:nth-child(3) > li:nth-child(2) > a:nth-child(2)
And I wait until service ready
And I click on opinion1 link element with css selector .cx-grid-title
And I wait until service ready
!-- Write note
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
Edit unpublished opinion
Meta:
@name Edit unpublished opinion
!-- Open opinion page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on opinions menu item with css selector .open > ul:nth-child(3) > li:nth-child(2) > a:nth-child(2)
And I wait until service ready
And I click on opinion1 link element with css selector .cx-grid-title
And I wait until service ready
And I click on edit link with css selector button.cx-right-nav-btn:nth-child(1)
!-- Edit page. Download file. Upload new file.
And I wait until service ready
And I type opinion1_edited into $description with css selector input.ng-valid-maxlength
And I download opinion file from <a> element with css selector .col-sm-offset-3 > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat.jpeg
When I upload file cat2.jpeg using input element with xpath //input[@type='file']
And I click on remove ticker icon with css selector tr.ng-scope:nth-child(3) > td:nth-child(8) > a:nth-child(1) > i:nth-child(1)
And I click on save button with css selector span.pull-right:nth-child(1) > button:nth-child(2)
And I wait until service ready
!-- Check edit. Download file. Check title
Then I see opinion title with css selector .cx-subtitle is: opinion1_edited
When I download opinion file from <a> element with css selector .cx-upload-line > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat2.jpeg
And I see second ticker with css selector span.text-center > span:nth-child(2) > span:nth-child(1) is not: AA

Scenario:
Publish opinion
Meta:
@name Publish opinion
!-- Open opinion page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on opinions menu item with css selector .open > ul:nth-child(3) > li:nth-child(2) > a:nth-child(2)
And I wait until service ready
And I click on opinion1 link element with css selector .cx-grid-title
And I wait until service ready
!-- publish opinion
When I click on publish link with css selector button.cx-right-nav-btn:nth-child(3)
Then I see page header with css selector .cx-ma-less-bottom-margin is: Publish
!-- Set price and description
When I type 5.00 into $description with css selector input.form-control
And I type opinion description into description input with css selector textarea.form-control
!-- Check preview page
And I click on Preview link with css selector .cx-right-nav-btn
And I wait until service ready
Then I see page header is with css selector .cx-ma-less-bottom-margin is: Preview in MALL
And I see opinon title is with css selector .cx-subtitle is: opinion1_edited
And I see tickers list on search page with css selector .cx-navigation-list-first-item > div:nth-child(3) > span:nth-child(1) > span:nth-child(1) > span:nth-child(1) is: A
And I see price on search page with css selector div.text-center:nth-child(6) is: $5.00
!-- Publish
When I click on publish button with css selector .cx-right-nav-btn
And I wait until service ready
!-- Check opinion is published
Then I see opinion tittle with css selector .cx-grid-title is: opinion1_edited
And I see tickers list with css selector .label is: A
And I see opinion price with css selector div.ng-binding:nth-child(6) is: $5.00
When I download opinion file file from <a> element with css selector a.btn
Then Downloaded file is cat2.jpeg


Scenario:
Edit published opinion
Meta:
@name Edit published opinion
!-- Open opinion page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on opinions menu item with css selector .open > ul:nth-child(3) > li:nth-child(2) > a:nth-child(2)
And I wait until service ready
And I click on Published tab with css selector ul.nav:nth-child(3) > li:nth-child(2) > a:nth-child(1)
And I wait until service ready
And I click on opinion link with css selector .cx-grid-title
!-- Check header
Then I see .cx-ma-less-bottom-margin with css selector .cx-ma-less-bottom-margin is: Published Opinion
And I see opinion titile with css selector .cx-subtitle is: opinion1_edited
And I see opinion description with css selector div.col-sm-11:nth-child(2) > span:nth-child(1) is: opinion description
!-- Click edit link
When I click on edit link with css selector button.ng-isolate-scope:nth-child(1)
And I wait until service ready
!-- Edit opinion. Change title. Remove ticker. Upload new file
And I type opinion1_edited2 into $description with css selector input.ng-valid-maxlength
And I download opinion file from <a> element with css selector .col-sm-offset-3 > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat2.jpeg
When I upload file cat.jpeg using input element with xpath //input[@type='file']
And I click on remove ticker icon with css selector .fa-trash-o
And I click on save button with css selector span.pull-right:nth-child(1) > button:nth-child(2)
And I wait until service ready
!-- Check edit. Download file. Check title. Check ticker was removed
Then I see opinion title with css selector .cx-subtitle is: opinion1_edited2
When I download opinion file from <a> element with css selector .cx-upload-line > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat.jpeg
And I see first ticker with css selector span.text-center > span:nth-child(1) is not: A

Scenario:
Unpublish opinion
Meta:
@name Unpiblish opinion
!-- Open opinion page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on opinions menu item with css selector .open > ul:nth-child(3) > li:nth-child(2) > a:nth-child(2)
And I wait until service ready
And I click on Published tab with css selector ul.nav:nth-child(3) > li:nth-child(2) > a:nth-child(1)
And I wait until service ready
And I click on opinion link with css selector .cx-grid-title
!-- Unpublish
When I click on upublish link with css selector button.cx-right-nav-btn:nth-child(3)
And I will wait until confirmation dialog element with css selector div.in:nth-child(1) > div:nth-child(1) > div:nth-child(1) will be visible
And I click on Yes button in confirmation dialog element with css selector button.ng-binding:nth-child(1)
!-- Check it unpublished
Then I see opinion tittle with css selector .cx-grid-title is: opinion1_edited2
When I click on Published tab with css selector ul.nav:nth-child(3) > li:nth-child(2) > a:nth-child(1)
And I wait until service ready
Then I see published opinions list with css selector span.ng-binding is: NO DATA FOUND


Scenario:
Remove opinion
Meta:
@name Remove opinion
!-- Remove opinion
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on opinions menu item with css selector .open > ul:nth-child(3) > li:nth-child(2) > a:nth-child(2)
And I wait until service ready
And I click on trash opinion tab with css selector button.btn-link
And I will wait until confirmation dialog element with css selector div.in:nth-child(1) > div:nth-child(1) will be visible
And I click on Yes button with css selector button.ng-binding:nth-child(1)
!-- Check it was removed
And I wait until service ready
Then I see opinions list with css selector span.ng-binding is: NO DATA FOUND

Scenario:
Setup params. Set user to expert
Given Set test param username value from property param.expert.username
Given Set test param password value from property param.expert.password

Scenario: Work with opinions as expert user
GivenStories:
    stories/iproduct/opinion/opinion.story#{name:Create opinion},
    stories/iproduct/opinion/opinion.story#{name:Add note to opinion},
    stories/iproduct/opinion/opinion.story#{name:Edit unpublished opinion},
    stories/iproduct/opinion/opinion.story#{name:Publish opinion},
    stories/iproduct/opinion/opinion.story#{name:Edit published opinion},
    stories/iproduct/opinion/opinion.story#{name:Unpiblish opinion},
    stories/iproduct/opinion/opinion.story#{name:Remove opinion},
Given Do nothing
