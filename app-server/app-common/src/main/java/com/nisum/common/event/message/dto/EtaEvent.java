package com.nisum.common.event.message.dto;

import java.io.Serializable;

import com.nisum.common.constant.EventTypeEnum;
import com.nisum.common.shop.dto.ShopBaseDTO;


public class EtaEvent implements Serializable{

	private static final long serialVersionUID = 696077954345028201L;
	private String objectType;
	private String eventType;
	private String payload;
	private String actionType;


	public EtaEvent(Class<? extends ShopBaseDTO> objectType, EventTypeEnum eventType, String payload,EventTypeEnum actionType){
		this.objectType = objectType.getSimpleName();
		this.eventType = eventType.getKey();
		this.payload = payload;
		this.actionType = actionType.getKey();
	}
	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public EtaEvent() {
	}
	
	public EtaEvent(String objectType , String object) {
		this.objectType = objectType;
		this.payload = object;
	}
	
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
	

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	
}
