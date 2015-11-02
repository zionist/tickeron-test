Story: Iproducts chart

Scenario:
Setup params. Set user to advisor
Given Set test param username value from property param.advisor.username
Given Set test param password value from property param.advisor.password

Scenario:
Create chart
Meta:
@name Create chart
GivenStories:
    stories/login/login.story#{name:Login with email and password}
!-- Open charts page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on charts menu item with css selector .open > ul:nth-child(3) > li:nth-child(3) > a:nth-child(2)
And I wait until service ready
!-- Check header and no charts found
Then I see page header with css selector h1.ng-binding is: Charts
Then I see charts list with css selector span.ng-binding is: NO DATA FOUND
!-- Create new
When I click on Create new link with css selector .cx-btn-create-new
!-- Upload file. Add two tickers
And I wait until service ready
And I type chart1 into chart titile with css selector input.ng-valid-maxlength
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
!-- Save chart
When I wait big timeout
And I click on save button with css selector span.pull-right > button:nth-child(2)
And I wait until service ready
!-- Check chart was created
Then I see chart tittle with css selector .cx-subtitle is: chart1
And I see page header with css selector .cx-ma-less-bottom-margin is: Personal Chart
And I see first ticker with css selector span.text-center > span:nth-child(1) is: A
And I see second ticker with css selector span.text-center > span:nth-child(2) > span:nth-child(1) is: AA
When I download chart file file from <a> element with css selector .cx-upload-line > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat.jpeg

Scenario:
Add note to chart
Meta:
@name Add note to chart
!-- Open chart page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on charts menu item with css selector .open > ul:nth-child(3) > li:nth-child(3) > a:nth-child(2)
And I wait until service ready
And I click on chart1 link element with css selector .cx-grid-title
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
Edit unpublished chart
Meta:
@name Edit unpublished chart
!-- Open chart page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on charts menu item with css selector .open > ul:nth-child(3) > li:nth-child(3) > a:nth-child(2)
And I wait until service ready
And I click on chart1 link element with css selector .cx-grid-title
And I wait until service ready
And I click on edit link with css selector button.cx-right-nav-btn:nth-child(1)
!-- Edit page. Download file. Upload new file.
And I wait until service ready
And I type chart1_edited into titile input with css selector input.ng-valid-maxlength
And I download chart file from <a> element with css selector .col-sm-offset-3 > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat.jpeg
When I upload file cat2.jpeg using input element with xpath //input[@type='file']
And I click on remove ticker icon with css selector tr.ng-scope:nth-child(3) > td:nth-child(8) > a:nth-child(1) > i:nth-child(1)
And I click on save button with css selector span.pull-right:nth-child(1) > button:nth-child(2)
And I wait until service ready
!-- Check edit. Download file. Check title
Then I see chart title with css selector .cx-subtitle is: chart1_edited
When I download chart file from <a> element with css selector .cx-upload-line > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat2.jpeg
And I see second ticker with css selector span.text-center > span:nth-child(2) > span:nth-child(1) is not: AA

Scenario:
Publish chart
Meta:
@name Publish chart
!-- Open chart page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on charts menu item with css selector .open > ul:nth-child(3) > li:nth-child(3) > a:nth-child(2)
And I wait until service ready
And I click on chart1 link element with css selector .cx-grid-title
And I wait until service ready
!-- publish chart
When I click on publish link with css selector button.cx-right-nav-btn:nth-child(3)
And I wait until service ready
Then I see page header with css selector .cx-ma-less-bottom-margin is: Publish
!-- Set price and description
When I type 5.00 into $description with css selector input.form-control
And I type chart description into description input with css selector textarea.form-control
!-- Check preview page
And I click on Preview link with css selector .cx-right-nav-btn
And I wait until service ready
Then I see page header is with css selector .cx-ma-less-bottom-margin is: Preview in MALL
And I see opinon title is with css selector .cx-subtitle is: chart1_edited
And I see tickers list on search page with css selector .cx-navigation-list-first-item > div:nth-child(3) > span:nth-child(1) > span:nth-child(1) > span:nth-child(1) is: A
And I see price on search page with css selector div.text-center:nth-child(6) is: $5.00
!-- Publish
When I click on publish button with css selector .cx-right-nav-btn
And I wait until service ready
!-- Check chart is published
Then I see chart tittle with css selector .cx-grid-title is: chart1_edited
And I see tickers list with css selector .label is: A
And I see chart price with css selector div.ng-binding:nth-child(6) is: $5.00
When I download chart file file from <a> element with css selector a.btn
Then Downloaded file is cat2.jpeg

