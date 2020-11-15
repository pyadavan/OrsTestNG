package com.generateReportInExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.utility.Global_VARS;

public class GenerateReport {


	public static  int actualSkipCount=0;
	public static  int SkipCount=0;
	@SuppressWarnings("unused")
	public static void generateExcelReport (String location, String destFilename) throws ParserConfigurationException, SAXException, IOException{

		//String path = ExcelReportGenerator.class.getClassLoader().getResource("./").getPath();//Loading the class with the path
		String path =System.getProperty("user.dir")+"\\test-output\\";
		String path1 = path +"testng-results.xml";
		//System.out.println(path1);

		int customr=10;

		File xmlFile = new File(path1);//Reading the XML File
		System.out.println(xmlFile.isFile());

		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = fact.newDocumentBuilder();
		Document doc = build.parse(xmlFile);
		doc.getDocumentElement().normalize();

		XSSFWorkbook book = new XSSFWorkbook();		//Creating workbook
		//XSSFCellStyle Fail = book.createCellStyle();	//for stlying the cell initiation
		//XSSFCellStyle Pass = book.createCellStyle();

		XSSFSheet S = book.createSheet("Summary");	
		S.setDisplayGridlines(false);

		XSSFCellStyle style1 = book.createCellStyle();
		XSSFCellStyle style2 = book.createCellStyle();

		XSSFCellStyle H1 = book.createCellStyle();
		XSSFCellStyle H2 = book.createCellStyle();
		XSSFCellStyle TNH2 = book.createCellStyle();
		XSSFCellStyle H2_P = book.createCellStyle();		//for stlying the cell initiation
		XSSFCellStyle H2_F = book.createCellStyle();
		XSSFFont font = book.createFont();
		XSSFFont font1 =book.createFont();
		XSSFFont font2 =book.createFont();
		XSSFFont font3 =book.createFont();
		XSSFFont font4 =book.createFont();

		CellRangeAddress cellRangeAddress = new CellRangeAddress(10,10,5,9);

		/*Heading1.setFillForegroundColor(HSSFColor.BLUE.index);		// intiating the color to the cell
			Heading2.setFillForegroundColor(HSSFColor.BLUE_GREY.index);		

			Heading1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);		//filling the color to the cell
			Heading2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);*/

		XSSFRow row = S.createRow(customr);
		for (int i = 5; i <= 9; ++i) {
			//Cell cell = row.createCell(i);
			XSSFCell cell_name =row.createCell(i);
			cell_name.setCellStyle(H1);
			ThickH1(H1, font, 20);

			if (i == 5) {
				FileInputStream fs = new FileInputStream(
						System.getProperty("user.dir") + "//src//com//config//config.properties");
				Properties pro = new Properties();
				pro.load(fs);
				if(Global_VARS.BROWSER.equalsIgnoreCase("IE"))
				{
					cell_name.setCellValue("Automation Edge Execution Report");
				}
				else if (Global_VARS.BROWSER.equalsIgnoreCase("chrome")){
					cell_name.setCellValue("Automation Chrome Execution Report");
				}
				else if (Global_VARS.BROWSER.equalsIgnoreCase("Safari")){
					cell_name.setCellValue("Automation MAC Execution Report");
				}
				else if (Global_VARS.BROWSER.equalsIgnoreCase("firefox")){
					cell_name.setCellValue("Automation Firefox Execution Report");
				}

			} 
		}
		XSSFRow row1 = S.createRow(customr+1);
		row1.createCell(5).setCellValue("Total TC's");		//Creating and setting cell for method name
		row1.createCell(6).setCellValue("Passed");
		row1.createCell(7).setCellValue("Failed");
		row1.createCell(8).setCellValue("Skipped");
		row1.createCell(9).setCellValue("Duration in Mins");
		row1.getCell(5).setCellStyle(H2);
		row1.getCell(6).setCellStyle(H2);
		row1.getCell(7).setCellStyle(H2);
		row1.getCell(8).setCellStyle(H2);
		row1.getCell(9).setCellStyle(H2);
		ThickH2(H2, font2, 18);

		S.addMergedRegion(cellRangeAddress);	

		NodeList test_list= doc.getElementsByTagName("test");//getting the list of test's
		for(int i=0; i<test_list.getLength(); i++ ){
			int r=1;
			Node test_node= test_list.item(i); 				//reading the list of test's in it
			String test_name = ((Element)test_node).getAttribute("name");			// getting the test name	
			XSSFSheet Sheet = book.createSheet(test_name);				//Creating a sheet for the test
			NodeList class_list= ((Element)test_node).getElementsByTagName("class");
			//System.out.println(test_list.getLength());
			for(int j=0; j<class_list.getLength(); j++ ){
				Node class_node= class_list.item(j); 		//getting the list of classes in it
				String class_name = ((Element)class_node).getAttribute("name");
				NodeList test_method_list= ((Element)class_node).getElementsByTagName("test-method");
				for(int k=0; k<test_method_list.getLength(); k++ ){
					//int c=k+1;
					Node test_method_node = test_method_list.item(k);		//getting the method's name

					String test_method_name = ((Element)test_method_node).getAttribute("name");	
					if(!(test_method_name.equals("setUp") 
							|| test_method_name.equals("tearDown")
							||test_method_name.equals("closeBrowser")
							||test_method_name.equals("suiteSetup")
							||test_method_name.equals("testSetUp")
							||test_method_name.equals("testClassTearDown")
							||test_method_name.equals("testTearDown")
							||test_method_name.equals("suiteTeardown"))){

						String test_description = ((Element)test_method_node).getAttribute("description");	
						String test_method_status = ((Element)test_method_node).getAttribute("status");	
						String test_method_duration = ((Element)test_method_node).getAttribute("duration-ms");	
						//System.out.println(test_method_list.getLength());

						if(test_method_status.equalsIgnoreCase("skip") && test_method_duration.equalsIgnoreCase("0")) {
							actualSkipCount++;
						}

						if(test_method_status.equalsIgnoreCase("skip") && !test_method_duration.equalsIgnoreCase("0")) {
							SkipCount++;
						}

						if(!test_method_status.equalsIgnoreCase("skip") && !test_method_status.equalsIgnoreCase("0")) {

							XSSFRow row2 = Sheet.createRow(r++);
							row2.createCell(0).setCellValue(r-1);
							row2.getCell(0).setCellStyle(style1);//Creating and setting the cell with method name
							row2.createCell(1).setCellValue(test_description);
							//row2.createCell(0).setCellValue(test_method_name);
							//cell_name.setCellValue(test_method_name);
							row2.getCell(1).setCellStyle(style1);
							row2.createCell(2).setCellValue(test_method_status); 		//Creating and setting the cell with status
							row2.getCell(2).setCellStyle(style1);
							row2.createCell(3).setCellValue(""); 		
							row2.getCell(3).setCellStyle(style1);
							ThinNormal(style1, font3, 11);

							XSSFRow row_title = Sheet.createRow(0);
							row_title.createCell(0).setCellValue("SI NO");
							row_title.createCell(1).setCellValue("Test cases");
							row_title.createCell(2).setCellValue("Status");
							row_title.createCell(3).setCellValue("Reason for Failure");

							row_title.getCell(0).setCellStyle(TNH2);
							row_title.getCell(1).setCellStyle(TNH2);
							row_title.getCell(2).setCellStyle(TNH2);
							row_title.getCell(3).setCellStyle(TNH2);

							ThinH2(TNH2, font4, 12);


							if("fail".equalsIgnoreCase(test_method_status)){
								row2.getCell(2).setCellStyle(H2_F);
								Fail(H2_F);}
							else{
								row2.getCell(2).setCellStyle(H2_P);
								Pass(H2_P);}

							String exp_msg;
							String expected_message=null;


							Sheet.setDisplayGridlines(false);

							if("fail".equalsIgnoreCase(test_method_status)){
								NodeList exp_list= ((Element)test_method_node).getElementsByTagName("exception");	//getting all exception list
								Node exp_node = exp_list.item(0);		//selecting only first exception	
								exp_msg=((Element)exp_node).getAttribute("class");	//getting the exception message

								switch(exp_msg) {
								case "java.lang.AssertionError":{
									expected_message="Expected and Actual are not matching.";
									break;
								}

								case "java.lang.NullPointerException":{
									expected_message="Preferred WebElement is not found.";
									break;	
								}

								case "org.openqa.selenium.WebDriverException":{
									expected_message="WebDriver method not able to perform its action.";
									break;	
								}
								case "org.openqa.selenium.NoSuchElementException":{
									expected_message="Unable to locate WebElement in the WebPage.";
									break;	
								}
								case "org.openqa.selenium.TimeoutException":{
									expected_message="Not able to locate the WebElement in the stipulated time.";
									break;	
								}
								case "org.openqa.selenium.ElementNotVisibleException":{
									expected_message="WebElement is not visible in the desired location in the WebPage.";
									break;	
								}
								default:{
									expected_message="Unexpected Failure reason : Need manual analysis";
								}
								}

								row2.createCell(3).setCellValue(expected_message);
								row2.getCell(3).setCellStyle(H2_F);
								//							row2.getCell(3).setCellStyle(style1);//Creating and setting the value of exception message to the cell

							}
						}

					}
				}

				NodeList tst_res_list= doc.getElementsByTagName("testng-results");

				for (int l=0;l<tst_res_list.getLength(); l++){

					Node tst_res_node = tst_res_list.item(l);
					String tc_name =((Element)tst_res_node).getAttribute("total");
					int Total = Integer.parseInt(tc_name);
					String ignoredcount =((Element)tst_res_node).getAttribute("ignored");
					int ignored = Integer.parseInt(ignoredcount);
					String tc_name1=((Element)tst_res_node).getAttribute("passed");
					String tc_name2=((Element)tst_res_node).getAttribute("failed");
					//String tc_name3=((Element)tst_res_node).getAttribute("skipped");
					int ActualTotal = Total-ignored-SkipCount;

					XSSFRow Row2 =S.createRow(customr+2);
					Row2.createCell(5).setCellValue(ActualTotal);
					Row2.createCell(6).setCellValue(tc_name1);
					Row2.createCell(7).setCellValue(tc_name2);
					Row2.createCell(8).setCellValue(actualSkipCount);

					Row2.getCell(5).setCellStyle(style2);
					Row2.getCell(6).setCellStyle(style2);
					Row2.getCell(7).setCellStyle(style2);
					Row2.getCell(8).setCellStyle(style2);
					ThickNormal(style2, font1, 11);
				}

				NodeList tst_res_list1= doc.getElementsByTagName("suite");
				for (int m=0;m<tst_res_list1.getLength(); m++){

					Node tst_res_node1 = tst_res_list1.item(m);
					String tc_name4ms =((Element)tst_res_node1).getAttribute("duration-ms");
					long number = new Long(tc_name4ms).longValue();
					long tc_name4=((number)/1000)/60;
					XSSFRow Row2 =S.getRow(customr+2);

					Row2.createCell(9).setCellValue(tc_name4);

					Row2.getCell(9).setCellStyle(style2);
					ThickNormal(style2, font1, 11);
				}

				//autosizing the whole workbook
				int numberOfSheets = book.getNumberOfSheets();
				for (int A = 0; A < numberOfSheets; A++) {

					Sheet sheet = book.getSheetAt(A);
					if (sheet.getPhysicalNumberOfRows() > 0) {
						Row R = sheet.getRow(0);
						// sheet.autoSizeColumn(0);
						if(A==0){
							Iterator<Cell> cellIterator1 = row.cellIterator();
							while (cellIterator1.hasNext()) {
								Cell cell1 = cellIterator1.next();
								int columnIndex = cell1.getColumnIndex();
								sheet.autoSizeColumn(columnIndex);
							}
						}
						else{
							Iterator<Cell> cellIterator = R.cellIterator();
							while (cellIterator.hasNext()) {
								Cell cell = cellIterator.next();
								int columnIndex = cell.getColumnIndex();
								sheet.autoSizeColumn(columnIndex);
							}
						}
					}
				}



			}
		}
		//FileOutputStream fout= new FileOutputStream(path+ "report/"+ destFilename);

		FileOutputStream fout= new FileOutputStream(location+destFilename);
		book.write(fout);
		book.close();
		//fout.close();
		System.out.println("Report generated");

	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		new GenerateReport();
		/*@Test
		public static void Report() throws ParserConfigurationException, SAXException, IOException {
		 */
		GenerateReport.deletefile();
		GenerateReport.generateExcelReport(System.getProperty("user.dir")+"/test-output/","ExecutionReport"+".xlsx");

		//new ExcelReportGenerator().XLR("report.xlsx");
	}

