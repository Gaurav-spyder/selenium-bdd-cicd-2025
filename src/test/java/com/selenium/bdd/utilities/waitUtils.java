package com.selenium.bdd.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waitUtils {
public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int timeoutsInSeconds) {
	new WebDriverWait(driver,Duration.ofSeconds(timeoutsInSeconds))
	.until(ExpectedConditions.visibilityOf(element));
}
public static List<Map<String, String>> readDataFromExcel(String filePath, String sheetName) {

    List<Map<String, String>> dataList = new ArrayList<>();

    try (FileInputStream fis = new FileInputStream(filePath);
         Workbook workbook = WorkbookFactory.create(fis)) {

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new RuntimeException("Sheet not found: " + sheetName);
        }

        Row headerRow = sheet.getRow(0);  // First row = headers
        int totalColumns = headerRow.getLastCellNum();

        // Loop all data rows starting from row 1
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> rowData = new HashMap<>();

            for (int j = 0; j < totalColumns; j++) {
                String header = headerRow.getCell(j).getStringCellValue();
                Cell cell = row.getCell(j);

                String value = (cell == null) ? "" : cell.toString().trim();
                rowData.put(header, value);
            }

            dataList.add(rowData);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return dataList;
}

}
