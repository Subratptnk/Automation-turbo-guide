package subratpattanaik.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject() {
		
		String path =  System.getProperty("user.dir")+"//Reports//index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Extend Report Automation Demo ");
		report.config().setDocumentTitle("Test Results");
		
		ExtentReports rep = new ExtentReports();
		rep.attachReporter(report);
		rep.setSystemInfo("Tester", "Subrat");
		
		return rep;
	}

}
