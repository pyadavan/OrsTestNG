package com.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.utility.Global_VARS;

public class TestNG_ConsoleRunner extends TestListenerAdapter {

	public static String logFile = null;
	
	/**
	 * onStart -method to log data before any tests start
	 * 
	 * @param testContest
	 */
	@Override
	public void onStart(ITestContext testContext) {
		try {
			log("\nSuite Start Date: "
					+ new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss")
							.format(new Date()) + ".log");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onStart(testContext);
	}

	/**
	 * onFinish- method to log data after all tests are complete
	 * 
	 * @param testContest
	 */
	@Override
	public void onFinish(ITestContext testContext) {
		try {
			log("\nTotal Passed = " + getPassedTests().size()
					+ ", Total Failed = " + getFailedTests().size()
					+ ", Total Skipped = " + getSkippedTests().size() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onFinish(testContext);
	}

	/**
	 * onTestStartMethod
	 * 
	 * @param tr
	 */
	@Override
	public void onTestStart(ITestResult tr) {
		try{
		if (logFile == null) {
			logFile = Global_VARS.LOGFILE_PATH
					+ Global_VARS.SUITENAME
					+ "-"
					+ new SimpleDateFormat("MM.dd.yy.HH.mm.ss")
							.format(new Date()) + ".log";
		}

		log("\n-----------------------------------------Test '" + tr.getName()
				+ getTestDescription(tr) + "'------------------------------\n");
		
		log(tr.getStartMillis(),"Start-> "+tr.getName()+"\n");
		log(" ********Test Description = "+getTestParams(tr)+"\n");
		}
		catch(Exception e){
		e.printStackTrace();
		}
		super.onTestStart(tr);
		
	}

	/**
	 * onTestSuccess -method to log the results if the test passes
	 * 
	 * @param tr
	 */
	@Override
	public void onTestSuccess(ITestResult tr) {
		try {
			log("*****Result = PASSED\n");
			log(tr.getEndMillis(),
					"END -> " + tr.getName());
			log("\n----\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onTestSuccess(tr);
	}
	
	
	
	
	
	
	/**
	 * onTestFailure Method
	 * @param tr
	 */
	@Override
	public void onTestFailure(ITestResult tr){
		try{
	if(!getTestMessage(tr).equals(""))	{
		log(getTestMessage(tr)+"\n");
	}
	
	log("*****************Result = FAILED\n");
	log(tr.getEndMillis(),"END ->"+tr.getInstanceName()+"."+tr.getName());
	log("\n--------------\n");
	
	ImageCapture.screenShot(tr);
	
	
	
	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	super.onTestFailure(tr);
	}
	

	
	
	
	/**
	 * onTestSkipped Method
	 * @param tr
	 */
	@Override
	public void onTestSkipped(ITestResult tr){
		try{
	if(!getTestMessage(tr).equals(""))	{
		log(getTestMessage(tr)+"\n");
	}
	
	log("*****************Result = SKIPPED\n");
	log(tr.getEndMillis(),"END ->"+tr.getInstanceName()+"."+tr.getName());
	log("\n--------------\n");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	super.onTestSkipped(tr);
	}
	
	
	@Override
	public void onConfigurationSuccess(ITestResult itr){
	super.onConfigurationSuccess(itr);	
	}
	
	
	
	@Override
	public void onConfigurationFailure(ITestResult tr){
		try{
		if(!getTestMessage(tr).equals("")){
			log(getTestMessage(tr)+"\n");
		}
		log("          ***Result = CONFIGURATION FAILED\n");
		log(tr.getEndMillis(),"END CONFIG ->"+tr.getInstanceName()+"."+tr.getName());
		log("\n--------------\n");
		}
		catch(Exception e){
		e.printStackTrace();	
		}
	super.onConfigurationFailure(tr);	
	}
	
	@Override
	public void onConfigurationSkip(ITestResult tr){
		try{
	    log(getTestMessage(tr)+"\n");
		log("          ***Result = CONFIGURATION SKIPPED\n");
		log(tr.getEndMillis(),"END CONFIG ->"+tr.getInstanceName()+"."+tr.getName());
		log("\n--------------\n");
		}
		catch(Exception e){
		e.printStackTrace();
		}
	super.onConfigurationSkip(tr);	
	}
	
	/**
	 * log- method to log data to standard out or logfile
	 * 
	 * @param dateMillis
	 * @param line
	 */
	public void log(long dateMillis, String line) throws Exception {
		System.out.format("%s: %s%n", String.valueOf(new Date(dateMillis)), line);
		if (logFile != null) {
			writeTestngLog(logFile, line);
		}
	}

	/**
	 * log- overloaded method to log data to standard out or logfile
	 * 
	 * @param line
	 * 
	 */
	public void log(String line) throws Exception {
		System.out.format("%s%n" , line);
		if (logFile != null) {
			writeTestngLog(logFile, line);
		}
	}
	
	
	public String getTestMessage(ITestResult tr){
		Boolean found=false;
		if(tr != null && tr.getThrowable() != null){
			found=true;
		}
		if(found == true){
			return tr.getThrowable().getMessage() == null ? "" : tr.getThrowable().getMessage();
		}
		else {
			return"";
		}
		
	}
	
	public String getTestParams(ITestResult tr){
		int iLength=tr.getParameters().length;
		String message="";
		message=tr.getMethod().getDescription();
		try{
			if(tr.getParameters().length > 0){
			message=tr.getParameters()[0].toString();
			System.out.println(message);
			for(int iCount=0; iCount<iLength ; iCount++){
				if(iCount==0)
				message=tr.getParameters()[1].toString();
				else{
				message=message+ ", "+tr.getParameters()[iCount].toString()	;

				}
			}
			}
		}
		catch(Exception e){
		//do nothing
		}
		return message;
	}
	
	
	public String getTestDescription(ITestResult tr){
		String message="";
		
		
		try{
			if(tr.getParameters().length > 0)
			message=": "+tr.getParameters()[1].toString();
			}
			
		catch(Exception e){
		//do nothing
		}
		return message;
	}

	/**
	 * extractData_LOG - method to extract Log File data for use in Testing
	 * 
	 * @param logFile
	 *            - the logfile to read
	 * @return List<String>
	 * @throws IOException
	 */
	public static List<String> extractData_LOG(String logFile)
			throws IOException {
		List<String> rows = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(logFile));
		String line = "";
		while ((line = reader.readLine()) != null) {
			rows.add(line);
		}
		reader.close();
		return rows;
	}

	/**
	 * writeFile- method to stuff a row entry into a file
	 * 
	 * @param file
	 *            - the file to write to
	 * @param rowData
	 *            - the line to write into the file
	 * @throws Exception
	 */
	public static void writeTestngLog(String logFile, String line) {
		
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date=new Date();
		File directory=new File(Global_VARS.LOGFILE_PATH);
		File file=new File(logFile);
		try{
			if(!directory.exists()){
				directory.mkdirs();
			}
			else if(!file.exists()){
				file.createNewFile();
			}
			BufferedWriter writer=new BufferedWriter(new FileWriter(logFile,true));
			if(line.contains("START") || line.contains("END")){
				writer.append("["+dateFormat.format(date)+"] "+line);
			}
			else{
				writer.append(line);
			}
			writer.newLine();
			writer.close();
		}
		catch(IOException e){
			//Do nothing
		}
}
}