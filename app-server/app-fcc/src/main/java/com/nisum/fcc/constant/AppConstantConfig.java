package com.nisum.fcc.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value= {
		"classpath:appconstant.properties"
})
public class AppConstantConfig {
	
    @Value("${item.not.found}")
    private String ITEM_NOT_FOUND;

    @Value("${product.id.tokenizer}")
    private String PRODUCT_ID_TOKINIZER;

	/**
	 * @return the iTEM_NOT_FOUND
	 */
	public String getITEM_NOT_FOUND() {
		return ITEM_NOT_FOUND;
	}

	/**
	 * @param iTEM_NOT_FOUND the iTEM_NOT_FOUND to set
	 */
	public void setITEM_NOT_FOUND(String iTEM_NOT_FOUND) {
		ITEM_NOT_FOUND = iTEM_NOT_FOUND;
	}

	/**
	 * @return the pRODUCT_ID_TOKINIZER
	 */
	public String getPRODUCT_ID_TOKINIZER() {
		return PRODUCT_ID_TOKINIZER;
	}

	/**
	 * @param pRODUCT_ID_TOKINIZER the pRODUCT_ID_TOKINIZER to set
	 */
	public void setPRODUCT_ID_TOKINIZER(String pRODUCT_ID_TOKINIZER) {
		PRODUCT_ID_TOKINIZER = pRODUCT_ID_TOKINIZER;
	}

}
