package com.training.stepdefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TagEx {
	
	WebDriver driver;
	@Given("User launches redbus in chrome")
	public void user_launches_redbus_in_chrome() {
	    
		System.setProperty("webdriver.chrome.driver", "Chrome94//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://chromedriver.chromium.org/");
	}

	@When("User feed username and password")
	public void user_feed_username_and_password() throws InterruptedException {
	    
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//a[text()='Chrome Extensions'])[2]")).click();
		Thread.sleep(4000);
		System.out.println(10/0);
	}

	@Then("User verifies home page with details")
	public void user_verifies_home_page_with_details() throws InterruptedException {
		Thread.sleep(4000);
		String text = driver.findElement(By.xpath("//span[text()='Chrome Extensions']")).getText();
		if(text.contains("Chrome Extensions")) {
			System.out.println("pass");
		}
		else {
			System.out.println("fail");
		}
	}


}
