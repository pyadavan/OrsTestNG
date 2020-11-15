package com.utility;

import java.io.File;


/**
 * Global Variable Class
 * @author Susovan
 *
 */
public class Global_VARS {

	
public static final String BROWSER=Browsers.get();	
public static final String PLATFORM="Windows server 2012";


public static String DEF_BROWSER=null;
public static String DEF_PLATFORM=null;
public static String DEF_ENVIRONMENT=null;


//suite folder defaults
public static final String TARGET_URL="https://www.facebook.com/";
public static String SUITENAME = null;


//driver class defaults
public static String propFile=System.getProperty("user.dir")+"//src//Selenium.properties";
public static final String 	SE_PROPS=new File(propFile).getAbsolutePath();

//test output defaults
public static final String BITMAP_PATH =System.getProperty("user.dir")+"//test-output//screenshots//";
public static final String TEST_OUTPUT_PATH=System.getProperty("user.dir")+"//Reports//";
public static final String LOGFILE_PATH= TEST_OUTPUT_PATH + "Logs//";
public static final String REPORT_PATH= TEST_OUTPUT_PATH + "Reports//";
public static final String REPORT_CONFIG_FILE= System.getProperty("user.dir")+"//extent-config.xml";


//timeout defaults
public static final int TIMEOUT_MINUTE=60;
public static final int TIMEOUT_SECOND=1;
public static final int TIMEOUT_ZERO=0;
public static final int TIMEOUT_ELEMENT=10;


}
