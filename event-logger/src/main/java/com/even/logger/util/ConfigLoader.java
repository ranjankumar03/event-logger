/**
 * 
 */
package com.even.logger.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ranjan kumar
 *
 */
public class ConfigLoader {

	private static final Logger logger = LoggerFactory
			.getLogger(ConfigLoader.class);

	/**
	 * Loading hsqldb properties
	 * 
	 * @return
	 * @throws IOException
	 */
	public Properties loadConfigurations() throws IOException {

		InputStream inputStream = null;
		Properties prop = null;
		try {
			prop = new Properties();
			String propFileName = "hsqldb.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(
					propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			}
		} 
		catch (IOException e) {
			logger.error("Exception: " + e);
		}catch (Exception e) {
			logger.error("Exception: " + e);
		} finally {
			inputStream.close();
		}

		return prop;
	}
}
