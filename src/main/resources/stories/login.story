Story: Login scenarios

Meta:
@login
@web

Narrative:
In order to work with Service Login
As a users with advisor, beginer, expert and intermidiates roles
I want to use login form

Scenario:
Setup params for Login scenarios
GivenStories:
    stories/_set_params.story
Given Do nothing

Scenario:
Login with email and password as advisor user
Meta:
@name Login with email and password as advisor user
Given Browser ready
When I make login with user %{username} and password %{password}
Then The login is successful

