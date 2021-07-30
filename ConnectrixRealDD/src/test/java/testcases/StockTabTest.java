package testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import util.TestUtil;

public class StockTabTest extends TestBase {
	
	@BeforeMethod      
	public void setUp() throws MalformedURLException, InterruptedException   
	{
		initialization();             
	} 
	
	
	@Test
	public void StockTabFilterVerification(String ItemCode,String ItemName,String ItemGroup)
	{
		
	}
	
	
	
	
    @AfterMethod
    public void TearDown(ITestResult testResult) throws IOException, InterruptedException
    {
     System.out.println("Ending Session");
     Thread.sleep(3000);
     if (testResult.getStatus() == ITestResult.FAILURE)
     {
     System.out.println("Test Failed");
     String FailedTestName= testResult.getMethod().getMethodName();
     System.out.println("Failed TestName: "+FailedTestName);
     TestUtil.takeScreenshotAtEndOfTest(FailedTestName);
     }
    driver.quit();  	 
    }
	
	StockTabTest()
	{
		
	}
}
