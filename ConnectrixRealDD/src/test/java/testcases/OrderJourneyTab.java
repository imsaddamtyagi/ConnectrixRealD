package testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.TestBase;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import util.TestUtil;


public class OrderJourneyTab extends TestBase {
	
	
	@BeforeMethod      
	public void setUp() throws MalformedURLException, InterruptedException   
	{
		initialization();             
	} 

	 
	@Test(enabled=true,priority=1,dataProvider="getDataForNewPOUploadOrder")
	public void AddNewPOUploadPurchaseOrder(String Acc_Name,String Branch,String PO_Number,String comment) throws InterruptedException
	{
		System.out.println("Adding New Purchase Order:Upload New Purchase Order");
		driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Order Journey\")"))).click();
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/relNewOrder\")"))).click();
	    System.out.println("Upload Purchase order");
	    MobileElement mobileElement=(MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/atvCustomer_chat_list\")")));	
	    mobileElement.click();
	    String args = Acc_Name;  
	    int index = args.length();
	    String str = args.substring(0,index-1);
	    mobileElement.sendKeys(str);
	    WebDriverWait wait = new WebDriverWait(driver,10);
	    WebElement ActualAcc=  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]\r\n"+ "")));
	    ActualAcc.click();
	    System.out.println("Entered Account Name: "+Acc_Name);
	    Thread.sleep(5000);
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\")"))).click();
	    System.out.println("Selected Contact Name");
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\").instance(1)"))).click();
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/rbUpload\")"))).click();
	    driver.navigate().back();
	    Thread.sleep(1000);
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
	    String BranchTrim = Branch.trim(); // Branch to uppercase.
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+BranchTrim+"\")"))).click();
	    System.out.println("Branch Selected: "+BranchTrim);
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/edit_text_Guid_as_ponumber\")"))).sendKeys(PO_Number);
	    System.out.println("POnumber entered: "+PO_Number);
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/edit_text_po_date\")"))).click();
	    Thread.sleep(500);	
	    driver.findElement(By.id("android:id/button1")).click();
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
	 		  Thread.sleep(1000);
	 		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click();
	 		 Thread.sleep(1000);
	 		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
	 		 Thread.sleep(1000);
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
				driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Order Journey\")"))).click();
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/relNewOrder\")"))).click();
			    System.out.println("Upload Purchase order");
			    MobileElement mobileElement1=(MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/atvCustomer_chat_list\")")));	
			    mobileElement1.click();
			    String args1 = Acc_Name;  
			    int index1 = args1.length();
			    String str1 = args.substring(0, index1-1);
			    mobileElement.sendKeys(str1);
			    WebDriverWait wait1 = new WebDriverWait(driver,10);
			    WebElement ActualAcc1=  wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]\r\n"+ "")));
			    ActualAcc1.click();
			    System.out.println("Entered Account Name: "+Acc_Name);
			    Thread.sleep(4000);
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\")"))).click();
			    System.out.println("Selected Contact Name");
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\").instance(1)"))).click();
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/rbUpload\")"))).click();
			    driver.navigate().back();
			    Thread.sleep(1000);
			    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
			    String BranchTrim1 = Branch.trim(); // Branch to uppercase.
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+BranchTrim1+"\")"))).click();
			    System.out.println("Branch Selected: "+BranchTrim);
			    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/edit_text_Guid_as_ponumber\")"))).sendKeys(PO_Number);
			    System.out.println("POnumber entered: "+PO_Number);
			    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/edit_text_po_date\")"))).click();
			    Thread.sleep(500);	
			    driver.findElement(By.id("android:id/button1")).click();
			    System.out.println("Changing Billing Address");
			    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeBillAddress")).click(); 
				driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_bill_address")).click();
 		   }
	    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	    System.out.println("Changing Shipping Address");
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeShipAddress")).click();
	    Thread.sleep(500);	
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_ship_address")).click();
	    Thread.sleep(500);	
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/edit_text_order_description")).sendKeys(comment);
	    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Submit\").instance(0))").click();
	    System.out.println("Uploading Attcahment");
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/frameUpload\")"))).click();
        driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_gallery")).click();       
        WebDriverWait wait1 = new WebDriverWait(driver,40);
		wait1.until(ExpectedConditions.alertIsPresent()); 
		Alert alert1 =  driver.switchTo().alert();
		alert1.accept();		  
	    driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout[1]\r\n"		  		+ "")).click();
		driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.FrameLayout/android.widget.TextView\r\n"		  		+ "")).click();       
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Submit\").instance(0))").click();
		Thread.sleep(3000);
		System.out.println("Order is Created by Upload Purchase Order");
	}	
	
	
	@Test(priority=2,enabled=true,dataProvider="getDataForNewPOSelectFromItems")  // New Purchase Order-> Select From Items
	public void AddNewPOSelectFromItems(String Acc_Name,String Branch,String Item_Name,String quantity,String DiscountPercentage,String PO_Number,String notes) throws InterruptedException
	{
	System.out.println("Adding New Purchase Order:Select From Items");
	driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Order Journey\")"))).click();
    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/relNewOrder\")"))).click();
    MobileElement mobileElement=   (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/atvCustomer_chat_list\")")));	    
    mobileElement.click();
    String args = Acc_Name;  
    int index = args.length();
    String str = args.substring(0, index-1);
    mobileElement.sendKeys(str);
    WebDriverWait wait = new WebDriverWait(driver,10);
    WebElement ActualAcc=  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]\r\n"+ "")));
    ActualAcc.click();
    Thread.sleep(4000);
    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\")"))).click();
    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\").instance(1)"))).click();
    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/rbSelect\")"))).click();
    driver.navigate().back();
    Thread.sleep(1000);
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
    String BranchTrim = Branch.trim(); // Branch to uppercase.
    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+BranchTrim+"\")"))).click();
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();  
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();      
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_name")).sendKeys(Item_Name); // Filter item name Dell002
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnAdd")).click();
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/etQty")).sendKeys(quantity);
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_cart")).click();
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_add_discount")).click();
	driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_precentage")).sendKeys(DiscountPercentage);
	driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NOTES\").instance(0))").click();
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_pref_no")).sendKeys(PO_Number); //PO Number
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tvPODate")).click();
    driver.findElement(By.id("android:id/button1")).click();
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_notes")).sendKeys(notes);
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_confirm")).click(); // confirm address button
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeBillAddress")).click(); 
    Thread.sleep(2000);
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
 		  Thread.sleep(1000);	
 		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click();
 		  Thread.sleep(1000);
 		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click(); // back cart
 		 Thread.sleep(1000);
 		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click();
 		  driver.findElement(By.id("android:id/button1")).click();
 		 Thread.sleep(1000);
 	     driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
 		 Thread.sleep(1000);
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
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click(); // Back to the  Dashboard
			System.out.println("Adding New Purchase Order:Select From Items");
			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Order Journey\")"))).click();
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/relNewOrder\")"))).click();
		    MobileElement mobileElement1=   (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/atvCustomer_chat_list\")")));	    
		    mobileElement1.click();
		    String args1 = Acc_Name;  
		    int index1 = args1.length();
		    String str1 = args1.substring(0, index1-1);
		    mobileElement.sendKeys(str1);
		    WebDriverWait wait1 = new WebDriverWait(driver,10);
		    WebElement ActualAcc1=  wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]\r\n"+ "")));
		    ActualAcc1.click();
		    Thread.sleep(4000);
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\")"))).click();
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\").instance(1)"))).click();
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/rbSelect\")"))).click();
		    driver.navigate().back();
		    Thread.sleep(1000);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
		    String BranchTrim1 = Branch.trim(); // Branch to uppercase.
		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+BranchTrim1+"\")"))).click();
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();  
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();      
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_name")).sendKeys(Item_Name); // Filter item name Dell002
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnAdd")).click();
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/etQty")).sendKeys(quantity);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_cart")).click();
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_add_discount")).click();
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_precentage")).sendKeys(DiscountPercentage);
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
		    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NOTES\").instance(0))").click();
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_pref_no")).sendKeys(PO_Number); //PO Number
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tvPODate")).click();
		    driver.findElement(By.id("android:id/button1")).click();
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_notes")).sendKeys(notes);
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_confirm")).click(); // confirm address button
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeBillAddress")).click(); 
		    System.out.println("Changing Billing Address");
		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeBillAddress")).click(); 
			driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_bill_address")).click();
		   }
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    
  
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeShipAddress")).click();
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_ship_address")).click();
    Thread.sleep(5000);	
    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_confirm")).click();
    Thread.sleep(5000);
    System.out.println("Order is Created By Select From Items");  
		  }
	
	
	@Test(enabled=true,priority=3,dataProvider="getDataForNewPOGeneratePInvoice")	
	public void AddNewPoGenerateProformaInvoice(String Acc_Name,String Branch,String Item_Name,String quantity,String DiscountPercentage,String PO_Number,String Notes) throws InterruptedException
	{
		System.out.println("Adding New Purchase Order:Generate Proforma Invoice");
		driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Order Journey\")"))).click();
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/relNewOrder\")"))).click();
	    MobileElement mobileElement=(MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/atvCustomer_chat_list\")")));	
	    mobileElement.click();
	    String args = Acc_Name;  
	    int index = args.length();
	    String str = args.substring(0, index-1);
	    mobileElement.sendKeys(str);
	    WebDriverWait wait = new WebDriverWait(driver,10);
	    WebElement ActualAcc=  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]\r\n"+ "")));
	    ActualAcc.click();
	    Thread.sleep(4000);
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\")"))).click();
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\").instance(1)"))).click();
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/rbGeneratePi")).click();
	    driver.navigate().back();
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
	    String BranchTrim = Branch.trim(); // Branch to uppercase.
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+BranchTrim+"\")"))).click();
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();      
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_name")).sendKeys(Item_Name); // Filter item name Dell002
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnAdd")).click();
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/etQty")).sendKeys(quantity);
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_cart")).click();
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_add_discount")).click();
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_precentage")).sendKeys(DiscountPercentage);
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
	    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Confirm Address\").instance(0))").click();
	    Thread.sleep(4000);	
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_pref_no")).sendKeys(PO_Number);
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tvPODate")).click();
	    Thread.sleep(2000);	
	    driver.findElement(By.id("android:id/button1")).click();
	    Thread.sleep(500);
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_notes")).sendKeys(Notes);
	    Thread.sleep(2000);	
	    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/frameUpload\")"))).click();
	    WebDriverWait wait1 = new WebDriverWait(driver,30);
		wait1.until(ExpectedConditions.alertIsPresent()); 
		Alert alert1 =  driver.switchTo().alert(); alert1.accept();	
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_gallery")).click();    		
	    driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageView\r\n"+ "")).click();
	    Thread.sleep(500);
	    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Confirm Address\").instance(0))");
	    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_confirm")).click(); // confirm address button
	    System.out.println("Adding Address...");
        driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeBillAddress")).click();
	    Thread.sleep(2000);
        System.out.println("Changing Billing Address");
        
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        boolean a=driver.findElements(By.id("com.uneecops.sapsalesapp:id/rb_bill_address")).size()>0;
        System.out.println(a);
        if(a==true){  
        	System.out.println("Address present....changing now..");
        	driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_bill_address")).click();

     	   } else 
     	   {
     		   driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     		   System.out.println("Need to add addresses to the account....");
     		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/ivClose")).click();
     		  Thread.sleep(1000);	
     		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click();
     		  Thread.sleep(1000);
     		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click(); // back cart
     		 Thread.sleep(1000);
     		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click();
     		 Thread.sleep(1000);
     		  driver.findElement(By.id("android:id/button1")).click();
     		 Thread.sleep(1000);
     	     driver.findElement(By.id("com.uneecops.sapsalesapp:id/navigation_accounts")).click(); // Accounts icon	    
     		 Thread.sleep(1000);
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
    			driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_back")).click(); // Back to the  Dashboard
    			System.out.println("Adding New Purchase Order:Generate Proforma Invoice");
    			driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Order Journey\")"))).click();
    		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/relNewOrder\")"))).click();
    		    MobileElement mobileElement1=   (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/atvCustomer_chat_list\")")));	    
    		    mobileElement1.click();
    		    String args1 = Acc_Name;  
    		    int index1 = args1.length();
    		    String str1 = args1.substring(0, index1-1);
    		    mobileElement.sendKeys(str1);
    		    WebDriverWait wait2 = new WebDriverWait(driver,10);
    		    WebElement ActualAcc1=  wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]\r\n"+ "")));
    		    ActualAcc1.click();
    		    Thread.sleep(4000);
    		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\")"))).click();
    		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/tv_cotactperson\").instance(1)"))).click();
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/rbGeneratePi")).click();
    		    driver.navigate().back();
    		    Thread.sleep(1000);
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
    		    String BranchTrim1 = Branch.trim(); // Branch to uppercase.
    		    driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().textContains(\""+BranchTrim1+"\")"))).click();
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();  
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/button8")).click();      
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_name")).sendKeys(Item_Name); // Filter item name Dell002
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnAdd")).click();
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/etQty")).sendKeys(quantity);
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_cart")).click();
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tv_add_discount")).click();
    			driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_precentage")).sendKeys(DiscountPercentage);
    			driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnApply")).click();
    		    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"NOTES\").instance(0))").click();
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_pref_no")).sendKeys(PO_Number); //PO Number
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/tvPODate")).click();
    		    driver.findElement(By.id("android:id/button1")).click();
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_notes")).sendKeys(Notes);
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_confirm")).click(); // confirm address button
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeBillAddress")).click(); 
    		    System.out.println("Changing Billing Address");
    		    driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeBillAddress")).click(); 
    			driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_bill_address")).click();
    		   }
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        
        driver.findElement(By.id("com.uneecops.sapsalesapp:id/btnChangeShipAddress")).click();
        System.out.println("Changing Shipping Address");
        driver.findElement(By.id("com.uneecops.sapsalesapp:id/rb_ship_address")).click();
        System.out.println("Address Added");
        driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_confirm")).click();   
        Thread.sleep(3000);
        System.out.println("Order is Created by Generate Proforma Invoice");       
		
	}
	
	
	
	
	  @DataProvider
	    public Object[][] getDataForNewPOUploadOrder()          
	    {
	    	Object data[][]=TestUtil.getTestData(prop.getProperty("UploadPO"));
	    	return data;
	    }   
	  
    @DataProvider
    public Object[][] getDataForNewPOSelectFromItems()          
    {
    	Object data[][]=TestUtil.getTestData(prop.getProperty("SelectFromItems"));
    	return data;
    }    
  
    @DataProvider
    public Object[][] getDataForNewPOGeneratePInvoice()          
    {
    	Object data[][]=TestUtil.getTestData(prop.getProperty("GenerateInvoice"));
    	return data;
    }   
    @AfterMethod
    public void TearDown(ITestResult testResult) throws InterruptedException, IOException
    {
    	System.out.println("Quitting Session After Test");
    	Thread.sleep(3000);
    	 if (testResult.getStatus() == ITestResult.FAILURE)
         {
    	 System.out.println("Test Failed");
    	 String FailedTestName= testResult.getMethod().getMethodName();
         System.out.println("Failed TestName: "+FailedTestName);
         TestUtil.takeScreenshotAtEndOfTest(FailedTestName);
         }
        Thread.sleep(2000);
    	driver.quit();
    }
    OrderJourneyTab()
    { }  

  
}
