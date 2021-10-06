package com.training.stepdefinition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SeleniumTest {

	WebDriver driver;
	String currentWindow ;
	@Given("User launches chrome")
	public void user_launches_chrome() {
	  
		System.out.println("Given");
		System.setProperty("webdriver.chrome.driver", "Chrome94//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.salesforce.com/au/");
	}

	@When("User enters username and password")
	public void user_enters_username_and_password() throws InterruptedException {
		System.out.println("When");
		driver.findElement(By.xpath("//span[contains(text(),'Start my free trial')]")).click();
		Thread.sleep(2000);
		 currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		ArrayList<String> al = new ArrayList<String>(windows);
		Iterator<String> itr = windows.iterator();
		
		while(itr.hasNext()) {
			
			String win = itr.next();
			if(!win.equals(currentWindow)) {
				
				driver.switchTo().window(win);
			}
			
		}
	}

	@Then("User verifies the home page")
	public void user_verifies_the_home_page() {
		System.out.println("Then");
		driver.findElement(By.xpath("//input[@name='UserFirstName']")).sendKeys("Siva");
		driver.close();
		driver.switchTo().window(currentWindow);
		String text = driver.findElement(By.xpath("//span[contains(text(), 'We bring companies and customers to')]")).getText();
		System.out.println(text);

	}
	
}
