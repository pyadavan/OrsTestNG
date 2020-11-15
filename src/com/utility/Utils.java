package com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Utils {


    private static Properties properties = new Properties();
    static {
        try {
        	FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"//src//com//config//config.properties");
            properties.load(fs);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static String getPropertyBrowser(String string, String URL) throws Exception{
		FileInputStream fs = new FileInputStream(URL);
		Properties pro = new Properties();
		pro.load(fs);
		return pro.getProperty(string);
	}
    
    private Utils() {
    }

    public static String getProperty(String property) throws Exception {
    	String browser;
        
        	String URLbrowser=System.getProperty("user.dir") + "//src//com//config//browser.properties";
        	try{browser=getPropertyBrowser(property, URLbrowser).trim();
        	if(browser.length()!=0) {
        		return browser;
        	}
        	}
        	catch(Exception e) {
        		System.out.println("Browser did not found in browser.properties");
        	}
        	String URL=System.getProperty("user.dir") + "//src//com//config//config.properties";
        	browser=getPropertyBrowser(property, URL);
        		return browser;
       
    }

    public static Object[][] populateExcelDataProvider() throws Exception {

       // int iterationIndex = Integer.parseInt(IterationIndex.get());
        Object[][] excelData;

       
            excelData = new Object[ExcelUtils.ExcelWSheet.getLastRowNum()][1];
            for (int count = 0; count < ExcelUtils.ExcelWSheet.getLastRowNum(); count++) {
                excelData[count][0] = count + 1;
            }

        

        return excelData;
    }
}
