package rahulshettyacademy.resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG
{
	
	public static ExtentReports getreportObject()
	{
	String path=System.getProperty("user.dir")+"//reports//index.html";
	ExtentSparkReporter reporter=new ExtentSparkReporter(path);
	reporter.config().setReportName("Web Automation Results");
	reporter.config().setDocumentTitle("Test Results");
	
	
	ExtentReports extent; extent = new ExtentReports();
	 extent.attachReporter(reporter);
	 extent.setSystemInfo("Tester", "Rahul shetty");
	 return extent;
	}
	
	
		
	}


