package com.FindingHospitals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.FindingHospitals.BaseHome;

/*********** COPORATE WELLNESS *************/

public class CoporateWellness extends BaseHome {

	// Providers

	@FindBy(xpath = "//span[@class='u-d-item up-triangle' and contains(text(),'For Providers')]")
	public static WebElement providers;

	// Corporate wellness link text

	@FindBy(linkText = "Corporate wellness")
	public static WebElement copwell;

	// Name box

	@FindBy(id = "name")
	public static WebElement name;

	// Organization name box

	@FindBy(id = "organization_name")
	public static WebElement orgname;

	// Email box

	@FindBy(id = "official_email_id")
	public static WebElement email;

	// phone number box

	@FindBy(id = "official_phone_no")
	public static WebElement phone;

	// clicking the demo button

	@FindBy(id = "button-style")
	public static WebElement save;

}
