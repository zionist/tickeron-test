Story: Portfolio scenarios

Meta:
@portfolio
@web

Scenario: Screate portfolio as advisor user
Meta:
GivenStories:
    stories/login.story#{name:Login with email and password as advisor user}
Given I wait for 10000 milliseconds


