package com.nisum.common.constant;

public enum EventTypeEnum {

	CART_EVENT("CART_EVENT", "Order processing Events."), 
	USER_EVENT("USER_EVENT", "User related Events."),
	CHECKOUT_EVENT("CHECKOUT_EVENT","Check out related Events"),
	ADD_TO_CART("ADD_TO_CART","add item to cart"),
	PLACE_ORDER("PLACE_ORDER","place order at time of chekout"),
	UPDATE_USER("UPDATE_USER", "update user at checkout");
    private final String key;
    private final String description;

    EventTypeEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }
    public String getValue() {
        return description;
    }
}
