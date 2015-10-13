Story: Portfolio scenarios

Meta:
@portfolio
@web

Narrative:
In order to work with Portfolio
As a users with advisor, beginer, expert and intermidiates roles
I want to use portfolio menu tab and wizards

Scenario:
Setup params for Portfolio scenarios
GivenStories:
    stories/_set_params.story
Given Do nothing

Scenario: Create portfolio as advisor user using starts page wizard
Meta:
@name Create portfolio as advisor user using starts page wizard
GivenStories:
    stories/login.story#{name:Login with email and password as advisor user}
Given Do nothing



