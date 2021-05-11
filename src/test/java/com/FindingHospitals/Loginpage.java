package com.FindingHospitals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/************* LOGIN ********************/
public class Loginpage extends BaseHome {
	@FindBy(id = "username")
	public static WebElement username;
	@FindBy(id = "login")
	public static WebElement login;
	@FindBy(name = "Practo login")
	public static WebElement logg;

}
//<include name="Regression Suite"/>
// <class name="com.FindingHospitals.Regression"/>
