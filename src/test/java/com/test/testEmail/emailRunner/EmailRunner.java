package com.test.testEmail.emailRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(	
		features="src/test/resources/", glue= {"stepDefinition"},
				plugin = { 
		"json:target/cucumber-report.json"}
)
public class EmailRunner extends AbstractTestNGCucumberTests{
	
	@Override
	public Object[][] scenarios(){
		return super.scenarios();
	}
}
