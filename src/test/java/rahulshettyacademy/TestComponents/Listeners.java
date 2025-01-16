package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resource.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{

ExtentTest test;
ExtentReports extent = ExtentReporterNG.getreportObject();

// ThreadLocal to ensure thread-safety in parallel tests
ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

// Called before each test method is invoked
@Override
public void onTestStart(ITestResult result) {
    test = extent.createTest(result.getMethod().getMethodName());
    extentTest.set(test);  // Set the test instance for the current thread
}

// Called if the test is successful
@Override
public void onTestSuccess(ITestResult result) {
    extentTest.get().log(Status.PASS, "Test Passed");
}

// Called if the test fails
@Override
public void onTestFailure(ITestResult result) {
    // Log the failure exception
    extentTest.get().fail(result.getThrowable());
    
    // Capture screenshot of the failed test
    WebDriver driver = getDriver(result);
    try {
        String filepath = getScreenshot(result.getMethod().getMethodName(), driver);
        extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
    } catch (IOException e) {
        e.printStackTrace(); // Log the exception (use logger instead of printStackTrace)
    }
}

// Helper method to get WebDriver instance
private WebDriver getDriver(ITestResult result) {
    WebDriver driver = null;
    try {
        // Access driver from BaseTest or context if available
        driver = (WebDriver) result.getTestContext().getAttribute("driver"); // Example: Assuming driver is set in test context
    } catch (Exception e) {
        e.printStackTrace();
    }
    return driver;
}

// Called after all tests have finished
@Override
public void onFinish(ITestContext context) {
    extent.flush(); // Finalize the report
}

// Uncomment if you need to handle skipped tests
// @Override
// public void onTestSkipped(ITestResult result) {
//    extentTest.get().log(Status.SKIP, "Test Skipped");
// }

// Uncomment if you need to handle tests that fail but within success percentage
// @Override
// public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//    extentTest.get().log(Status.FAIL, "Test Failed but within success percentage");
// }

// Called before any tests start (Optional, if needed)
// @Override
// public void onStart(ITestContext context) {
//    // Can be used to initialize before test suite starts
// }
}
