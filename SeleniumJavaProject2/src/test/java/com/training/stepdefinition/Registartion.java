package com.training.stepdefinition;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Registartion {

	WebDriver driver;
	@Given("User launches FB application with url {string}")
	public void user_launches_fb_application_with_url(String url) {

		System.setProperty("webdriver.chrome.driver", "Chrome94//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@When("User enters the details and clicks on signup")
	public void user_enters_the_details_and_clicks_on_signup(DataTable data) throws InterruptedException {


		List<List<String>> ud = data.asLists();
		String fName =  ud.get(1).get(0);
		String lName =  ud.get(1).get(1);
		String email =  ud.get(1).get(2);
		String password =  ud.get(1).get(3);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(fName);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lName);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@name='websubmit']")).click();
	}

	@Then("User should see error message")
	public void user_should_see_error_message() {
		System.out.println("");

	}


}
