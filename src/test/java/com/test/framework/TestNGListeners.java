package com.test.framework;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestNGListeners implements ITestListener{
	
	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("starting test "+result.getMethod().getMethodName().replace("_", " "), true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("Testcase: Pass "+result.getMethod().getMethodName().replace("_", " "), true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("staring Fail "+result.getMethod().getMethodName().replace("_", " "), true);
		
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("Skipped "+result.getMethod().getMethodName().replace("_", " "), true);	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		Reporter.log("starting test suit: "+context.getName(), true);
	}

	@Override
	public void onFinish(ITestContext context) {
		Reporter.log("End test suit: "+context.getName(), true);
	}

}