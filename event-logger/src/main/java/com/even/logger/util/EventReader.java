/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.even.logger.util;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.even.logger.constants.LogEventConstants;
import com.even.logger.pojo.LogEvent;

public class EventReader {

	private static final Logger logger = LoggerFactory
			.getLogger(EventReader.class);

	/**
	 * Reading Input file and building Event object
	 * @param FilePath
	 * @return
	 */
	public Map<String, LogEvent> readInputFile(String FilePath) {

		Map<String, LogEvent> eventHolder = new LinkedHashMap<>();
		File file = new File(FilePath);

		try {
			LineIterator it = FileUtils.lineIterator(file, "UTF-8");
			while (it.hasNext()) {
				String line = it.nextLine();
				JSONObject jsonObject = new JSONObject(line);
				LogEvent logEvent = null;
				if (jsonObject.isNull("type"))
					logEvent = new LogEvent(jsonObject.getString("id"),
							jsonObject.getString("state"),
							jsonObject.getLong("timestamp"), null, null);
				else
					logEvent = new LogEvent(jsonObject.getString("id"),
							jsonObject.getString("state"),
							jsonObject.getLong("timestamp"),
							jsonObject.getString("type"),
							jsonObject.getString("host"));

				logger.info("Incoming Event:" + logEvent);
				if (eventHolder.containsKey(logEvent.getEventId())) {
					LogEvent previousLogEvent = eventHolder.get(logEvent
							.getEventId());
					long newTimestamp = Math.abs(previousLogEvent
							.getTimestamp() - logEvent.getTimestamp());
					logEvent.setTimestamp(newTimestamp);
					if (newTimestamp > LogEventConstants.DURATION_THRESHOLD)
						logEvent.setAlert(LogEventConstants.TRUE_VALUE);
					eventHolder.put(logEvent.getEventId(), logEvent);
				} else {
					eventHolder.put(logEvent.getEventId(), logEvent);
				}
			}
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}
		return eventHolder;
	}

	public boolean someReaderMethod() {
		return true;
	}
}