Scenario:
Edit published chart
Meta:
@name Edit published chart
!-- Open chart page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on charts menu item with css selector .open > ul:nth-child(3) > li:nth-child(3) > a:nth-child(2)
And I wait until service ready
And I click on Published tab with css selector ul.nav:nth-child(3) > li:nth-child(2) > a:nth-child(1)
And I wait until service ready
And I click on chart link with css selector .cx-grid-title
!-- Check header
Then I see .cx-ma-less-bottom-margin with css selector .cx-ma-less-bottom-margin is: Published Chart
And I see chart titile with css selector .cx-subtitle is: chart1_edited
And I see chart description with css selector div.col-sm-11:nth-child(2) > span:nth-child(1) is: chart description
!-- Click edit link
When I click on edit link with css selector button.ng-isolate-scope:nth-child(1)
And I wait until service ready
!-- Edit chart. Change title. Remove ticker. Upload new file
And I type chart1_edited2 into $description with css selector input.ng-valid-maxlength
And I download chart file from <a> element with css selector .col-sm-offset-3 > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat2.jpeg
When I upload file cat.jpeg using input element with xpath //input[@type='file']
And I click on remove ticker icon with css selector .fa-trash-o
And I click on save button with css selector span.pull-right:nth-child(1) > button:nth-child(2)
And I wait until service ready
!-- Check edit. Download file. Check title. Check ticker was removed
Then I see chart title with css selector .cx-subtitle is: chart1_edited2
When I download chart file from <a> element with css selector .cx-upload-line > div:nth-child(1) > a:nth-child(1)
Then Downloaded file is cat.jpeg
And I see first ticker with css selector span.text-center > span:nth-child(1) is not: A

Scenario:
Unpublish chart
Meta:
@name Unpiblish chart
!-- Open chart page
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on charts menu item with css selector .open > ul:nth-child(3) > li:nth-child(3) > a:nth-child(2)
And I wait until service ready
And I click on Published tab with css selector ul.nav:nth-child(3) > li:nth-child(2) > a:nth-child(1)
And I wait until service ready
And I click on chart link with css selector .cx-grid-title
!-- Unpublish
When I click on upublish link with css selector button.cx-right-nav-btn:nth-child(3)
And I will wait until confirmation dialog element with css selector div.in:nth-child(1) > div:nth-child(1) > div:nth-child(1) will be visible
And I click on Yes button in confirmation dialog element with css selector button.ng-binding:nth-child(1)
!-- Check it unpublished
Then I see chart tittle with css selector .cx-grid-title is: chart1_edited2
When I click on Published tab with css selector ul.nav:nth-child(3) > li:nth-child(2) > a:nth-child(1)
And I wait until service ready
Then I see published charts list with css selector span.ng-binding is: NO DATA FOUND


Scenario:
Remove chart
Meta:
@name Remove chart
!-- Remove chart
When I click on iproducts menu tab with css selector div.main_menu_item:nth-child(4) > div:nth-child(1) > a:nth-child(1)
When I click on charts menu item with css selector .open > ul:nth-child(3) > li:nth-child(3) > a:nth-child(2)
And I wait until service ready
And I click on trash chart tab with css selector button.btn-link
And I will wait until confirmation dialog element with css selector div.in:nth-child(1) > div:nth-child(1) will be visible
And I click on Yes button with css selector button.ng-binding:nth-child(1)
!-- Check it was removed
And I wait until service ready
Then I see charts list with css selector span.ng-binding is: NO DATA FOUND

Scenario:
Setup params. Set user to expert
Given Set test param username value from property param.expert.username
Given Set test param password value from property param.expert.password

Scenario: Work with charts as expert user
GivenStories:
    stories/iproduct/chart/chart.story#{name:Create chart},
    stories/iproduct/chart/chart.story#{name:Add note to chart},
    stories/iproduct/chart/chart.story#{name:Edit unpublished chart},
    stories/iproduct/chart/chart.story#{name:Publish chart},
    stories/iproduct/chart/chart.story#{name:Edit published chart},
    stories/iproduct/chart/chart.story#{name:Unpiblish chart},
    stories/iproduct/chart/chart.story#{name:Remove chart}
Given Do nothing
