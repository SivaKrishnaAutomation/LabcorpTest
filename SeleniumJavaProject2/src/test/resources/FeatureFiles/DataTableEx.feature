Feature: Data Table 

@DataTable
 Scenario: Data Table example
 
 	Given User launches FB application with url "https://www.facebook.com/"
 	When User enters the details and clicks on signup
 	
 	|FirstName  |LastName|Mobile     |Password|
 	|sivakrishna|Meni    |76978767556|abc1234 |

 	Then User should see error message
 	