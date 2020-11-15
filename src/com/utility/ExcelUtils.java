
package com.utility;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtils {
    public static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    public static int CurrentIterationIndex;

    // This method is to set the File path and to open the Excel file, Pass
    // Excel Path and Sheetname as Arguments to this method
    public static void setExcelFile(String Path, String SheetName)
            throws Exception {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            Log.info("Excel sheet opened");
        } catch (Exception e) {
            throw (e);
        }
    }

    // This method is to read the test data from the Excel cell, in this we are
    // passing parameters as Row num and Col num
    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    public static int getColumnIndex(String name) throws Exception {
        int index = -1;
        try {
            // Look for the column name
            XSSFRow headersRow = ExcelWSheet.getRow(0);
            short minIndex = headersRow.getFirstCellNum();
            short maxIndex = headersRow.getLastCellNum();
            for (short i = minIndex; i < maxIndex; i++) {
                XSSFCell cell = headersRow.getCell(i);
                if (cell == null) {
                    continue;
                }

                if (cell.getStringCellValue().toUpperCase().trim().equals(name.toUpperCase().trim())) {
                    index = i;
                    break;
                }
            }
        } catch (Exception e) {
            Log.error("Class ExcelUtil | Method getColumnIndex | Exception desc : " + e.getMessage());
            throw (e);
        }

        //if (index < 0)
        //    throw new Exception("Column '" + name + "' is not found in the excel doc.");
        return index;
    }

    public static String getCellData(int RowNum, String ColName) throws Exception {
        int index = getColumnIndex(ColName);
        if (index >= 0)
            return getCellData(RowNum, index);
        else {
            Log.info("Column '" + ColName + "' is not found in the excel doc.", true);
            return "";
        }
    }

    // This method is to write in the Excel cell, Row num and Col num are the
    // parameters
    public static void setCellData(String testCaseName, String Result,
                                   int RowNum, int ColNum) throws Exception {
        try {
            Row = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(
                    Constant.PathTestData + "//" + testCaseName + ".xlsx");
            ExcelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            throw (e);
        }
    }

    // This method is to write in the Excel cell, Row num and Col num are the
    // parameters
    // @SuppressWarnings("static-access")
    /*public static void setCellData(String testCaseName, String Result,
			int RowNum, String ColName) throws Exception {
		try {

			int colNum = -1;
			// Look for the column name
			XSSFRow headersRow = ExcelWSheet.getRow(0);
			short minColIx = headersRow.getFirstCellNum();
			short maxColIx = headersRow.getLastCellNum();
			for (short colIx = minColIx; colIx < maxColIx; colIx++) {
				XSSFCell cell = headersRow.getCell(colIx);
				if (cell == null) {
					continue;
				}

				if (cell.getStringCellValue().toUpperCase().trim()
						.equals(ColName.toUpperCase().trim())) {
					colNum = colIx;
					setCellData(testCaseName, Result, RowNum, colNum);
					break;
				}
			}
		} catch (Exception e) {
			throw (e);
		}
	}*/

    public static int getRowContains(String sTestCaseName, int colNum)
            throws Exception {
        int i;
        try {
            int rowCount = ExcelUtils.getRowUsed();
            for (i = 0; i < rowCount; i++) {
                if (ExcelUtils.getCellData(i, colNum).equalsIgnoreCase(
                        sTestCaseName)) {
                    break;
                }
            }
            return i;
        } catch (Exception e) {
            Log.error("Class ExcelUtil | Method getRowContains | Exception desc : "
                    + e.getMessage());
            throw (e);
        }
    }

    public static int getRowUsed() throws Exception {
        try {
            int RowCount = ExcelWSheet.getLastRowNum();
            Log.info("Total number of Row used return as < " + RowCount + " >.");
            return RowCount;
        } catch (Exception e) {
            Log.error("Class ExcelUtil | Method getRowUsed | Exception desc : "
                    + e.getMessage());
            System.out.println(e.getMessage());
            throw (e);
        }
    }

	/*
	 * public static int getRowNumber() throws Exception { try{ int RowCount =
	 * ExcelWSheet.getrows(); Log.info("Total number of Row used return as < " +
	 * RowCount + " >."); return RowCount; }catch (Exception e){
	 * Log.error("Class ExcelUtil | Method getRowUsed | Exception desc : "
	 * +e.getMessage()); System.out.println(e.getMessage()); throw (e); }
	 * 
	 * }
	 */
}