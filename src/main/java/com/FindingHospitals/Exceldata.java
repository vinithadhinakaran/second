package com.FindingHospitals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

public class Exceldata {
	static WebDriver driver;

	public static File file;
	public static FileInputStream fin;

	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static FileOutputStream fout;
	static XSSFRow row = null;

	public static void writeExcel(String[] name, String sheetname) throws Exception {

		String path1 = System.getProperty("user.dir") + "\\TestData\\data.xlsx";
		file = new File(path1);
		fin = new FileInputStream(file);
		wb = new XSSFWorkbook(fin);
		sh = wb.getSheet(sheetname);
		for (int i = 0; i < name.length; i++) {
			row = sh.createRow(i);
			row.createCell(0).setCellValue(name[i]);
		}
		sh.autoSizeColumn(0);
		// Writing the output to Excel file using FileOutputStream

		fout = new FileOutputStream(path1);
		wb.write(fout);
		fout.close();

	}

}
