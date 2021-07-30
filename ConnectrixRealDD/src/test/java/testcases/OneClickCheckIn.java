package testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.TestBase;
import io.appium.java_client.MobileBy;
import util.TestUtil;

public class OneClickCheckIn extends TestBase {
	
	@BeforeMethod
	public void setUp() throws MalformedURLException, InterruptedException   
	{
		initialization();             
	} 
	
	@Test(priority=1,enabled=true,dataProvider="getDataForAccount")
	public void CreateAccCheckIn(String AccName,String ContactP, String Designation, String Mobile, String Email,String Source,String NatureOfIndustry,String TurnOver,String AccountOverview,String OpportunityType, String CheckInType,String Oppn_Name,String Branch,String OppOwner,String Amount,String WONNatureOfIndustry,String Rating,String Comment) throws InterruptedException
	{
            System.out.println("Creating CheckIn");
		    System.out.println("Creating New Account for CheckIn: "+AccName);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
		    System.out.println("Entered in Account Section");
		    Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_check_in")).click() ;   // check in button.
			Thread.sleep(500);
			System.out.println("Adding new account");
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_add_new_account")).click();    // add new account
			Thread.sleep(500);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Enter Account Name\")"))).sendKeys(AccName);
			System.out.println("Account name entered: "+AccName);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Enter Contact Person Name\")"))).sendKeys(ContactP);
			System.out.println("Contact name entered: "+ContactP);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Enter Designation\")"))).sendKeys(Designation);
			System.out.println("Designation name entered: "+Designation);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Enter Mobile No.\")"))).sendKeys(Mobile); 
			System.out.println("Mobile Number entered: "+Mobile);		
			System.out.println("Scrolling Down until Source");
		    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"SOURCE *\"))").click();	        
		    System.out.println("Scrolling Down finished");         		
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_owner\")"))).click();
		    driver.findElement(By.className("android.widget.RadioButton")).click();
		    System.out.println("Default owner eneterd");
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_apply")).click();		
			Thread.sleep(500);	
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Enter Email Address\")"))).sendKeys(Email);
			Thread.sleep(500);	
			System.out.println("Email entered: "+Email);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select Source\")"))).click();
			Thread.sleep(500);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+Source+"\")"))).click(); 
			Thread.sleep(500);
			System.out.println("Source name entered: "+Source);
			
		//**************************************Default Mandatory field entered******************************************************//		    
		//*************************************Non Mandatory Field*****************************************************************//
		    
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			long startTime = System.currentTimeMillis(); 
			   
		   boolean a= driver.findElements(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Pharmaceutical\")"))).size()>0;
		   if(a==true) {  
		   driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Pharmaceutical\")"))).click();
		   Thread.sleep(500);
		   driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\""+NatureOfIndustry+"\")"))).click();
		   System.out.println("Nature of industry selected: "+NatureOfIndustry);
		   } else {System.out.println("Nature of industry Not present in Application: "+NatureOfIndustry);}

			System.out.println("Trying to Scroll Down until Turnover");
			boolean Scroll1= driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"TURNOVER\"))").size()>0;
			if(Scroll1==true)	
			{
			System.out.println("Scrolling down now...");
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"TURNOVER\"))").click();		
			System.out.println("Scrolling Down finished");
			}else {System.out.println("Can not find Turnover for scroll down");}
		   
		   boolean b=  driver.findElements(By.id("com.uneecops.sapsalesapp:id/et_field_e")).size()>0;
		   if(b==true) {
	           driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_field_e")).sendKeys(AccountOverview);
		       System.out.println("Account Overview entered: "+AccountOverview);
		   }else {System.out.println("Account Overview not present in Application: "+AccountOverview);}

		   
		   boolean c=  driver.findElements(By.id("com.uneecops.sapsalesapp:id/et_field_f")).size()>0;
		    if(c==true) {
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_field_f")).sendKeys(TurnOver); 
		    System.out.println("Turnover entered: "+TurnOver);
		    }else {System.out.println("Turnover not present in Application: "+TurnOver);}
		    
		    long endTime = System.currentTimeMillis(); 
			long totTime= endTime-startTime;
			long finalTime= totTime/1000;
			System.out.println("Time Taken in non- mandatory fields: "+finalTime+" Seconds.");
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
               //******************Non-Mandatory Field Entered**************//
	
	        driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_add")).click();	
			System.out.println("Account created: "+AccName);	
			
		    Thread.sleep(2000);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+CheckInType+"\")"))).click();		
			System.out.println("Check in Type Selected: "+CheckInType);
		    Thread.sleep(500);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_add_opportunity")).click();
		    Thread.sleep(500);
		    System.out.println("Adding Opportunity: "+Oppn_Name+" in the account: "+AccName);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_opportunity_name")).sendKeys(Oppn_Name);
		    Thread.sleep(500);
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select opportunity owner from the list\")"))).click();
		    Thread.sleep(500);
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+OppOwner+"\")"))).click();
		    Thread.sleep(500);
		    System.out.println("Opportunity Owner selected: "+OppOwner);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select contact person from the list\")"))).click();
			Thread.sleep(100);
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+ContactP+"\")"))).click();
			Thread.sleep(100);
			System.out.println("Contact person entered: "+ContactP);

			System.out.println("Trying to Scroll Down until Nature of Industry");
			boolean Scroll2= driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NATURE OF \"))").size()>0;
			if(Scroll2==true)	
			{
			System.out.println("Scrolling down now...");
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NATURE OF \"))").click();		
			System.out.println("Scrolling Down finished");
			}else {System.out.println("Can not find Nature of Industry for scroll down");}
		    
			
		    String OppTypeInExcel=OpportunityType.trim().toUpperCase();  // check type of opportunity
		    
		    if(OppTypeInExcel.equalsIgnoreCase("WON"))
		    {
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_won")).click();
		    Thread.sleep(500);
		    System.out.println("WON opportunity selected");	  
		    System.out.println("Selecting Branch");
		    String BranchTrim= Branch.trim().toUpperCase();
		      
		    switch(BranchTrim) 
	        { 
	            case "DELHI": 
	            	driver.findElements(By.id("com.uneecops.sapsalesapp:id/checkbox")).get(0).click(); 	       
	            	System.out.println("Delhi Selected");
	                break; 
	            case "HO": 
	            	driver.findElements(By.id("com.uneecops.sapsalesapp:id/checkbox")).get(1).click(); 
	            	System.out.println("HO Selected");
	                break; 
	            case "MUMBAI": 
	            	driver.findElements(By.id("com.uneecops.sapsalesapp:id/checkbox")).get(2).click(); 
	            	System.out.println("Mumbai Selected");
	                break; 
	            case "NOIDA": 
	            	driver.findElements(By.id("com.uneecops.sapsalesapp:id/checkbox")).get(3).click(); 
	            	System.out.println("Noida Selected");
	                break; 
	                
	            default: 
	            	driver.findElements(By.id("com.uneecops.sapsalesapp:id/checkbox")).get(0).click();  
	            	System.out.println("Default Selected");
	        } 
		    
		    Thread.sleep(500);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();	    
		    }
		    else
		    {
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_open")).click();  // Select open opportunity
		    System.out.println("Open opportunity selected");
		    } 
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_amount")).sendKeys(Amount);
		    System.out.println("Amount  entered: "+Amount);
		    
		    //******************Mandatory Field Entered**************//
    
		    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		    long startTime2 = System.currentTimeMillis(); 	
		    
		    boolean d = driver.findElements(By.id("com.uneecops.sapsalesapp:id/tv_date")).size()>0;
		    if(d==true)
		    {
		    	driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_date")).click();
		    	driver.findElement(By.id("android:id/button1")).click();
				System.out.println("Expected Date of closure  Selected");
		    } else {System.out.println("Expected Date of closure not present");}
		   
		   
		    boolean e =  driver.findElements(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Pharmaceutical\")"))).size()>0;
		    if(e==true)
		    {
		    	driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Pharmaceutical\")"))).click();
		    	Thread.sleep(500);
		    	driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\""+NatureOfIndustry+"\")"))).click();
		    	System.out.println("Nature of Industry Selected: "+NatureOfIndustry);
		    } else {System.out.println("Nature of Industry not present");}
		    
		    long endTime2 = System.currentTimeMillis(); 
			long totTime2= endTime2-startTime2;
			long finalTime2= totTime2/1000;
			System.out.println("Time Taken in non- mandatory fields: "+finalTime2+" Seconds.");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
			
           //******************Non-Mandatory Field Entered**************//

		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_add")).click();
		    System.out.println("WON opportunity created successfully: "+Oppn_Name);
		    Thread.sleep(500);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/dateTV")).click(); // Next Follow up date.
		    Thread.sleep(500);
	        driver.findElement(By.id("android:id/button1")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/rating_bar")).sendKeys(Rating);
		    System.out.println("Rating given: "+Rating);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_comments")).sendKeys(Comment);
		    System.out.println("Comments added for check in");
		    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Save\").instance(0))").click();	    	
		    System.out.println("Check in with opportunity created successfully");
		    Thread.sleep(3000); 
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceIdMatches(\"com.google.android.calendar:id/save\").instance(0)"))).click();
			System.out.println("Calender for Opportunity created");
			 }
    
    @DataProvider
    public Object[][] getDataForAccount()         
    {
    	Object data[][]=TestUtil.getTestData(prop.getProperty("sheetNameForWONCheckin"));
    	return data;
    }
 
    @AfterMethod
    public void TearDown(ITestResult testResult) throws IOException, InterruptedException
    {
     System.out.println("Ending Session");
     Thread.sleep(2000);
     if (testResult.getStatus() == ITestResult.FAILURE)
     {
    	 String FailedTestName= testResult.getMethod().getMethodName();
         System.out.println("Failed TestName: "+FailedTestName);
         TestUtil.takeScreenshotAtEndOfTest(FailedTestName);
     }
     driver.quit();  	 
    }
 
    OneClickCheckIn()
	{
		
	}
	

}
