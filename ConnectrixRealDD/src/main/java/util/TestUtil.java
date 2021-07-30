package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.google.common.io.Files;

import base.TestBase;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class TestUtil extends TestBase {

	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\"+prop.getProperty("ExcelName");
	                                                     
	
	static Workbook book;
	static org.apache.poi.ss.usermodel.Sheet sheet;

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {e.printStackTrace();}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}

		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
		for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
		data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
		
			}
		}
		return data;
	}

	public static void takeScreenshotAtEndOfTest(String TestName) throws IOException {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		System.out.println("Taking Screenshot");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(scrFile, new File("FailedTestScreenshot\\Failed" + TestName + generatedString + ".png"));
		System.out.println("Screenshot captured");
	}

	public static void AddBillingShippingAddress() throws InterruptedException {
		System.out.println("Adding Billing Address");		
		driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"BILLING ADDRESSES\")")))
				.click();
		driver.findElement(MobileBy.AndroidUIAutomator(
				String.format("new UiSelector().resourceId(\"com.uneecops.sapsalesapp:id/btn_add_more\")"))).click();
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_tag")).sendKeys("Dummy Bil" + generatedString);
		System.out.println("Billing address ID:" + "Dummy Bil" + generatedString);
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_area")).click();
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_county")).click();
		Thread.sleep(2000);
		driver.navigate().back();
		System.out.println("Selecting country");
		long startTime = System.currentTimeMillis(); 
		WebElement dropdown1 = driver
				.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select Country\")")));
		dropdown1.click();
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"India\").instance(0))")
				.click();
		System.out.println("Country selected");
		long endTime = System.currentTimeMillis(); 
		long totTime= endTime-startTime;
		long finalTime= totTime/60000;
		System.out.println("Time Taken in dropdown Selection: "+finalTime+" minutes.");
		
		System.out.println("Selecting State");
		driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select State\")")))
				.click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Delhi\")"))).click();
		Thread.sleep(2000);
		System.out.println("State selected");
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_gstn")).click();
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/cb_copy")).click(); // Copy to Shipping Address
		Thread.sleep(500);
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
		System.out.println("Billing Address Added");

		
		  // Add Shipping Address
		  
		  System.out.println("Adding Shipping Address");
	//	  driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"SHIPPING ADDRESSES\")"))).click();
   //	  driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_add_more")).click();
//		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_tag")).sendKeys("DummyShip"+generatedString);
//		  System.out.println("Shipping address ID:"+"DummyShip"+generatedString);
//		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_area")).click();
	//	  Thread.sleep(2000);
		//  driver.navigate().back(); 
		//  Thread.sleep(2000);
		 // driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_county")).click();
		 // Thread.sleep(2000); 
		  //driver.navigate().back();
		  //System.out.println("Selecting country");
		  ///WebElement dropdown2=driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select Country\")"))); 
		 // dropdown2.click();
		  //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"India\").instance(0))").click();
		  //System.out.println("Country selected");
		  //driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select State\")"))).click();
		//  Thread.sleep(2000); System.out.println("Selecting State");
		 // driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Delhi\")"))).click();
		 // Thread.sleep(2000);
		  //System.out.println("State selected");
		  //driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_gstn")).click();
		  //Thread.sleep(2000);
		  //driver.navigate().back();
		 
   // copied address
		MobileElement Add = (MobileElement) driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_tag"));
		Add.clear();
		Add.sendKeys("DummyShip" + generatedString);
		System.out.println("Shipping address ID:" + "DummyShip" + generatedString);
		
		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_area")).click();
		  Thread.sleep(2000);
		  driver.navigate().back(); 
		  Thread.sleep(2000);
		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_county")).click();
		  Thread.sleep(2000); 
		  driver.navigate().back();
	//	  System.out.println("Selecting country");
	//	  WebElement dropdown2=driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select Country\")"))); 
	//	  dropdown2.click();
	//	  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"India\").instance(0))").click();
	//	  System.out.println("Country selected");
	//	  driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Select State\")"))).click();
	//	  Thread.sleep(2000); System.out.println("Selecting State");
   //	  driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Delhi\")"))).click();
	//	  Thread.sleep(2000);
	//	  System.out.println("State selected");
		  driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_gstn")).click();
		  Thread.sleep(2000);
		  driver.navigate().back();

		driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_save")).click();
		System.out.println("Shipping Address Added");
		System.out.println("Both Address added successfully");
	
	}

	

}
