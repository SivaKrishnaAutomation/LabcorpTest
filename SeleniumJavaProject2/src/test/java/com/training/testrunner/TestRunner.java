package com.training.testrunner;


import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.training.seleniumutils.SeleniumUtils;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features =  {"src/test/resources/FeatureFiles"},
        glue=       {"com.training.stepdefinition","com.training.hooks"},
        tags =      "@Reportstest",
       monochrome = true,
        dryRun = false,
        plugin =    {"json:target/cucumber.json"}

        
        
   
    )
public class TestRunner {

	public static ExtentTest test;
    static String reportPath = SeleniumUtils.makeDir() + File.separator + "CEReport.html";
    public static ExtentReports reports = new ExtentReports(reportPath,true);
    
	@AfterClass
    public static void flushReports() {

        System.out.println("After class");
        reports.flush();
    }
	
}
