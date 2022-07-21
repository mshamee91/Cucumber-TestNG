package com.qa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	Properties prop;

	public Properties initProperties() throws IOException {
		prop = new Properties();
		FileInputStream inStream = new FileInputStream("src/test/resources/config/config.properties");
		prop.load(inStream);
		return prop;
	}

}
