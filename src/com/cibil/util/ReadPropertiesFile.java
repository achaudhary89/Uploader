package com.cibil.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {

	public Properties readFile(String fileName) {
		Properties properties = null;
		try {
			File file = new File(fileName);
			FileInputStream fileInput = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public Properties readOrderedFile(String fileName) {
		Properties properties = null;
		try {
			File file = new File(fileName);
			FileInputStream fileInput = new FileInputStream(file);
			properties = new LinkedProperties();
			properties.load(fileInput);
			fileInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}