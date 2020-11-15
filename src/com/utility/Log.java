package com.utility;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

public class Log {
	private static Logger Log = Logger.getLogger(Log.class.getName());
	
	public static void main(String args[]) {
		Log = Logger.getLogger(Log.class.getClass());
		PropertyConfigurator.configure("Log4j.properties");
	}
    // Initialize Log4j logs
    
    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
    public static void startTestCase(String sTestCaseName) {
    	
    	Log.info("****************************************************************************************");
        Log.info("****************************************************************************************");
        Log.info("$$$$$$$$$$$$$$$$$$$$$                 " + sTestCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$");
        Log.info("****************************************************************************************");
        Log.info("****************************************************************************************");
        Reporter.log("Executing Test Case: " + sTestCaseName);
    }


    // Need to create these methods, so that they can be called  
    public static void info(String message) {
        Log.info(message);
    }

    public static void info(String message, Boolean addToReporter) {
        Log.info(message);
        Reporter.log(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void error(String message, Boolean addToReporter) {
        Log.error(message);
        Reporter.log(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }
}