// CarValuationTest.java
package tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageDefs.BuyYourCarValuationPage;
import utilities.ReadTextFile;
import reporting.GenerateTestReport;

public class BuyYourCarValuationTest extends GenerateTestReport {
	private WebDriver driver;
	private List<String> registrationdetails;
	private List<String> expectedValuationsData;

	@BeforeClass
	public void setUp() throws IOException {
		createTest("Setup");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.webuyanycar.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		BuyYourCarValuationPage buyYourCarValuationPage = new BuyYourCarValuationPage(driver);
		buyYourCarValuationPage.acceptAllCookies();
		String path=System.getProperty("user.dir")+ File.separator + "files" + File.separator;
		registrationdetails = ReadTextFile.extractCarRegistrationNumbers(path+"car_input V4.txt");
		expectedValuationsData = ReadTextFile.readExpectedOutput(path+"car_output V4.txt");
	}

	@Test
	public void testCarValuations() throws InterruptedException {
		createTest("testCarValuations");
		BuyYourCarValuationPage buyYourCarValuationPage = new BuyYourCarValuationPage(driver);

		for (int i = 0; i < registrationdetails.size(); i++) {
			String regNumber = registrationdetails.get(i);
 			String expectedValuation = expectedValuationsData.get(i+1);

			buyYourCarValuationPage.enterRegistrationNumber(regNumber);
			buyYourCarValuationPage.enterMileage("58000"); // Example mileage
			buyYourCarValuationPage.clickonSearch();
			if(buyYourCarValuationPage.CheckIfError()) {
				buyYourCarValuationPage.refreshPage();
			}
			else{
				String actualValuation = buyYourCarValuationPage.getValuationResult();
				buyYourCarValuationPage.validatesResults(actualValuation, expectedValuation, actualValuation.split(",")[0]);
				buyYourCarValuationPage.clickonBackButton();
			}
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
