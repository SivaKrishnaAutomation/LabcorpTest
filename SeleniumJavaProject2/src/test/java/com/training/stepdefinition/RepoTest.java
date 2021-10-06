package com.training.stepdefinition;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.ExtentTest;
import com.training.seleniumutils.SeleniumUtils;
import com.training.testrunner.TestRunner;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RepoTest {
SeleniumUtils utils = new SeleniumUtils();
	
    ExtentTest test;
    //String  testCaseName = testcasee";
    String scenarioName ;

    @Before
    public void getScenarioName(Scenario scenario){

        String scenName = scenario.getName();
        this.scenarioName = scenName;
    }
	
	@Given("User login to app new file")
	public void user_login_to_app_new_file() {
	    
		 test  = TestRunner.reports.startTest(scenarioName);
		    utils.browserLaunch("chrome", "https://www.selenium.dev/", scenarioName);
			System.out.println("=====================hello============================");
	}

	@When("User do some actions new file")
	public void user_do_some_actions_new_file() throws Exception {
	    
		By nlink = By.xpath("//span[text()='Downloads']");
		utils.elementClick(utils.driver, nlink, test, "clicked on button");
	}

	@Then("User verifies the elements presents on homepage new file")
	public void user_verifies_the_elements_presents_on_homepage_new_file() throws Exception {
	    
		By doc = By.xpath("//span[text()='Documentation']");
		utils.elementClick(utils.driver, doc, test, "clicked on button");
	}

}
