/**
 * 
 */
package com.even.logger.processor.impl;

import java.util.Map;

import com.even.logger.pojo.LogEvent;

/**
 * @author ranjan kumar
 *
 */
@FunctionalInterface
public interface Processor {

	public void pocessEvents(Map<String, LogEvent> eventHolder);
}
