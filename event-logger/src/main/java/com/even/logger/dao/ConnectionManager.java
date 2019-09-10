/**
 * 
 */
package com.even.logger.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.even.logger.util.ConfigLoader;

/**
 * @author ranjan kumar 
 * HSQLDB connection
 *
 */
public class ConnectionManager {

	private static final Logger logger = LoggerFactory
			.getLogger(ConnectionManager.class);

	/**
	 * Connecting to database and returning connection for executing db specific
	 * operations
	 * 
	 * @return connection
	 */
	public static Connection getConnection() {

		ConfigLoader loader = new ConfigLoader();
		Properties properties = null;
		;
		try {
			properties = loader.loadConfigurations();
		} catch (IOException e) {
			logger.error(e.getStackTrace().toString());
		}
		Connection connection = null;
		try {
			String url = properties.getProperty("hsqldb.url");
			String user = properties.getProperty("hsqldb.user");
			String password = properties.getProperty("hsqldb.password");
			logger.info("Connecting to hsqlDB with db:" + url);
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			logger.error(e.getStackTrace().toString());
		}
		return connection;
	}
}
