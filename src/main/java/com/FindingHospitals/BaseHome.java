//**********************   HACKATHON PROJECT  [Team - 4 ]   **********************//

//************** [ FINDING HOSPITALS ] ***************//

/********** ( "Fantastic five " ) **********/

package com.FindingHospitals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.FindingHospitals.Exceldata;
import com.FindingHospitals.ExtentReport;

public class BaseHome {

	public static WebDriver driver = null;
	public static Properties prop = null;
	public ExtentReports report = ExtentReport.getReportInstance();
	public ExtentTest logger;
	String value = "Bangalore";
	String name = "Hospital";
	String mail = "Alan@gmail.com";

	/********** Managing MultiBrowser **************/

	public void invokeBrowsers(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivertwo\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		/************* MAXIMIZE THE WINDOW *************/

		driver.manage().window().maximize();
		waitload(20);

		/********** GETTING THE DATA FROM THE PROPERTY FILE ***********/

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(
						System.getProperty("user.dir") + "\\propertiesConfig.Properties");
				prop.load(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/*********** OPEN THE URL ***********/

	public void openURL(String WebsiteURL) {
		driver.get(prop.getProperty(WebsiteURL));
	}

	/**************** SEARCH FOR THE BANGALORE CITY *************/

	public void clickBangalore() {

		PageFactory.initElements(driver, Hospital.class);

		Hospital.searchBox.click();
		Hospital.crossIcon.click();
		Hospital.searchBox.sendKeys(value);
		waitload(10);
		Hospital.city.click();

	}

	/************ SEARCH FOR HOSPITAL IN BANGALORE ***************/

	public void clickHospital() throws InterruptedException {
		try {
			PageFactory.initElements(driver, Hospital.class);
			Hospital.searchHospital.click();
			Hospital.searchHospital.sendKeys(name);
			waitload(10);
			Hospital.hospital.click();

			/***************** CLICK THE OPEN [24*7] CHECKBOX *****************/

			Hospital.open.click();
			Thread.sleep(2000);

			/************ CLICK THE ALL FILTERS ICON *************/

			Hospital.allfilter.click();
			Thread.sleep(2000);

			/******** CLICK THE HAS PARKING CHECKBOX ***********/

			Hospital.parking.click();
			Thread.sleep(2000);

			/*********** SHOWS HOSPITALS HAVING ABOVE 3.5 RATINGS *************/

			List<WebElement> slist = driver.findElements(By.xpath("//span[@class='common__star-rating__value']"));
			System.out.println(" Hospitals Above 3.5 Ratings :  " + slist.size());
			CaptureScreenShot cap = new CaptureScreenShot();
			cap.Screenshot();
		} catch (NoSuchElementException e) {

			Reporter.log("ELEMENT WITH Xpath VALUE : IS NOT AVAILABLE");

			e.printStackTrace();

		} catch (Exception e) {

			Reporter.log("UNKNOWN EXCEPTION OCCURED");

			e.printStackTrace();

		}

	}

	public String[] getHospitalList() {

		List<WebElement> hospitals = driver.findElements(By.xpath("//h2[@data-qa-id='hospital_name']"));
		String[] hospitalList = new String[hospitals.size()];
		for (int i = 0; i < hospitals.size(); i++) {
			hospitalList[i] = hospitals.get(i).getText();
			System.out.println(hospitalList);
		}
		return hospitalList;

	}

	public void hospitalsdata() throws Exception {

		String[] hospitalList = getHospitalList();
		for (String temp : hospitalList)
			System.out.println(temp);
		Exceldata.writeExcel(hospitalList, "hospitalList");

	}

	/*********** CLICK THE DIAGNOSTICS *************/
	public void Diagnosticspage() throws InterruptedException {
		try {
			PageFactory.initElements(driver, Diagnostics.class);
			Diagnostics.diagnostics.click();
			CaptureScreenShot cap = new CaptureScreenShot();
			cap.Screenshot();
		} catch (NoSuchElementException e) {

			Reporter.log("ELEMENT WITH Xpath VALUE : IS NOT AVAILABLE");

			e.printStackTrace();

		} catch (Exception e) {

			Reporter.log("UNKNOWN EXCEPTION OCCURED");

			e.printStackTrace();

		}

	}

	/******** LIST OF TOP CITIES ***********/

	public String[] TopCities() {
		List<WebElement> topCities = driver.findElements(By.xpath("//li[@class='u-text--center']/div[2]"));

		String[] cities = new String[topCities.size()];
		for (int i = 0; i < topCities.size(); i++) {
			cities[i] = topCities.get(i).getText();
			System.out.println(cities);

		}

		return cities;
	}

	public void topcities() throws Exception {
		String[] topCities = TopCities();
		for (String temp : topCities)
			System.out.println(temp);
		Exceldata.writeExcel(topCities, "topCities");
	}

	/**************
	 * GO TO THE COPORATE WELLNESS PAGE AND FILL INVALID DETAILS AND ACCEPTS THE
	 * ALERT POPUP BOX
	 ***********/

	public void CopWellness() throws IOException, InterruptedException {
		try {

			/***** NAVIGATION OF PAGE *****/

			driver.navigate().back();

			/******* CLICK THE PROVIDERS *********/

			PageFactory.initElements(driver, CoporateWellness.class);
			CoporateWellness.providers.click();

			/********** COPORATE WELLNESS *********/

			CoporateWellness.copwell.click();

			/********** OPENING CHILD WINDOW TAB **********/

			Set<String> fill = driver.getWindowHandles();
			Iterator<String> it = fill.iterator();
			String mainpage = it.next();
			String form = it.next();
			driver.switchTo().window(form);

			/************* READ FROM THE XLSX FILE TO FILL THE BOXES *************/

			File src = new File(System.getProperty("user.dir") + "\\TestData\\input1.xlsx");
			FileInputStream stream = new FileInputStream(src);
			XSSFWorkbook book = new XSSFWorkbook(stream);
			XSSFSheet sheet = book.getSheet("Sheet1");

			String myname = sheet.getRow(0).getCell(0).getStringCellValue();
			String orgname = sheet.getRow(0).getCell(1).getStringCellValue();
			String mymail = sheet.getRow(0).getCell(2).getStringCellValue();
			long myphoneno = (int) sheet.getRow(0).getCell(3).getNumericCellValue();
			Thread.sleep(5000);
			String myphone = String.valueOf(myphoneno);

			waitload(10);

			/********** SENDING KEYS TO THE FIELD **********/
			PageFactory.initElements(driver, CoporateWellness.class);

			CoporateWellness.name.sendKeys(myname);
			CoporateWellness.orgname.sendKeys(orgname);
			CoporateWellness.email.sendKeys(mymail);
			CoporateWellness.phone.sendKeys(myphone);
			CoporateWellness.save.sendKeys(myname);

			Thread.sleep(3000);

			/******** POPUP ALERT MESSAGE ***********/

			String alertMessage = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();// simple alert
			Thread.sleep(1000);
			System.out.println(alertMessage);
			Thread.sleep(1000);

			/********* CAPTURE THE SCREENSHOT ************/

			CaptureScreenShot cap = new CaptureScreenShot();
			cap.Screenshot();

			Thread.sleep(3000);
			book.close();
			closeBrowser();
		} catch (NoSuchElementException e) {

			Reporter.log("ELEMENT WITH VALUE : IS NOT AVAILABLE");

			e.printStackTrace();

		} catch (Exception e) {

			Reporter.log("UNKNOWN EXCEPTION OCCURED");

			e.printStackTrace();

		}
	}

	public void clickLogin() throws InterruptedException {
		try {
			invokeBrowsers("chrome");
			openURL("WebsiteURL");
			String pageinfo = "Password field cannot be empty";
			PageFactory.initElements(driver, Loginpage.class);
			Loginpage.logg.click();

			Loginpage.username.sendKeys(mail);
			waitload(10);
			Loginpage.login.click();
			Assert.assertEquals("Password field cannot be empty", pageinfo);
			System.out.println("Asking to enter the password:" + " " + pageinfo);
			CaptureScreenShot capp = new CaptureScreenShot();
			capp.Screenshot();
			driver.navigate().back();
			waitload(10);

		} catch (NoSuchElementException e) {

			Reporter.log("ELEMENT WITH Xpath VALUE FOR CONFIRM ALERT : IS NOT AVAILABLE");

			e.printStackTrace();

		} catch (Exception e) {

			Reporter.log("UNKNOWN EXCEPTION OCCURED");

			e.printStackTrace();

		}

	}

	/******************* wait load **************************/
	public void waitload(int value) {
		driver.manage().timeouts().implicitlyWait(value, TimeUnit.SECONDS);
	}

	@AfterTest
	public void closeBrowser() {
		report.flush();
		driver.quit();
	}
}
