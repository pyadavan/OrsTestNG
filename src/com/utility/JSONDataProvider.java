package com.utility;


import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;



public class JSONDataProvider {

	public static String dataFile ="";
	public static String testCaseName = "NA";

	public JSONDataProvider() throws Exception {

	}

	/**
	 * fetchData method to retrieve test data for specified method
	 * 
	 * @param method
	 * @param Object[]
	 *            []
	 * @throws Exception
	 */

	@DataProvider(name = "fetchData_JSON")
	public static Object[][] fetchData(Method method) throws Exception {
		Object rowID, description;
		Object[][] result;
		testCaseName = method.getName();
		List<JSONObject> testDataList = new ArrayList<JSONObject>();
		JSONArray testData = (JSONArray) extractData_JSON(dataFile).get(method.getName());

		for (int i = 0; i < testData.size(); i++) {
			testDataList.add((JSONObject) testData.get(i));
		}

		// create object for dataprovider to return

		try {
			result = new Object[testDataList.size()][testDataList.get(0).size()];

			for (int i = 0; i < testDataList.size(); i++) {
				rowID = testDataList.get(i).get("rowID");
				description = testDataList.get(i).get("description");
				result[i] = new Object[] { rowID, description, testDataList.get(i) };

			}

		}

		catch (IndexOutOfBoundsException ie) {
			result = new Object[0][0];

		}
    return result;
	}
	
	
/**
 * extractData_JSON method to get JSON data from file
 * 
 * @param file
 * @param JSONObject
 * @throws Exception
 * 
 * 	
 */

	public static JSONObject extractData_JSON(String file) throws Exception {
		System.out.println(file);
	FileReader reader=new FileReader(file);	
	JSONParser jsonParser = new JSONParser();
	return (JSONObject) jsonParser.parse(reader);
	
	}
	
	
}
