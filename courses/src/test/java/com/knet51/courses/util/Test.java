package com.knet51.courses.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableCell;
import jxl.write.WritableWorkbook;

public class Test {
	private static Properties SetProperties() throws Exception{
		Properties p = new Properties();
		FileInputStream in  = new FileInputStream("src/main/resources/test.properties");
		p.load(in);
		return p;
	}
	
	public static void LoadProperties() throws Exception{
		Properties p = SetProperties();
		System.out.println(p.getProperty("id"));
		System.out.println(p.getProperty("value"));
	}
	
	public static void main(String[] args) throws Exception {
		//WritableWorkbook book =  Workbook.createWorkbook(new File("e://test.xls"));
		File file = new File("e://test.xls");
		System.out.println(file.getName());
		Workbook.getWorkbook(file);
//		Sheet sheet = book.getSheet(0);
//		Cell  c = sheet.getCell(1, 1);
//		System.out.println(c.getContents());
	}
}
