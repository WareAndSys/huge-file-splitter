/**
 * 
 */
package com.wareandsy.filesplitter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fangbe
 *
 */
public class HugeFileSplitterEvent {
	
	public static enum EventType {FILE_CREATED, FILE_COUNTED}

	private EventType eventType;
	
	private Map<String, Object> parameters;

	/**
	 * 
	 */
	public HugeFileSplitterEvent(EventType eventType) {
		this.eventType = eventType;
		parameters = new HashMap<>(5);
	}
	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void addParameter(String name, Object value){
		parameters.put(name, value);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Object getParameter(String name){
		return  parameters.get(name);
	}
	
	
	
	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}



	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the eventType
	 */
	public EventType getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

}
