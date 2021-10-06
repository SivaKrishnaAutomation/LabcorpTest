Feature: Login App

@LoginApp
  Scenario: Login application fb
    Given User launches chrome
    When User enters username and password
    Then User verifies the home page
   	And User checks link
   	
  
  @LoginApp
   Scenario: login to redbus app
    Given User launches redbus in chrome
    When User feed username and password
    Then User verifies home page with details 
   	