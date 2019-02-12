package AutomatedTesting;

import org.junit.runner.RunWith;

import com.relevantcodes.extentreports.ExtentReports;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:" )

public class Runner {

public static ExtentReports report = new ExtentReports(Constants.getReportfilepath()+Constants.getReportfilename());
	
	public static int counter = 0;
	
	public static void main(String[]args) {
		
		
	
	}
}
