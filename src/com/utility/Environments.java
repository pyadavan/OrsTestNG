package com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environments {

	public static String getURL() throws IOException {

		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "//src//com//config//config.properties");
		Properties pro = new Properties();
		pro.load(fs);
		String url = pro.getProperty("testURL");

		return url;
	}	
}