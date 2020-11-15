package com.dataproviders.login;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.DataProvider;

import com.utility.Constant;
import com.utility.ExcelUtils;
import com.utility.Log;
import com.utility.Utils;

public class loginValidation {
	
	public static String tcName;
	
	@DataProvider(name="Credentials")
	public static Object[][] credentials() throws Exception
	{
		DOMConfigurator.configure("log4j.xml");
		tcName=loginValidation.class.getSimpleName();
		Log.startTestCase(tcName);
		
		ExcelUtils.setExcelFile(Constant.PathTestData+"//login//"+tcName+".xlsx", "loginDetails");
		
		return Utils.populateExcelDataProvider();
	}
	

}
