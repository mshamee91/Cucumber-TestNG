package com.qa.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	//public String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;

	public void excelReader(String path,String sheetName) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		int sheetNo = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(sheetNo);
		fis.close();		
	}
	
	public int getTotalRows() {
		int rows = sheet.getLastRowNum()+1;
		return rows;
	}
	
	public String getCellValue(String ColumnName,int rownum) {
		int columnNum = 0;
		row = sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++) {
			cell = row.getCell(i);
			if(cell.getStringCellValue().equalsIgnoreCase(ColumnName)) {
				columnNum = i;
			}
		}
		row = sheet.getRow(rownum);
		cell = row.getCell(columnNum);
		String cellValue = cell.getStringCellValue();
		return cellValue;
	}
	
}
