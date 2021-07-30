package testcases;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import io.appium.java_client.MobileBy;
import util.TestUtil;

public class DashBoardTabTest extends TestBase{
	
	@BeforeMethod      
	public void setUp() throws MalformedURLException, InterruptedException   
	{
		initialization();             
	} 
	
	
	@Test
	public void verifyAccountsinProfileSection() throws InterruptedException
	{
	   driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_profile")).click(); // click on profile
	   
	   String Profile_Name= driver.findElement(By.id("com.uneecops.sapsalesapp:id/txt_user_name")).getText();
	   System.out.println("User name: "+Profile_Name);
	   driver.findElement(By.id("com.uneecops.sapsalesapp:id/switch_account")).click();	// click on switch account
	   String Acc1=driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\"Development_30_04_2020\")"))).getText(); // Another account select		
       String Acc2=driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\"Hana_Branch_Test\")"))).getText();
       System.out.println("Availabe Accounts :"+Acc1+" , "+Acc2);
       driver.navigate().back();
	   String CurreentCompany= driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/text_current_company\")"))).getText();
	   System.out.println("Current company selected is: "+CurreentCompany);
	   
	}
	
	
	@AfterMethod
	public void TearDown(ITestResult testResult) throws IOException, InterruptedException
	    {
	     System.out.println("Ending Session");
	     Thread.sleep(2000);
	     if (testResult.getStatus() == ITestResult.FAILURE)
	     {
	     System.out.println("Test Failed");
	     String FailedTestName= testResult.getMethod().getMethodName();
         System.out.println("Failed TestName: "+FailedTestName);
	     TestUtil.takeScreenshotAtEndOfTest(FailedTestName);
	     }
	    // driver.quit();  	 
	    }
	
	
	DashBoardTabTest()
	{
		
	}
}
