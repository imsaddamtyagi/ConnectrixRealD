package testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import base.TestBase;
import io.appium.java_client.MobileBy;
import util.TestUtil;
import org.testng.annotations.Test;

public class AccountTabTest extends TestBase {
	
	@BeforeMethod      
	public void setUp() throws MalformedURLException, InterruptedException   
	{
		initialization();             
	} 
	     
	@Test(priority=1,enabled=true,dataProvider="getDataForAccount") 
	     public void Create_NewAccountConnectrix(String AccName,String ContactP, String Designation, String Mobile, String Email,String Source,String NatureOfIndustry,String TurnOver,String AccountOverview) throws InterruptedException   
	{
	    System.out.println("Creating New Account: "+AccName);
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
	   //**************************************Non Mandatory Field******************************************************************//
		
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
		if(Scroll1==true){
		System.out.println("Scrolling down now");
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
			
	   //***************************Non-Mandatory fields entered**************************************//
	
        driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_add")).click();	
		System.out.println("Account created: "+AccName);	
		Thread.sleep(2000);
	   driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click(); // Going back to account tab 
		 
	}
	
	     
	  @Test(priority=2,enabled=true,dataProvider="getDataForWONOppO") 
	     public void Create_New_WON_Opp(String Acc_Name,String CheckInType,String Oppn_Name,String Branch,String OppOwner,String ContactPerson,String Amount,String NatureOfIndustry,String Rating,String Comment) throws InterruptedException
	  {
		  
		    System.out.println("Creating WON Opportunity: "+Oppn_Name+" in the account: "+Acc_Name);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
			Thread.sleep(1000);
			System.out.println("Eneterd into Account Section");
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();  	
			System.out.println("Filter Account name to check");
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_account_name")).sendKeys(Acc_Name);
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
			Thread.sleep(500);
			System.out.println("Checking if account is present");
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();
			Thread.sleep(500);
			System.out.println("Account exist in the list: "+Acc_Name);
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_check_in")).click();  // check in on already existing account
			Thread.sleep(500);
			System.out.println("Check in on Account");
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+CheckInType+"\")"))).click();		
			System.out.println("Check in Type Selected: "+CheckInType);
		    Thread.sleep(500);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_add_opportunity")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_opportunity_name")).sendKeys(Oppn_Name);
		    Thread.sleep(500);
		    System.out.println("Adding Opportunity: "+Oppn_Name);
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select opportunity owner from the list\")"))).click();
		    Thread.sleep(500);
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+OppOwner+"\")"))).click();
		    Thread.sleep(500);
		    System.out.println("Opportunity Owner selected: "+OppOwner);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select contact person from the list\")"))).click();
			Thread.sleep(100);
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+ContactPerson+"\")"))).click();
			Thread.sleep(100);
			System.out.println("Contact person entered: "+ContactPerson);

			System.out.println("Trying to Scroll Down until Nature of Industry");
			boolean Scroll2= driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NATURE OF \"))").size()>0;
			if(Scroll2==true)	
			{
			System.out.println("Scrolling down now...");
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NATURE OF \"))").click();		
			System.out.println("Scrolling Down finished");
			}else {System.out.println("Can not find Nature of Industry for scroll down");}

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
		      
		   
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_amount")).sendKeys(Amount);
		    System.out.println("Amount  entered: "+Amount);
		    
		    //******************Mandatory Field Entered**************//
		    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			long startTime = System.currentTimeMillis(); 	
		    
		    
		    boolean a = driver.findElements(By.id("com.uneecops.sapsalesapp:id/tv_date")).size()>0;
		    if(a==true)
		    {
		    	driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_date")).click();
		    	driver.findElement(By.id("android:id/button1")).click();
				System.out.println("Expected Date of closure  Selected");
		    } else {System.out.println("Expected Date of closure not present");}
		   
		   
		    boolean b =  driver.findElements(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Pharmaceutical\")"))).size()>0;
		    if(b==true)
		    {
		    	driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Pharmaceutical\")"))).click();
		    	Thread.sleep(500);
		    	driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\""+NatureOfIndustry+"\")"))).click();
		    	System.out.println("Nature of Industry Selected: "+NatureOfIndustry);
		    } else {System.out.println("Nature of Industry not present");}
		    
		    long endTime = System.currentTimeMillis(); 
			long totTime= endTime-startTime;
			long finalTime= totTime/1000;
			System.out.println("Time Taken in non- mandatory fields: "+finalTime+" Seconds.");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
			
			//******************Non-Mandatory Field Entered**************//
		
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_add")).click();
		    System.out.println("WON opportunity created successfully: "+Oppn_Name);
		    Thread.sleep(500);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/dateTV")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("android:id/button1")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/rating_bar")).sendKeys(Rating);
		    System.out.println("Rating given: "+Rating);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_comments")).sendKeys(Comment);
		    System.out.println("Comments added for check in");
		    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Save\").instance(0))").click();	    	
		    System.out.println("Check in with WON opportunity created successfully");
		    Thread.sleep(3000); 
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceIdMatches(\"com.google.android.calendar:id/save\").instance(0)"))).click();
			System.out.println("Calender for WON Opportunity created");
			 
	  }
	 

	  @Test(priority=3,enabled=true,dataProvider="getDataForOPENOppO")
	     public void Create_OPEN_Opp(String Acc_Name,String CheckInType,String Oppn_Name,String OppOwner,String contactPerson,String Amount,String NatureOfIndustry,String Rating,String Comment) throws InterruptedException 
	{
		
		    System.out.println("Creating OPEN Opportunity: "+Oppn_Name+" in the account: "+Acc_Name);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
		    System.out.println("Entered into Account Section");
		    Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();  		
			System.out.println("Filter Account name to check");
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_account_name")).sendKeys(Acc_Name);
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
			System.out.println("Checking if account is present");
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click(); 
			System.out.println("Account exist in the list: "+Acc_Name);
			Thread.sleep(1000);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_check_in")).click();  // check in on already existing account
			System.out.println("Check in on Account");
			Thread.sleep(500);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+CheckInType+"\")"))).click();	
			System.out.println("Check in Type Selected: "+CheckInType);
			Thread.sleep(500);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_add_opportunity")).click();
		    System.out.println("Adding Opportunity: "+Oppn_Name);
		    Thread.sleep(500);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_opportunity_name")).sendKeys(Oppn_Name);
		    Thread.sleep(500);
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select opportunity owner from the list\")"))).click();
		    Thread.sleep(500);
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+OppOwner+"\")"))).click();
		    System.out.println("Opportunity Owner entered: "+Oppn_Name);
		    Thread.sleep(500);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select contact person from the list\")"))).click();
			Thread.sleep(100);
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+contactPerson+"\")"))).click();
			System.out.println("Contact person entered: "+contactPerson);
			Thread.sleep(100);
			
			System.out.println("Trying to Scroll Down until Nature of Industry");
			boolean Scroll2= driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NATURE OF \"))").size()>0;
			if(Scroll2==true){
			System.out.println("Scrolling down now...");
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NATURE OF \"))").click();		
			System.out.println("Scrolling Down finished");
			}else {System.out.println("Can not find Nature of Industry for scroll down");}
		    
			
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_open")).click();  //open opportunity
		    Thread.sleep(100);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_amount")).sendKeys(Amount);
		    System.out.println("Amount entered: "+Amount);
		    Thread.sleep(100);
		    
		    //******************Mandatory Field Entered**************//
		    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		    	   long startTime = System.currentTimeMillis(); 
		    
		    boolean a = driver.findElements(By.id("com.uneecops.sapsalesapp:id/tv_date")).size()>0;  
		    if(a==true)
		    {
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_date")).click();
		    driver.findElement(By.id("android:id/button1")).click();
		    System.out.println("Expected date of closure entered");
		    } else {System.out.println("Expected Date of closure not present");}
		    
		    System.out.println("Entering Nature of Industry");
		    boolean b = driver.findElements(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Pharmaceutical\")"))).size()>0;  
		    if(b==true)
		    {
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Pharmaceutical\")"))).click();
		    Thread.sleep(500);
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\""+NatureOfIndustry+"\")"))).click();
		    System.out.println("Nature of Industry Added: "+NatureOfIndustry);
		    } else {System.out.println("Nature of Industry not present");}
		    
		    long endTime = System.currentTimeMillis(); 
			long totTime= endTime-startTime;
			long finalTime= totTime/1000;
			System.out.println("Time Taken in non- mandatory fields: "+finalTime+" Seconds.");
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	  
                //******************Non-Mandatory Field Entered**************//
	
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_add")).click();	 
		    System.out.println("OPEN Opportunity created successfully: "+Oppn_Name);
		    Thread.sleep(100);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/dateTV")).click(); // follow up date and time in check in
		    Thread.sleep(500);
		    driver.findElement(By.id("android:id/button1")).click();
		    System.out.println("Next follow up Date and Time in Check in Added");
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/rating_bar")).sendKeys(Rating);
		    System.out.println("Rating given: "+Rating);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_comments")).sendKeys(Comment);
		    System.out.println("Comments Added for check in");
		    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Save\").instance(0))").click();	
		    Thread.sleep(500); 	
		    System.out.println("Check In with OPEN Opportunity created successfully");
		    Thread.sleep(3000); 			
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceIdMatches(\"com.google.android.calendar:id/save\").instance(0)"))).click(); 
			System.out.println("Calender for WON Opportunity created");		  
	}

	     
	     @Test(priority=4,enabled=true,dataProvider="getDataForLOSTOppO")  
		 public void Create_NewLostOppo(String Acc_Name,String CheckInType,String Oppn_Name,String OppOwner,String contactPerson,String Reason,String Amount,String NatureOfIndustry,String Comment) throws InterruptedException 
		{
	    	 	
	    	    System.out.println("Creating LOST Opportunity: "+Oppn_Name+" in the account: "+Acc_Name);
		        driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
		        System.out.println("Entered into Account Section");
		        Thread.sleep(500);
				driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click(); 
				System.out.println("Filter Account name to check");
				Thread.sleep(500);
				driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_account_name")).sendKeys(Acc_Name);
				Thread.sleep(500);
				driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
				System.out.println("Checking if account is present");
				Thread.sleep(500);
				driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();  // Check IN button is outside 
				System.out.println("Account exist in the list: "+Acc_Name);
				Thread.sleep(1000);
				driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_check_in")).click();  // check in on already existing account
				Thread.sleep(500);
				System.out.println("Check in on Account");
				driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+CheckInType+"\")"))).click();	
			    Thread.sleep(500);
			    System.out.println("Check in Type Selected: "+CheckInType);
			    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_add_opportunity")).click();
			    Thread.sleep(500);
			    System.out.println("Adding Opportunity: "+Oppn_Name);
			    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_opportunity_name")).sendKeys(Oppn_Name);
			    Thread.sleep(500);
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select opportunity owner from the list\")"))).click();
			    System.out.println("Opportunity Owner selected: "+OppOwner);
			    Thread.sleep(500);
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+OppOwner+"\")"))).click();
				Thread.sleep(500);
				driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select contact person from the list\")"))).click();
				Thread.sleep(100);
				driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+contactPerson+"\")"))).click();		
				System.out.println("Contact person entered: "+contactPerson);
				Thread.sleep(100);
				
				System.out.println("Trying to Scroll Down until Nature of Industry");
				boolean Scroll2= driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NATURE OF \"))").size()>0;
				if(Scroll2==true)	
				{
				System.out.println("Scrolling down now...");
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NATURE OF \"))").click();		
				System.out.println("Scrolling Down finished");
				}else {System.out.println("Can not find Nature of Industry for scroll down");}
			    
			    driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_lost")).click();
			    Thread.sleep(100);
			    System.out.println("Lost opportunity selected from the radio button");
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select a reason from the list\")"))).click();
			    Thread.sleep(100);
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+Reason+"\")"))).click();
			    System.out.println("Added Reason For Lost: "+Reason);
			    Thread.sleep(100);
			    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_amount")).sendKeys(Amount);
			    System.out.println("Amount added:"+Amount);
			    
			 //***************************Mandatory Field Entered******************************************************//

			  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 long startTime = System.currentTimeMillis(); 
			  	   
			    boolean a = driver.findElements(By.id("com.uneecops.sapsalesapp:id/tv_date")).size()>0;  
			    if(a==true)
			    {
			    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_date")).click();
			    driver.findElement(By.id("android:id/button1")).click();
			    System.out.println("Expected date of closure entered");
			    } else {System.out.println("Expected Date of closure not present");}
			    
			
			    boolean b =  driver.findElements(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Pharmaceutical\")"))).size()>0;  
			    if(b==true)
			    {
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Pharmaceutical\")"))).click();
			    Thread.sleep(500);
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\""+NatureOfIndustry+"\")"))).click();
			    System.out.println("Nature of Industry Added: "+NatureOfIndustry);
			    } else {System.out.println("Nature of Industry not present");}   
			    Thread.sleep(500);
			    
			    long endTime = System.currentTimeMillis(); 
				long totTime= endTime-startTime;
				long finalTime= totTime/1000;
				System.out.println("Time Taken in non- mandatory fields: "+finalTime+" Seconds.");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	  
	//******************Non-Mandatory Field Entered**************//
				
			    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_add")).click();   // Lost OPP Added.
			    System.out.println("Lost opportunity created successfully: "+Oppn_Name);
			    Thread.sleep(100);
			    System.out.println("Lost Opportunity added");	 
		  }
		     
	     
	  @Test(priority=5,enabled=true,dataProvider="getDataForOrder")
	     public void Create_OrderFROMWonOppnt(String Acc_Name,String Oppn_Name,String Device,String Quantity,String DiscountPercentage, String PO) throws InterruptedException    
	     {  
		    System.out.println("Placing order on WON Opportunity: "+Oppn_Name+" in the Account: "+Acc_Name);
	 	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	
	 	    System.out.println("Entered into Account Section");
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();  		
			System.out.println("Filter Account name to check");
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_account_name")).sendKeys(Acc_Name); // Enter account name
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
			System.out.println("Checking if account is present");
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();
			System.out.println("Account exist in the list: "+Acc_Name);
			Thread.sleep(500);
			System.out.println("Going to check if opportunity exist in the list");	 
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"OPPORTUNITY\")"))).click();
			Thread.sleep(500);
			System.out.println("Filter Opportunity name to check");
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_opportunity_name")).sendKeys(Oppn_Name);
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_apply")).click();
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_order")).click();
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb")).click();
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();                           
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_name")).sendKeys(Device);
			System.out.println("Product name for order: "+Device);
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnAdd")).click();
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/etQty")).sendKeys(Quantity);
			System.out.println("Quantity  for order: "+Quantity);
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
			Thread.sleep(500);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_cart")).click();
			System.out.println("Added to cart");
			Thread.sleep(500);		
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_add_discount")).click();
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_precentage")).sendKeys(DiscountPercentage);
			System.out.println("Discountn percentage Added: "+DiscountPercentage);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
		    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NOTES\").instance(0))").click();
		    Thread.sleep(2000);	
	     driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_pref_no")).sendKeys(PO);
	     System.out.println("PO number added:"+PO);
	     Thread.sleep(500);	
	     driver.findElement(By.id("com.uneecops.sapsalesapp:id/tvPODate")).click();
	     Thread.sleep(500);	
	     driver.findElement(By.id("android:id/button1")).click();
	     System.out.println("PO Date Added");
	     Thread.sleep(500);	
	     driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_confirm")).click(); // confirm address button
	     Thread.sleep(500);	
	     System.out.println("Changing Billing Address");
	     driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeBillAddress")).click(); 

		    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		    boolean a=   driver.findElements(By.id("com.uneecops.sapsalesapp:id/rb_bill_address")).size()>0;
		    if(a==true) {  
		    	System.out.println("Address present....changing now..");
		    	driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_bill_address")).click();
		 	   } else 
		 	   {
		 		   driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		 		   System.out.println("Need to add addresses to the account....");
		 		   driver.findElement(By.id("com.uneecops.sapsalesapp:id/ivClose")).click();
		 		   Thread.sleep(500);	
		 		   driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click();
		 		   Thread.sleep(1000);
		 		   driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click();
		 		   Thread.sleep(1000);
		 		   driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click();
		 		   driver.findElement(By.id("android:id/button1")).click();
		 		   driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click();  // Fine
		 		   driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
		 		   Thread.sleep(500);
				   System.out.println("Entered into Account Section");
				   driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();  	
					System.out.println("Filter Account name to check");
					Thread.sleep(500);
					driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_account_name")).sendKeys(Acc_Name);
					Thread.sleep(500);
					driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
					Thread.sleep(500);
				    driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();
					Thread.sleep(500);
					System.out.println("Account exist in the list: "+Acc_Name);
					TestUtil.AddBillingShippingAddress();                                        //Adding address
					Thread.sleep(1000);
					driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click(); // Back to the  Dashboard
					
					    driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	
				 	    System.out.println("Entered into Account Section");
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();  		
						System.out.println("Filter Account name to check");
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_account_name")).sendKeys(Acc_Name); // Enter account name
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
						System.out.println("Checking if account is present");
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();
						System.out.println("Account exist in the list: "+Acc_Name);
						Thread.sleep(500);
						System.out.println("Going to check if opportunity exist in the list");	 
						driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"OPPORTUNITY\")"))).click();
						Thread.sleep(500);
						System.out.println("Filter Opportunity name to check");
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_opportunity_name")).sendKeys(Oppn_Name);
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_apply")).click();
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_order")).click();
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb")).click();
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();                           
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_name")).sendKeys(Device);
						System.out.println("Product name for order: "+Device);
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnAdd")).click();
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/etQty")).sendKeys(Quantity);
						System.out.println("Quantity  for order: "+Quantity);
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
						Thread.sleep(500);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_cart")).click();
						System.out.println("Added to cart");
						Thread.sleep(500);		
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_add_discount")).click();
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_precentage")).sendKeys(DiscountPercentage);
						System.out.println("Discount percentage Added: "+DiscountPercentage);
						driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
					    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NOTES\").instance(0))").click();
					    Thread.sleep(2000);	
				     driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_pref_no")).sendKeys(PO);
				     System.out.println("PO number added:"+PO);
				     Thread.sleep(500);	
				     driver.findElement(By.id("com.uneecops.sapsalesapp:id/tvPODate")).click();
				     Thread.sleep(500);	
				     driver.findElement(By.id("android:id/button1")).click();
				     System.out.println("PO Date Added");
				     Thread.sleep(500);	
				     driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_confirm")).click(); // confirm address button
				     Thread.sleep(500);	
				     System.out.println("Changing Billing Address");
				     driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeBillAddress")).click(); 
				     driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_bill_address")).click();
		 	   }
		    
		    
	     System.out.println("Changing Shipping Address");
	     driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeShipAddress")).click();
	     Thread.sleep(500);	
	     driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_ship_address")).click();
	     Thread.sleep(500);
	     System.out.println("Address confirmed");
	     driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_confirm")).click();
	     Thread.sleep(5000);	
	     System.out.println("Order placed successfully");     
	       }
	   

	  
    	  @Test(priority=6,enabled=true,dataProvider="getDataForAccount")
	     public void verifyAccountDetails(String AccName,String ContactP, String Designation, String Mobile, String Email,String Source,String NatureOfIndustry,String TurnOver,String AccountOverview) throws InterruptedException
	        {
    		System.out.println("Verifying Account Details");
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();  		
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_account_name")).sendKeys(AccName);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();
			System.out.println("Account exist in the list");
			Thread.sleep(1000);
			String AccountNameInApp=driver.findElement(By.id("com.uneecops.sapsalesapp:id/page_title")).getText();
			System.out.println("Account name in the App: "+AccountNameInApp);
            driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_edit")).click();  //edit account.
          
            String ContactPersonInApp= driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_person")).getText();
            System.out.println("Contact person in the App: "+ContactPersonInApp);
            String mobileInApp = driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_mobile")).getText();
            System.out.println("Mobile number in the App: "+mobileInApp);
            String emailInApp = driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_email")).getText();
            System.out.println("Email Id in the App: "+emailInApp);
            String DesignationInApp = driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_position")).getText();
            System.out.println("Designation in the App: "+DesignationInApp);
            Thread.sleep(1000);
		         driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_close_bottom_pannel")).click();
			     driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_check_in")).click();		// check in to edit account  
			     driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"ACCOUNT NAME\")"))).click();
			     driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_edit_account")).click(); 
			     System.out.println("Scroll Down unitl Nature Of  Industry");
			     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NATURE OF \").instance(0))").click();    
			     System.out.println("Scroll Down Finished");
			     String OwnerinApp=driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_owner")).getText();		  
		         System.out.println("Owner in app: "+OwnerinApp);
		         String SourceinApp= driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+Source+"\")"))).getText();
		         System.out.println("Source in the App: "+SourceinApp);
		         
		            Assert.assertTrue(AccountNameInApp.equalsIgnoreCase(AccName), "Account Name Mismatch!");   
		            Assert.assertTrue(ContactPersonInApp.equalsIgnoreCase(ContactP), "ContactPerson Mismatch!");  
		            Assert.assertTrue(mobileInApp.equalsIgnoreCase(Mobile), "Mobile number Mismatch!"); 
		            Assert.assertTrue(emailInApp.equalsIgnoreCase(Email), "Email Mismatch!");  
		            Assert.assertTrue(DesignationInApp.equalsIgnoreCase(Designation), "Designation Mismatch!");  
		            Assert.assertTrue(OwnerinApp.equalsIgnoreCase("Mohd Husain Ahmad"), "Owner Mismatch!");  
		            Assert.assertTrue(SourceinApp.equalsIgnoreCase(Source), "Source Mismatch!");   
		    
		          //******************Mandatory Field Entered**************//

		            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		            	   long startTime = System.currentTimeMillis(); 	
		         
		         boolean a= driver.findElements(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+NatureOfIndustry+"\")"))).size()>0;  		      
		         if(a==true){ 
		        	 String	 NatureOfIndustryInApp = driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+NatureOfIndustry+"\")"))).getText();   	 
		        	 System.out.println("Nature of Industry in the App is:"+NatureOfIndustryInApp); 
		        	 Assert.assertTrue(NatureOfIndustryInApp.equalsIgnoreCase(NatureOfIndustry), "Nature of industry Mismatch!"); 
				   } else {System.out.println("Nature of industry Not present in Application: "+NatureOfIndustry);}
		         
		     	System.out.println("Trying to Scroll Down until Turnover");
				boolean Scroll1= driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"TURNOVER\"))").size()>0;
				if(Scroll1==true)	
				{
				System.out.println("Scrolling down now...");
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"TURNOVER\"))").click();		
				System.out.println("Scrolling Down finished");
				}else {System.out.println("Can not find Turnover for scroll down");}
			     
			     boolean b= driver.findElements(By.id("com.uneecops.sapsalesapp:id/et_field_e")).size()>0;  	
			     if(b==true){ 
			    	 String AccountOverViewInApp=driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_field_e")).getText();
                     System.out.println("Account overview in App: "+AccountOverViewInApp);
                     Assert.assertTrue(AccountOverViewInApp.equalsIgnoreCase(AccountOverview), "Account overview Mismatch!"); 
			     }else {System.out.println("Account OverView not present in the  Application: "+AccountOverview);}
			     
			     
			     
			    boolean c= driver.findElements(By.id("com.uneecops.sapsalesapp:id/et_field_f")).size()>0; 
			    if(c==true){ 
			    String TurnOverInapp= driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_field_f")).getText();
                System.out.println("TurnOver in App: "+TurnOverInapp);
                Assert.assertTrue(TurnOverInapp.equalsIgnoreCase(TurnOver),"Turn over Mismatch!"); 
			     }else {System.out.println("TurnOver not present in the  Application: "+TurnOver);}

			    long endTime = System.currentTimeMillis(); 
				long totTime= endTime-startTime;
				long finalTime= totTime/1000;
				System.out.println("Time Taken in non- mandatory fields: "+finalTime+" Seconds.");
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	  
	        //******************Non-Mandatory Field **************//
       
            System.out.println("Account details verified successfully");
            
	  }
   	  
    	 @Test(priority=7,enabled=true,dataProvider="getDataForWONOppO")
    	 public void verifyWONOpportunityDetails(String Acc_Name,String CheckInType,String Oppn_Name,String Branch,String OppOwner,String ContactPerson,String Amount,String NatureOfIndustry,String Rating,String Comment) throws InterruptedException
    	  {
    			    System.out.println("Verifying WON Opportunity Details");    			
    			    driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
    				driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();  		
    				driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_account_name")).sendKeys(Acc_Name);
    				driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
    				driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();
    				System.out.println("Account exist in the list");
    				driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"OPPORTUNITY\")"))).click();
    			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/button8\")"))).click();
	                driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_opportunity_name")).sendKeys(Oppn_Name);
                    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_apply")).click();
    		        driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();
    		        System.out.println("Opportunity is present in the list");
    		        driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_update")).click();
    		        Thread.sleep(1000);
    		        driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
    		        String AccountNameInApp=driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_account_name")).getText();
    		        System.out.println("Account name in the App is:"+AccountNameInApp);
                    String OppNameInApp= driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_opportunity_name")).getText();
                    System.out.println("Opportunity name in the App is:"+OppNameInApp);
                    String OwnerInApp= driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+OppOwner+"\")"))).getText();
                    System.out.println("Owner name in the App is:"+OwnerInApp);
                    String ContactInApp=driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\""+ContactPerson+"\")"))).getText();   
                    System.out.println("Contact in the App is:"+ContactInApp);
                    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NATURE OF \").instance(0))").click();	
                    String AmountInApp= driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_amount")).getText();
                    System.out.println("Amount in the App is:"+AmountInApp);
                    

                  //******************Mandatory Field Entered**************//

                  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                  	   long startTime = System.currentTimeMillis(); 	    
                    
             boolean a= driver.findElements(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+NatureOfIndustry+"\")"))).size()>0;
             if(a==true) { 
             String NatureOfIndustryInApp=driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+NatureOfIndustry+"\")"))).getText();  
             System.out.println("Nature of Industry in the App is:"+NatureOfIndustryInApp);  
             Assert.assertTrue(NatureOfIndustryInApp.equalsIgnoreCase(NatureOfIndustry), "Nature of Industry Mismatch!");  
             }else {System.out.println("Nature of industry not present in Application: "+NatureOfIndustry);}
                 
             
     	            long endTime = System.currentTimeMillis(); 
     				long totTime= endTime-startTime;
     				long finalTime= totTime/1000;
     				System.out.println("Time Taken in non- mandatory fields: "+finalTime+" Seconds.");
     				
     				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	  
     	//******************Non-Mandatory Field **************//
     				
                    Assert.assertTrue(AccountNameInApp.equalsIgnoreCase(Acc_Name), "Account Name Mismatch!");   
                    Assert.assertTrue(OppNameInApp.equalsIgnoreCase(Oppn_Name), "Opportunity Name Mismatch!");   
                    Assert.assertTrue(OwnerInApp.equalsIgnoreCase(OppOwner), "Owner Mismatch!");  
                    Assert.assertTrue(ContactInApp.equalsIgnoreCase(ContactPerson), "Contact Person Mismatch!");  
                    Assert.assertTrue(AmountInApp.equalsIgnoreCase(Amount), "Amount Mismatch!");  
                    
                
                    System.out.println("WON Opportunity details verified successfully");
    		  
    	  
    	  }
	  
	  
	    @Test(priority=8,enabled=true,dataProvider="getDataForOPENOppO")
	     public void verifyOPENOpportunityDetails(String Acc_Name,String CheckInType,String Oppn_Name,String OppOwner,String ContactPerson,String Amount,String NatureOfIndustry,String Rating,String Comment) throws InterruptedException
	     {
	    	 System.out.println("Verifying OPEN Opportunity Details");    			
			    driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
				driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();  		
				driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_account_name")).sendKeys(Acc_Name);
				driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
				driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();
				System.out.println("Account exist in the list");
				driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"OPPORTUNITY\")"))).click();
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/button8\")"))).click();
                driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_opportunity_name")).sendKeys(Oppn_Name);
                driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_apply")).click();
		        driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();
		        System.out.println("Opportunity exist in the list");
		        driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_update")).click();
		        String AccountNameInApp=driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_account_name")).getText();
		        System.out.println("Account Name in App: "+AccountNameInApp);
                String OppNameInApp= driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_opportunity_name")).getText();
                System.out.println("Opportunity Name in App: "+OppNameInApp);
                String OwnerInApp= driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+OppOwner+"\")"))).getText();              
                System.out.println("Owner Name in App: "+OwnerInApp);
                String ContactInApp=driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+ContactPerson+"\")"))).getText();  
                System.out.println("Contact Name in App: "+ContactInApp);
                driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NATURE OF \").instance(0))").click();	
                String AmountInApp= driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_amount")).getText();
                System.out.println("Amount in App: "+AmountInApp);
                
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
           	   long startTime = System.currentTimeMillis(); 	    
             
      boolean a= driver.findElements(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+NatureOfIndustry+"\")"))).size()>0;
      if(a==true) { 
      String NatureOfIndustryInApp=driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+NatureOfIndustry+"\")"))).getText();  
      System.out.println("Nature of Industry in the App is:"+NatureOfIndustryInApp);  
      Assert.assertTrue(NatureOfIndustryInApp.equalsIgnoreCase(NatureOfIndustry), "Nature of Industry Mismatch!");  
      }else {System.out.println("Nature of industry not present in Application: "+NatureOfIndustry);}
          
      
	            long endTime = System.currentTimeMillis(); 
				long totTime= endTime-startTime;
				long finalTime= totTime/1000;
				System.out.println("Time Taken in non- mandatory fields: "+finalTime+" Seconds.");
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	  
                                                   
                Assert.assertTrue(AccountNameInApp.equalsIgnoreCase(Acc_Name), "Account Name Mismatch!");   
                Assert.assertTrue(OppNameInApp.equalsIgnoreCase(Oppn_Name), "Opportunity Name Mismatch!");   
                Assert.assertTrue(OwnerInApp.equalsIgnoreCase(OppOwner), "Owner Mismatch!");  
                Assert.assertTrue(ContactInApp.equalsIgnoreCase(ContactPerson), "Contact Person Mismatch!");  
                Assert.assertTrue(AmountInApp.equalsIgnoreCase(Amount), "Amount Mismatch!");  

               System.out.println("OPEN Opportunity details verified successfully");
			  
	       }
	  
	  
	    @Test(priority=9,enabled=true,dataProvider="getDataForLOSTOppO")
	     public void verifyLOSTOpportunityDetails(String Acc_Name,String CheckInType,String Oppn_Name,String OppOwner,String ContactPerson,String Reason,String Amount,String NatureOFIndustry,String Comment)
	    {
		    System.out.println("Verifying LOST Opportunity Details");    			
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();  		
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_account_name")).sendKeys(Acc_Name);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();
			System.out.println("Account exist in the List");
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"OPPORTUNITY\")"))).click();
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/button8\")"))).click();
            driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_opportunity_name")).sendKeys(Oppn_Name);
            driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_apply")).click();
            driver.findElement(By.id("com.uneecops.sapsalesapp:id/img_arrow")).click();
            System.out.println("Opportunity is present in the list");

			  String AccountNameInApp=driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+Acc_Name+"\")"))).getText(); 
			  System.out.println("AccountNameInApp: "+AccountNameInApp);
			  String OppNameInApp = driver.findElement(By.id("com.uneecops.sapsalesapp:id/page_title")).getText();	
              System.out.println("OppNameInApp: "+OppNameInApp);
			  String OwnerInApp= driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+OppOwner+"\")"))).getText();
			  System.out.println("OwnerInApp: "+OwnerInApp);
			  String ContactInApp= driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\""+ContactPerson+"\")"))).getText();
			  System.out.println("ContactInApp: "+ContactInApp);
			  String ReasonInApp= driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+Reason+"\")"))).getText(); 
			  System.out.println("ReasonInApp: "+ReasonInApp);
			  String AmountInApp= driver.findElement(By.xpath ("//android.widget.TextView[@bounds='[540,754][988,806]']")).getText();
			  String AmountInt = StringUtils.substringBefore(AmountInApp, "."); //Taking String before '.'to remove float values. 
			  String AmountTrim= AmountInt.replaceAll("[^\\d]","").trim(); // Replace values that are not Digits.
			  System.out.print("AmountInApp: "+AmountInApp);
			  System.out.println(",That is:"+AmountTrim);
			  System.out.println();
			  Assert.assertTrue(AccountNameInApp.equalsIgnoreCase(Acc_Name),"Account Name mismatched");
			  Assert.assertTrue(OppNameInApp.equalsIgnoreCase(Oppn_Name),"Opportunity Name mismatched");			  
			  Assert.assertTrue(OwnerInApp.equalsIgnoreCase(OppOwner+"  "),"Owner Name mismatched");
			  Assert.assertTrue(ContactInApp.equalsIgnoreCase(ContactPerson),"Contact Name mismatched");
			  Assert.assertTrue(ReasonInApp.equalsIgnoreCase(Reason),"Reason mismatched");
			  Assert.assertTrue(AmountTrim.equalsIgnoreCase(Amount),"Amount mismatched");
			  System.out.println("Below are the Details in the App:");
			  System.out.println("Account Name In App: "+AccountNameInApp+","+"Opportunity Name In App: "+OppNameInApp+","+"Owner Name In App: "+OwnerInApp);
			  System.out.println("Contact Name In App: "+ContactInApp+","+"Amount in App: "+AmountTrim+","+"Reason In App: "+ReasonInApp);
			  System.out.println();
			  System.out.println("Lost Opportunity details verified successfully");
			 
		   
	      }
	    
	    
	    
	 
	    
	    
    @DataProvider
    public Object[][] getDataForAccount()         
    {
    	Object data[][]=TestUtil.getTestData(prop.getProperty("sheetNameForAccount"));
    	return data;
    }
   
    @DataProvider
    public Object[][] getDataForWONOppO()        
    {
    	Object data[][]=TestUtil.getTestData(prop.getProperty("sheetNameForWON"));
    	return data;
    }
    
    @DataProvider
    public Object[][] getDataForOPENOppO()      
    {
    	Object data[][]=TestUtil.getTestData(prop.getProperty("sheetNameForOPEN"));
    	return data;
    }
    
    @DataProvider
    public Object[][] getDataForLOSTOppO()      
    {
    	Object data[][]=TestUtil.getTestData(prop.getProperty("sheetNameForLost"));
    	return data;
    }
    
    @DataProvider
    public Object[][] getDataForOrder()          
    {
    	Object data[][]=TestUtil.getTestData(prop.getProperty("sheetNameForOrder"));
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
 
	AccountTabTest()
	{
		
	}
	
	
}
