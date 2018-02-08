package com.nisum.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nisum.common.util.BaseEntity;

@Entity
@Table(name="event")
@JsonIgnoreProperties({ "appModules", "hibernateLazyInitializer", "handler" })
public class Event extends BaseEntity implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -12345L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",unique = true, nullable = false)
	private Long id;
	
	/** The name. */
	@Column(name="payload", columnDefinition = "TEXT")
	private String payload;
	
	/** The processed. */
	@Column(name="is_processed", length = 1)
	private int isProcessed;
	
	/** The User bean. */
	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	/** The AppModules bean. */
	// bi-directional many-to-one association to Module
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id")
	private AppModules appModules;
	
	/** The EventType bean. */
	// bi-directional many-to-one association to Event Type
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private EventType eventType;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public int getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(int isProcessed) {
		this.isProcessed = isProcessed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AppModules getAppModules() {
		return appModules;
	}

	public void setAppModules(AppModules appModules) {
		this.appModules = appModules;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
