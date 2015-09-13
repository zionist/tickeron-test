Story: Login scenarios

Meta:
@login
@web

Narrative:
In order to work with Service Login
As a users with advisor, beginer, expert and intermidiates roles
I want to use login form

Scenario:
Login with email and password as advisor user
Meta:
@name Login with email and password as advisor user
Given Browser ready
When I make login with user 4@portfoliodirect.com and password 123456789
Then The login is successful

