package reporting;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenerateTestReport {
	
	
	public static ExtentReports extent;
	public static ExtentSparkReporter htmlreporter;
	public static ExtentTest childtest;
	
	
	@BeforeSuite
	
	public void generateHTMLReport()
	{
		
		try {
			
			long time=System.currentTimeMillis();
			
			String path=System.getProperty("user.dir")+"\\automationreport\\Report"+time+".html";
			htmlreporter=new ExtentSparkReporter(path);
			extent=new ExtentReports();
			extent.attachReporter(htmlreporter);
			
			 htmlreporter.config().setDocumentTitle("identitye2etest WebBased Testing");
			 htmlreporter.config().setReportName("identitye2etest Web Testing Report");
			 htmlreporter.config().setTheme(Theme.STANDARD);
			
			 String osname=System.getProperty("os.name");
			 String username=System.getProperty("user.name");
			 
			 extent.setSystemInfo("System Name", username);
			 extent.setSystemInfo("OS Of SYSTEM", osname);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	@AfterSuite
	public void saveReport()
	{
		
		extent.flush();
		
	}
	
	
	public void createTest(String name)
	{
		
		try {
			childtest=extent.createTest(name);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	
	}