	public static String datetime(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());

	}

	public static void deletefile(){

		try{

			File file = new File(System.getProperty("user.dir")+"/test-output/", "ExecutionReport" + ".xlsx");

			if(file.delete()){
				System.out.println(file.getName() + " is deleted!");
			}else{
				System.out.println("Delete operation is failed.");
			}

		}catch(Exception e){

			e.printStackTrace();

		}
	}

	public static void XLR(String desFile){

		String path = GenerateReport.class.getClassLoader().getResource("./").getPath();
		//System.out.println("Path of the file is :" +path);
		path = path.replaceAll("bin", "test-output");
		//System.out.println("Path of the file is Updated :" +path);
		String path1 = path +"testng-results.xml";
		//System.out.println(path1);
		File xmlFile = new File(path1);//Reading the XML File

		System.out.println("Does xml File present: "+xmlFile.isFile());

	}

	public static void ThickNormal (XSSFCellStyle Style, XSSFFont Font, int height){

		Style.setBorderBottom(BorderStyle.THICK);
		Style.setBorderLeft(BorderStyle.THICK);
		Style.setBorderRight(BorderStyle.THICK);
		Style.setBorderTop(BorderStyle.THICK);
		//Style.setAlignment(CellStyle.ALIGN_CENTER);
		Font.setFontName(HSSFFont.FONT_ARIAL);
		Font.setFontHeight(height);

		Style.setFont(Font);

	}

	public static void ThinNormal (XSSFCellStyle Style, XSSFFont Font, int height){

		Style.setBorderBottom(BorderStyle.THIN);
		Style.setBorderLeft(BorderStyle.THIN);
		Style.setBorderRight(BorderStyle.THIN);
		Style.setBorderTop(BorderStyle.THIN);

		Font.setFontName(HSSFFont.FONT_ARIAL);
		Font.setFontHeight(height);

		Style.setFont(Font);
	}

	@SuppressWarnings("deprecation")
	public static void ThickH1 (XSSFCellStyle Style, XSSFFont Font, int height){


		Style.setBorderBottom(BorderStyle.THICK);
		Style.setBorderLeft(BorderStyle.THICK);
		Style.setBorderRight(BorderStyle.THICK);
		Style.setBorderTop(BorderStyle.THICK);

		Style.setFillForegroundColor(HSSFColor.AQUA.index);		

		Style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		Font.setFontName(HSSFFont.FONT_ARIAL);
		Font.setFontHeight(height);

		Style.setFont(Font);

	}
	@SuppressWarnings("deprecation")
	public static void ThickH2 (XSSFCellStyle Style, XSSFFont Font, int height){


		Style.setBorderBottom(BorderStyle.THICK);
		Style.setBorderLeft(BorderStyle.THICK);
		Style.setBorderRight(BorderStyle.THICK);
		Style.setBorderTop(BorderStyle.THICK);

		Style.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);		

		Style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		Font.setFontName(HSSFFont.FONT_ARIAL);
		Font.setFontHeight(height);

		Style.setFont(Font);

	}

	@SuppressWarnings("deprecation")
	public static void ThinH2 (XSSFCellStyle Style, XSSFFont Font, int height){


		Style.setBorderBottom(BorderStyle.THIN);
		Style.setBorderLeft(BorderStyle.THIN);
		Style.setBorderRight(BorderStyle.THIN);
		Style.setBorderTop(BorderStyle.THIN);

		Style.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);		

		Style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		Font.setFontName(HSSFFont.FONT_ARIAL);
		Font.setFontHeight(height);

		Style.setFont(Font);

	}

	@SuppressWarnings("deprecation")
	public static void Fail (XSSFCellStyle Status){


		Status.setFillForegroundColor(HSSFColor.RED.index);		// intiating the color to the cell

		Status.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);		//filling the color to the cell
		Status.setBorderBottom(BorderStyle.THIN);
		Status.setBorderTop(BorderStyle.THIN);
		Status.setBorderRight(BorderStyle.THIN);
		Status.setBorderLeft(BorderStyle.THIN);
	}

	@SuppressWarnings("deprecation")
	public static void Pass(XSSFCellStyle Status){

		Status.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
		Status.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		Status.setBorderBottom(BorderStyle.THIN);
		Status.setBorderTop(BorderStyle.THIN);
		Status.setBorderRight(BorderStyle.THIN);
		Status.setBorderLeft(BorderStyle.THIN);
	}

}