package com.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class File_IO {

/**
 * loadProps- method to load a Properties file
 * @param file- The File to load
 * @return Properties- the properties to retrieve
 * @throws Exception
 */
public static Properties loadProps(String file) throws Exception{
Properties props=new Properties();
props.load(new FileInputStream(file));
return props;
}

}
