/**
 * 
 */
package com.even.logger.processor.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.even.logger.dao.ConnectionManager;
import com.even.logger.pojo.LogEvent;

/**
 * @author ranjan kumar
 * 
 *         EventProcessorImpl
 */
public class EventProcessorImpl implements Processor {

	private static final Logger logger = LoggerFactory
			.getLogger(EventProcessorImpl.class);

	@Override
	public void pocessEvents(Map<String, LogEvent> eventHolder) {
		Connection connection = null;

		try {
			connection = ConnectionManager.getConnection();
			Statement stmt = connection.createStatement();
			int result = 0;
			for (String id : eventHolder.keySet()) {
				LogEvent eventToBePersisted = eventHolder.get(id);
				String query = "INSERT INTO EVENT (event_id,event_duration,event_type,event_host,alert) "
						+ "VALUES ("
						+ "'"
						+ eventToBePersisted.getEventId()
						+ "',"
						+ eventToBePersisted.getTimestamp()
						+ ","
						+ "'"
						+ eventToBePersisted.getEventType()
						+ "',"
						+ "'"
						+ eventToBePersisted.getEventHost()
						+ "',"
						+ eventToBePersisted.getAlert() + ")";
				logger.info("Sql Query->" + query);
				result = stmt.executeUpdate(query);
			}
			connection.commit();
			if (result > 0) {
				logger.info("Events inserted into hsqldb");
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
	}
}
