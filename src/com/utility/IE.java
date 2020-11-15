package com.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class IE {
	
	public static void main(String[] args) throws Exception {
		
			String BROWSER = "IE";
		
			Properties properties = new Properties();
			String path =System.getProperty("user.dir")+"\\src\\com\\config\\browser.properties";
			try(OutputStream outputStream = new FileOutputStream(path)){  
			    properties.setProperty("Browser", BROWSER);
			    properties.store(outputStream, null);
			    System.out.println(BROWSER+" browser set");
			} catch (IOException e) {
			    e.printStackTrace();
			} 
			
			System.out.println(Utils.getProperty("Browser"));
		
	}

}
