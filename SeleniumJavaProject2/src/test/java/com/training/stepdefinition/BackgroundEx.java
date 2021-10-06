package com.training.stepdefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BackgroundEx {
	
	WebDriver driver ;
	
	@Given("User launches chrome browser")
	public void user_launches_chrome_browser() {
		System.setProperty("webdriver.chrome.driver", "Chrome94//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	   
	}

	@When("User enters url and clicks on contact link")
	public void user_enters_url_and_clicks_on_contact_link() {
	     
		driver.get("https://www.hyrtutorials.com/p/alertsdemo.html");
		driver.findElement(By.xpath("(//a[text()='Contact'])[2]")).click();
	}

	@Then("User should verify the home page details")
	public void user_should_verify_the_home_page_details() {
	   
		boolean b = driver.findElement(By.xpath("//h1[@class='post-title entry-title']")).getText().contains("Contact Me");
		if(b==true)
			System.out.println("-----------------------Pass-------------------------------");
		else
			System.out.println("-----------------------Fail-------------------------------");
	}

	@Given("user verifies the logo")
	public void user_verifies_the_logo() {
	   
		boolean a = driver.findElement(By.xpath("//img[@title='HYR']")).isDisplayed();
		if(a==true)
			System.out.println("-----------------------Pass-------------------------------");
		else
			System.out.println("-----------------------Fail-------------------------------");
	
	}

	@When("User enters firstname and email")
	public void user_enters_firstname_and_email() {
	    
		driver.findElement(By.xpath("//input[@class='contact-form-name' and @placeholder='Name']")).sendKeys("SivaKrishna");
		driver.findElement(By.xpath("(//input[@id='ContactForm1_contact-form-email'])[1]")).sendKeys("SivaKrishna@1234.com");
	}

	@Then("User should navigate to ContactMe page")
	public void user_should_navigate_to_contact_me_page() {
	    
		System.out.println("Then in scenario1");
	}

	@Given("user checks the elements")
	public void user_checks_the_elements() {
		
		if(driver.findElements(By.xpath("//ul[@id='nav1']/li")).size()==4) {
			
			System.out.println(" elements matched");
		}
	    
	}

	@When("User enters message and clicks on send button")
	public void user_enters_message_and_clicks_on_send_button() {
	    
		System.out.println("clicks on send button");
	}

	@Then("User should verify the message displayed")
	public void user_should_verify_the_message_displayed() {
	    
		System.out.println("Message is displayed");
	}



}
