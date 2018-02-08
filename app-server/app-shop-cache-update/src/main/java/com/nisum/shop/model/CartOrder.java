package com.nisum.shop.model;

import java.util.List;

public class CartOrder {
	private String id;
	private List<String> items;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getItems() {
		return items;
	}
	public void setItems(List<String> items) {
		this.items = items;
	}

}
