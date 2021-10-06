Feature: Background Test

Background:

Given User launches chrome browser
When User enters url and clicks on contact link
Then User should verify the home page details 

@Login
Scenario: Contact me

 Given user verifies the logo 
 When User enters firstname and email
 Then User should navigate to ContactMe page
 
 @Login
 Scenario: Contact Details

 Given user checks the elements 
 When User enters message and clicks on send button
 Then User should verify the message displayed
 
 
 