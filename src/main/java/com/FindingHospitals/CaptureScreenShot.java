package com.FindingHospitals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.FindingHospitals.DateUtil;

public class CaptureScreenShot extends BaseHome {

	/********** SCREENSHOT PAGE ***************/

	public void Screenshot() {

		TakesScreenshot scrshot = (TakesScreenshot) driver;
		File srcFile = scrshot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
