// BuyYourCarValuationPage.java
package pageDefs;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import reporting.GenerateTestReport;

public class BuyYourCarValuationPage extends GenerateTestReport {
    private WebDriver driver;
    
    // Locators for the registration input, mileage, and search button
    
    @FindBy(id="onetrust-accept-btn-handler")
    private WebElement acceptallcookiesbutton;
    
    @FindBy(name="vehicleReg")
    private WebElement registrationnumbertxtbox;
    
    @FindBy(name="Mileage")
    private WebElement mileagetxtbox;
    
    @FindBy(id="btn-go")
    private WebElement getmycarvaluationbutton;
       
    
    @FindBy(xpath = "(//div[@id='vehicleImage']/div[@class='details-vrm ng-star-inserted'])[2]")
    private List<WebElement> variant_reg_txt;
    
    @FindBy(xpath = "(//div[contains(text(),'Manufacturer:')]/following-sibling::div)[2]")
    private List<WebElement> maketxt;
    
    @FindBy(xpath = "(//div[contains(text(),'Model:')]/following-sibling::div)[2]")
    private List<WebElement> modeltxt;
    
    @FindBy(xpath = "(//div[contains(text(),'Year:')]/following-sibling::div)[2]")
    private List<WebElement> yeartxt;
    
    @FindBy(id="btn-back")
    private WebElement backbutton;

    @FindBy(xpath="//*[@id='wbac-app-container']/div/div/vehicle-registration-check/section[1]/div/div[1]/div/div[1]/h1")
    private WebElement couldNotFindCar;
    

    public BuyYourCarValuationPage(WebDriver driver) {
    
			this.driver=driver;
			PageFactory.initElements(this.driver, this);
		
    }

    public void enterRegistrationNumber(String registrationNumber) {
    	waitforsecond();
    	waitforelementvisibilty(registrationnumbertxtbox);
        registrationnumbertxtbox.clear();
		registrationnumbertxtbox.sendKeys(registrationNumber);
		
		
    }

    public void enterMileage(String mileage) {
    	mileagetxtbox.sendKeys(mileage);
    }

    public void clickonSearch() {
        getmycarvaluationbutton.click();
    }
    
    public void acceptAllCookies() {
    	acceptallcookiesbutton.click();
    }

    public void clickonBackButton() {
    	backbutton.click();
    }

    public void refreshPage() {
        driver.navigate().back();
        driver.navigate().refresh();
    }

    public String getValuationResult() {
    	
    	String result="";
    	for(int i=0;i<variant_reg_txt.size();i++)
    	{
    		String variant_reg=variant_reg_txt.get(i).getText().trim();
    		String make=maketxt.get(i).getText().trim();
    		String model=modeltxt.get(i).getText().trim();
    		String year=yeartxt.get(i).getText().trim();
    		List<String> stringList = Arrays.asList(variant_reg, make,model,year);
            // Convert list to string with a comma separator
             result = String.join(",", stringList);
    	}
        return result;
    }
    
    
    public void waitforelementvisibilty(WebElement element)
    {
    	
    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
    	wait.until(ExpectedConditions.visibilityOf(element));
    	
    }
    
    public void waitforsecond()
    {
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    
    public void validatesResults(String actualValuation,String expectedValuation,String regNumber)
    {
    	
    	
    	SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualValuation.toString(), expectedValuation,"Mismatch for vehicle: " + regNumber);
       
        if(actualValuation.equals(expectedValuation))
        {
        	childtest.log(Status.PASS," Actual value ==> "+actualValuation+" Expected Value ==>"+expectedValuation+" Matched for vehicle: " + regNumber);
	
        }
        else
        {
        	childtest.log(Status.FAIL,"Actual value ==> "+actualValuation+" Expected value ==>"+expectedValuation+" Mismatch for vehicle: " + regNumber);

        	
        }
        
    }

    public boolean CheckIfError() throws InterruptedException {
        Thread.sleep(3000);
        if(driver.getPageSource().contains("Sorry, we couldn't find your car")){
            childtest.log(Status.FAIL, "Registration Number not found on website");
            return true;
        }
        return false;
    }

}
    

