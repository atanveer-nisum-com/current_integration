package com.nisum.common.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.nisum.common.event.message.dto.EtaEvent;
/**
 * 
 * @author omussawir
 *
 */

public class Utils {

	private static Gson gson = new Gson();
	
	public static final String convertObjectToJson(Object dto){
		return gson.toJson(dto);	
	}

	public static final <T> T convertJsonToObject(String payload , Class<T> object){
		return gson.fromJson(payload, (Type)object);	
	}
}
