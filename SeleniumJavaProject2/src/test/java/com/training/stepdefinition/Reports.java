package com.training.stepdefinition;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.ExtentTest;
import com.training.pageobjects.LoginPage;
import com.training.seleniumutils.SeleniumUtils;

import com.training.testrunner.*;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Reports {
	SeleniumUtils utils = new SeleniumUtils();
	LoginPage lpage = new LoginPage();
    ExtentTest test;
    //String  testCaseName = testcasee";
    String scenarioName ;

    @Before
    public void getScenarioName(Scenario scenario){

        String scenName = scenario.getName();
        this.scenarioName = scenName;
    }
	
	@Given("User login to app")
	public void user_login_to_app() {
		
		 test  = TestRunner.reports.startTest(scenarioName);
		 String urlValue = utils.propDataReader("url");
		 String browserValue  = utils.propDataReader("browser");
	    utils.browserLaunch(browserValue,urlValue, scenarioName);
		System.out.println("=====================hello============================");
	}

	@When("User do some actions")
	public void user_do_some_actions() throws Exception {
	   
		utils.elementClick(utils.driver, lpage.nlink, test, "clicked on button");
	}

	@Then("User verifies the elements presents on homepage")
	public void user_verifies_the_elements_presents_on_homepage() {
	    
		System.out.println("==============done================");
	}

}
