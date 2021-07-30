package testcases;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.TestBase;
import io.appium.java_client.MobileBy;
import util.TestUtil;

public class ReceivableTab extends TestBase {
	
	
	@BeforeMethod      
	public void setUp() throws MalformedURLException, InterruptedException   
	{
		initialization();             
	} 
	
	
	@Test
	public void VerifySalesPersonNameInReceivableSection() throws InterruptedException
	{
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_profile")).click(); // click on profile   
    String actualSalesPersonName =driver.findElement(By.id("com.uneecops.sapsalesapp:id/txt_user_name")).getText();
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click();
    System.out.println("Sales person name in Profile Section: "+actualSalesPersonName);
    Thread.sleep(1000);
	driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tvTargetName\")"))).click();
    driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[2]/android.widget.TextView")).click(); 
    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Receivables\")"))).click();
    String SalesPersonNameInReceivableTab= driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_sales_person")).getText();
    System.out.println("Sales person name in Receivables Section: "+SalesPersonNameInReceivableTab);
    Assert.assertTrue(SalesPersonNameInReceivableTab.equalsIgnoreCase(actualSalesPersonName), "Name Mismatch!");    
	}
	
	
	@Test
	public void VerifyNumberOfInvoicesinReceivableSection()
	{
		System.out.println("Verifying Number of invoices in DashBoard OverDue Original section and Receivables Section");
 //   	driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tvTargetName\")"))).click();
  //   	System.out.println("Selecting individual profile 'ME' in DashBoard Section");
 //       driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[2]/android.widget.TextView")).click();
		String NoOfinvoicesInOverDueOriginal=driver.findElement(By.id("com.uneecops.sapsalesapp:id/txt_overdue_invoices_count")).getText();
		System.out.print("Invoice count in Overdue original section: "+NoOfinvoicesInOverDueOriginal);
		String NoOfinvoicesInOverDueOriginalTrim=NoOfinvoicesInOverDueOriginal.replaceAll("[^\\d]","").trim(); // Replacing every non-digit number with NULL.
		System.out.println(", i.e: "+NoOfinvoicesInOverDueOriginalTrim);
		int NoOfInvoicesInOverDueOriginalNumber=Integer.parseInt(NoOfinvoicesInOverDueOriginalTrim);
		
		
		System.out.println("Going to Receivables section");
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/lin_original")).click();
		String ReceivablesSectionTitle=driver.findElement(By.id("com.uneecops.sapsalesapp:id/page_title")).getText();
		Assert.assertTrue(ReceivablesSectionTitle.equalsIgnoreCase("Receivables"),"We are not in Receivables section");
		System.out.println("We are in receivable Section now...");
		List<WebElement> listOfElements = driver.findElements(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Invoice No.\")")));	
		int NoOfInvoiceCountInReceivablesSection=listOfElements.size();
    	System.out.println("Number of Invoice in Receivables section: "+NoOfInvoiceCountInReceivablesSection);
		
    	
		if(NoOfInvoicesInOverDueOriginalNumber==NoOfInvoiceCountInReceivablesSection)
		{System.out.println("Invoice count are equal in both section");}
		else{System.out.println("Invoice count are not equal");}

        

		
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
   // driver.quit();  	 
    }
 
    ReceivableTab()
	{
		
	}
	
	
	
	
	
	
	

}
