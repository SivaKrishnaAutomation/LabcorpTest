Feature: check Reports

@Reportstest
Scenario: validate the generated report

	Given User login to app
	When User do some actions
	Then User verifies the elements presents on homepage
	
	@Reportstest
Scenario: validate the generated report new file

	Given User login to app new file
	When User do some actions new file
	Then User verifies the elements presents on homepage new file