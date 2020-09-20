package com.test.testEmail.emailRunner;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.gherkin.model.Feature;
import static report.BaseUtils.features;

import report.ExtentReportUtils;

public class NGTestListener implements ITestListener {

	ExtentReportUtils extentReportUtils=new ExtentReportUtils();
	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test failed");
	}

	public void onTestSkipped(ITestResult result) {	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {		
	}

	public void onStart(ITestContext context) {
		System.out.println("Test Start");
		extentReportUtils.ExtentReport();
		features=extentReportUtils.extent.createTest(Feature.class,"test email feature");
		
	}

	public void onFinish(ITestContext context) {
		System.out.println("onFinish");
		extentReportUtils.flushReport();
		
	}

}
