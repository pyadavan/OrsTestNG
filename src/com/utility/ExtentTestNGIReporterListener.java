package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utility.Global_VARS;

public class ExtentTestNGIReporterListener implements IReporter {

	private String bitmapDir1 = Global_VARS.BITMAP_PATH;
	private String seleniumRev = "3.7.1";
	private String docTitle = "Selenium framework design in Data Driven Testing";
	private ExtentReports extent;

	/**
	 * generateReport
	 * 
	 * @param xmlSuites
	 * @param suites
	 * @param outputDirectory
	 */
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		for (ISuite suite : suites) {
			init(suite);
			Map<String, ISuiteResult> results = suite.getResults();
			for (ISuiteResult result : results.values()) {
				try {
					processTestResults(result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		extent.flush();
		
		
		InputStream input = null;
		
	    OutputStream output = null;

	    try {
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
			Calendar calobj = Calendar.getInstance();
	        input = new FileInputStream(Global_VARS.REPORT_PATH + "AutomationSuite" + ".html");
	
	        output = new FileOutputStream(Global_VARS.REPORT_PATH + "AutomationSuite" +df.format(calobj.getTime()) + ".html");
	
	        byte[] buf = new byte[1024];
	
	        int bytesRead;
	
	        while ((bytesRead = input.read(buf)) > 0) {
	
	            output.write(buf, 0, bytesRead);
	
	        }
	
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	
	        try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	        try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	    }
		
		
		
	}

	/**
	 * init method to customize report
	 * 
	 * @param suite
	 */
	private void init(ISuite suite) {
		File directory = new File(Global_VARS.REPORT_PATH);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				Global_VARS.REPORT_PATH + suite.getName() + ".html");

		// report attributes
		htmlReporter.config().setDocumentTitle(docTitle);
		htmlReporter.config().setReportName(suite.getName().replace("_", ""));
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setProtocol(Protocol.HTTPS);
		htmlReporter.config().setTimeStampFormat("MM-dd-yyyy HH:mm:ss a");
		htmlReporter.loadXMLConfig(new File(Global_VARS.REPORT_CONFIG_FILE));
		extent = new ExtentReports();
		// report system info
		extent.setSystemInfo("Browser", Global_VARS.BROWSER);
		extent.setSystemInfo("Platform", Global_VARS.PLATFORM);
		extent.setSystemInfo("OS Version", System.getProperty("os.version"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("Selenium Version", seleniumRev);
		try {
			extent.setSystemInfo("Site URL - 1", WebdriverUtils.getProperty("UATtestURL_AU"));
		} catch (Exception e) {
			System.out.println("Site URL not specified properly.");
			e.printStackTrace();
		}
		try {
			extent.setSystemInfo("Site URL - 1", WebdriverUtils.getProperty("UATtestURL_NZ"));
		} catch (Exception e) {
			System.out.println("Site URL not specified properly.");
			e.printStackTrace();
		}

		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);
	}

	private void processTestResults(ISuiteResult r) throws Exception {
		ExtentTest test = null;
		Status status = null;
		String message = null;

		// gather results
		Set<ITestResult> passed = r.getTestContext().getPassedTests()
				.getAllResults();
		Set<ITestResult> failed = r.getTestContext().getFailedTests()
				.getAllResults();
		Set<ITestResult> skipped = r.getTestContext().getFailedTests()
				.getAllResults();
//		Set<ITestResult> configs = r.getTestContext().getFailedConfigurations()
//				.getAllResults();

		Set<ITestResult> tests = new HashSet<ITestResult>();

		tests.addAll(passed);
		tests.addAll(skipped);
		tests.addAll(failed);

		// process results
		if (tests.size() > 0) {
			// sort results by the Date field
			List<ITestResult> resultList = new LinkedList<ITestResult>(tests);

			class ResultComparator implements Comparator<ITestResult> {
				public int compare(ITestResult r1, ITestResult r2) {
					return getTime(r1.getStartMillis()).compareTo(
							getTime(r2.getStartMillis()));
				}

			}
			Collections.sort(resultList, new ResultComparator());
			for (ITestResult result : resultList) {
//				if (getTestParams(result).isEmpty()) {
					test = extent
							.createTest(result.getMethod().getDescription());
//				} else {
//					if (getTestParams(result).split(",")[0].contains(result
//							.getMethod().getMethodName())) {
//						test = extent.createTest(
//								getTestParams(result).split(",")[0],
//								getTestParams(result).split(",")[1]);
//					} else {
//						test = extent.createTest(result.getMethod()
//								.getMethodName(),
//								getTestParams(result).split(",")[1]);
//					}
//				}

				test.getModel().setStartTime(getTime(result.getStartMillis()));
				test.getModel().setEndTime(getTime(result.getEndMillis()));
				
				
				
				//String packageName = className.substring(0, className.lastIndexOf('.'));
				//dynamicClass = cl.loadClass(packageName);
				
				int size = result.getMethod().getTestClass().toString()
						.split("\\.").length;
				String testName = result.getMethod().getRealClass()
						.getName().toString().split("\\.")[size - 1];
				
				
				
				
				test.assignCategory(testName);

//				for (String group : result.getMethod().getGroups()) {
//					if (!group.isEmpty()) {
//						test.assignCategory(group);
//					} else {
//						int size = result.getMethod().getTestClass().toString()
//								.split("\\.").length;
//						String testName = result.getMethod().getRealClass()
//								.getName().toString().split("\\.")[size - 1];
//						test.assignCategory(testName);
//					}
//				}
				// get Status

				switch (result.getStatus()) {
				case 1:
					status = Status.PASS;
					break;
				case 2:
					status = Status.FAIL;
					break;
				case 3:
					status = Status.SKIP;
					break;
				default:
					status = Status.INFO;
				}

				// Set Colours of status
				if (status.equals(Status.PASS)) {
					message = "<font colour=#00af00>"
							+ status.toString().toUpperCase() + "</font>";
				} else if (status.equals(Status.FAIL)) {
					message = "<font colour=#F7464A>"
							+ status.toString().toUpperCase() + "</font>";
				} else if (status.equals(Status.SKIP)) {
					message = "<font colour=#2196F3>"
							+ status.toString().toUpperCase() + "</font>";
				} else {
					message = "<font colour=black>"
							+ status.toString().toUpperCase() + "</font>";
				}

				// log status in report

				test.log(status, message);

				if (!getTestParams(result).isEmpty()) {
					test.log(Status.INFO, "TEST DESCRIPTION= ["
							+ result.getMethod().getDescription() + "]");
				}
				
				
					if (result.getThrowable() != null) {
					String	exceptionMessage = "<font colour=#F7464A>"
								+ result.getThrowable().getMessage() + "</font>";
						test.log(Status.INFO, "EXCEPTION = ["
								+ exceptionMessage + "]");	
				
				
//				String screenshotpath=bitmapDir+result.getMethod().getMethodName()+".png";
//				System.out.println(screenshotpath);
//				test.log(Status.INFO, "SCREENSHOT "+test.addScreenCaptureFromPath(screenshotpath));
				
				
				
				

				
				
//				if (!getTestParams(result).isEmpty()) {
					// must capture screenshot to include in report
						String bitmapDir;
						if(Browsers.get().contains("IE")||Browsers.get().contains("ie")) {
							 bitmapDir=bitmapDir1.replace("C:\\Users\\shamsundar.br\\.jenkins\\workspace", "//192.168.101.18//workspace2//");
						}
						else 
						//changing the URL of the screen shot which should be included in the HTML report
						 bitmapDir=bitmapDir1.replace("C:\\Program Files (x86)\\Jenkins\\workspace\\", "//192.168.101.18//workspace//");
						
					test.log(
							Status.INFO,
							"SCREENSHOT",
							MediaEntityBuilder
									.createScreenCaptureFromPath(
											bitmapDir
													+ result.getMethod().getMethodName()+".png")
									.build());
					
					test.log(Status.INFO, "STACKTRACE "+getStrackTrace(result));
					}
				
				
				}
				
					
				}

			}
			
		
			
			
			/**getTime method to retrieve current date/time
			 * @param millis
			 * @param Date
			 */
			
			private Date getTime(long millis){
			Calendar calender=Calendar.getInstance();
			calender.setTimeInMillis(millis);
			return calender.getTime();
			}
			
			
			/**
			 * getTestParams method to retrieve test Parameters
			 * 
			 * @param tr
			 * @return String
			 * @throws Exception
			 */
			private String getTestParams(ITestResult tr) throws Exception{
				TestNG_ConsoleRunner runner=new TestNG_ConsoleRunner();
				return runner.getTestParams(tr);
			}
			
			/**
			 * getStrackTrace method to retrieve stack trace
			 * 
			 * @param result
			 * @return
			 */
			
			private String getStrackTrace(ITestResult result){
			Writer writer=new StringWriter();
			PrintWriter printWriter=new PrintWriter(writer);
			result.getThrowable().printStackTrace(printWriter);
			
			return "<br/>\n" + writer.toString().replace(System.lineSeparator(), "<br/>\n");
			
			}
			
			
			
		}

	


