Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal



Scenario:
Setup params. Set user to expert
Meta:
Given Set test param username value from property param.expert.username
Given Set test param password value from property param.expert.password

Scenario: Work with newsletters as expert user
GivenStories:
    stories/02_iproducts_newsletter.story#{name:Create newsletter},
    stories/02_iproducts_newsletter.story#{name:Edit newsletter},
    stories/02_iproducts_newsletter.story#{name:Remove newsletter}
Given Do nothing


