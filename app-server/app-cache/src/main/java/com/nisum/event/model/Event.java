package com.nisum.event.model;

import java.io.Serializable;

public class Event implements Serializable{

	private static final long serialVersionUID = 696077954345028201L;
	private String objectType;
	private String eventType;
	private String object;
	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Event() {
	}
	
	public Event(String objectType , String object) {
		this.objectType = objectType;
		this.object = object;
	}
	
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	
	
}
