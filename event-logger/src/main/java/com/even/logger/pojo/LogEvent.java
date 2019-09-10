/**
 * 
 */
package com.even.logger.pojo;

/**
 * @author ranjan kumar
 *
 */
public class LogEvent {

	String eventId, eventState, eventType, eventHost;
	Long timestamp;
	Boolean alert = null;

	public LogEvent(String eventId, String eventState, Long timestamp,
			String eventType, String eventHost, Boolean alert) {
		this.eventId = eventId;
		this.eventState = eventState;
		this.timestamp = timestamp;
		this.eventType = eventType;
		this.eventHost = eventHost;
		this.alert = alert;
	}

	public LogEvent(String eventId, String eventState, long timestamp,
			String eventType, String eventHost) {
		this.eventId = eventId;
		this.eventState = eventState;
		this.timestamp = timestamp;
		this.eventType = eventType;
		this.eventHost = eventHost;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventState() {
		return eventState;
	}

	public void setEventState(String eventState) {
		this.eventState = eventState;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventHost() {
		return eventHost;
	}

	public void setEventHost(String eventHost) {
		this.eventHost = eventHost;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getAlert() {
		return alert;
	}

	public void setAlert(Boolean alert) {
		this.alert = alert;
	}

	@Override
	public String toString() {
		return "LogEvent [eventId=" + eventId + ", eventState=" + eventState
				+ ", eventType=" + eventType + ", eventHost=" + eventHost
				+ ", timestamp=" + timestamp + ", alert=" + alert + "]";
	}

}
