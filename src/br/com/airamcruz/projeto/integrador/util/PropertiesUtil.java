package br.com.airamcruz.projeto.integrador.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Leonardo Airam Muniz Cruz
 * 
 */

public class PropertiesUtil {
	
	public static String getProperty(String file, String property) {
		
		String result = "";
		
		InputStream inputStream = null;
		
		try {
			inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("properties/" + file + ".properties");
			
			Properties prop = new Properties();
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + file + ".properties' not found in the classpath");
			}
			
			result = prop.getProperty(property);
			inputStream.close();
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		return result;
	}

	public static Properties loadProperties(String file) {
		
		InputStream inputStream = null;
		
		try {
			inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("properties/" + file + ".properties");
			
			Properties prop = new Properties();
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + file + ".properties' not found in the classpath");
			}
			inputStream.close();
			return prop;
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		return null;
	}
}
