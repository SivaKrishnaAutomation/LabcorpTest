package com.training.hooks;

import com.training.seleniumutils.SeleniumUtils;

public class Test {
	
	public static void main(String [] args) {
		
		SeleniumUtils utils = new SeleniumUtils();
		utils.browserLaunch("chrome", "https://www.selenium.dev/", "testcase1");
		System.out.println("hello");
	}

}
