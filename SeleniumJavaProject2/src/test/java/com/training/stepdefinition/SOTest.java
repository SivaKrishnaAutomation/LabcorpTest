package com.training.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SOTest {

	@Given("User enters app url")
	public void user_enters_app_url() {
		System.out.println("Given");
	}

	@When("User clicks {string} and {string}")
	public void user_clicks_and(String userId, String password) {

		System.out.println(userId+"===="+password);
	}

	@Then("User says hello")
	public void user_says_hello() {
		System.out.println("Then");
	}


	@Then("User checks link")
	public void user_checks_link() {
		System.out.println("Then2");
	}

}
