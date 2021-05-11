package com.FindingHospitals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/******* DIAGNOSTICS *******************/

public class Diagnostics extends BaseHome {

	@FindBy(xpath = "//div[@class='product-tab__title' and contains(text(),'Diagnostics')]")
	public static WebElement diagnostics;

	@FindBy(xpath = "//li[@class='u-text--center']")
	public static WebElement topcitieslist;

}
