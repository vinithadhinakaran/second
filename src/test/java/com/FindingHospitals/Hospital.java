package com.FindingHospitals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Hospital extends BaseHome {

	/***************** HOSPITALS DESCRIPTIONS ********************/

	// SEARCH BOX

	@FindBy(xpath = "//input[@placeholder='Search location']")
	public static WebElement searchBox;

	@FindBy(xpath = "//*[@class='icon-ic_cross_solid']")
	public static WebElement crossIcon;

	// SEARCH BANGALORE CITY

	@FindBy(xpath = "//div[normalize-space()='Bangalore']")
	public static WebElement city;

	// SEARCH HOSPITALS

	@FindBy(xpath = "//input[@placeholder='Search doctors, clinics, hospitals, etc.']")
	public static WebElement searchHospital;

	@FindBy(xpath = "//div[normalize-space()='Hospital']")
	public static WebElement hospital;

	// FILTERS

	@FindBy(xpath = "//span[contains(text(),'Open 24X7')]")
	public static WebElement open;

	@FindBy(xpath = "//div[@class='u-d-inlineblock u-color--white u-c-pointer']")
	public static WebElement allfilter;

	@FindBy(xpath = "//span[contains(text(),'Has Parking')]")
	public static WebElement parking;

	// HOSPITALS LIST

	@FindBy(xpath = "//h2[@class='u-title-font u-c-pointer u-bold']")
	public static WebElement hospitalslist;

}
