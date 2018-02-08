package com.nisum.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:endpoints.properties" )
@Component
public class CommonEndPointConstant {

	public static String APP_SHOP_BASE_URL;
    public static  String MERGE_GUEST_USER;
    public static  String APP_FCC_BASE_URL;
    public static  String PRODUCT_FIND_ONE;
    
    public static String APP_USER_BASE_URL;
	
	public static String USER_FIND_ONE ;
	
	public static  String USER_IS_ALIVE ;
	
	public static String STATE_FIND_BY_ID ;
	
	public static String COUNTRY_FIND_BY_ID ;
	
	public static String CHECKOUT_SAVE_USER ;
	
	public static String SAVE_GUEST ;
	
	public static String AUTHORIZE_TOKEN;
	
    public static  String CART_PRODUCT_FIND_ONE;



	/**
	 * @param aPP_USER_BASE_URL the aPP_USER_BASE_URL to set
	 */
	@Value("${APP_USER_BASE_URL}")
	public void setAPP_USER_BASE_URL(String aPP_USER_BASE_URL) {
		APP_USER_BASE_URL = aPP_USER_BASE_URL;
	}


	/**
	 * @param uSER_FIND_ONE the uSER_FIND_ONE to set
	 */
	@Value("${USER_FIND_ONE}")
	public void setUSER_FIND_ONE(String uSER_FIND_ONE) {
		USER_FIND_ONE = APP_USER_BASE_URL + uSER_FIND_ONE ;
	}


	/**
	 * @param uSER_IS_ALIVE the uSER_IS_ALIVE to set
	 */
	@Value("${USER_IS_ALIVE}")
	public void setUSER_IS_ALIVE(String uSER_IS_ALIVE) {
		USER_IS_ALIVE =APP_USER_BASE_URL +  uSER_IS_ALIVE;
	}


	/**
	 * @param sTATE_FIND_BY_ID the sTATE_FIND_BY_ID to set
	 */
	@Value("${STATE_FIND_BY_ID}")
	public void setSTATE_FIND_BY_ID(String sTATE_FIND_BY_ID) {
		STATE_FIND_BY_ID = APP_USER_BASE_URL + sTATE_FIND_BY_ID;
	}


	/**
	 * @param cOUNTRY_FIND_BY_ID the cOUNTRY_FIND_BY_ID to set
	 */
	@Value("${COUNTRY_FIND_BY_ID}")
	public void setCOUNTRY_FIND_BY_ID(String cOUNTRY_FIND_BY_ID) {
		COUNTRY_FIND_BY_ID = APP_USER_BASE_URL + cOUNTRY_FIND_BY_ID;
	}


	/**
	 * @param cHECKOUT_SAVE_USER the cHECKOUT_SAVE_USER to set
	 */
	@Value("${CHECKOUT_SAVE_USER}")
	public void setCHECKOUT_SAVE_USER(String cHECKOUT_SAVE_USER) {
		CHECKOUT_SAVE_USER = APP_USER_BASE_URL + cHECKOUT_SAVE_USER;
	}


	/**
	 * @param sAVE_GUEST the sAVE_GUEST to set
	 */
	@Value("${SAVE_GUEST}")
	public void setSAVE_GUEST(String sAVE_GUEST) {
		SAVE_GUEST =  APP_USER_BASE_URL+sAVE_GUEST;
	}


	/**
	 * @param aPP_SHOP_BASE_URL the aPP_SHOP_BASE_URL to set
	 */
	@Value("${shop.url}")
	public void setAPP_SHOP_BASE_URL(String aPP_SHOP_BASE_URL) {
		APP_SHOP_BASE_URL = aPP_SHOP_BASE_URL;
	}


	/**
	 * @param aPP_FCC_BASE_URL the aPP_FCC_BASE_URL to set
	 */
	@Value("${fcc.url}")
	public void setAPP_FCC_BASE_URL(String aPP_FCC_BASE_URL) {
		APP_FCC_BASE_URL = aPP_FCC_BASE_URL;
	}

	/**
	 * @param mERGE_GUEST_USER the mERGE_GUEST_USER to set
	 */
	@Value("${merge.api.endpoint}")
	public void setMERGE_GUEST_USER(String mERGE_GUEST_USER) {
		MERGE_GUEST_USER = APP_SHOP_BASE_URL + mERGE_GUEST_USER;
	}

	

	

	

	/**
	 * @param pRODUCT_FIND_ONE the pRODUCT_FIND_ONE to set
	 */
	@Value("${product.api.endpoint}")
	public void setPRODUCT_FIND_ONE(String pRODUCT_FIND_ONE) {
		PRODUCT_FIND_ONE =APP_FCC_BASE_URL + pRODUCT_FIND_ONE ;
	}

	
	

	/**
	 * @param aUTHORIZE_TOKEN the aUTHORIZE_TOKEN to set
	 */
	@Value("${AUTHORIZE_TOKEN}")
	public void setAUTHORIZE_TOKEN(String aUTHORIZE_TOKEN) {
		AUTHORIZE_TOKEN = aUTHORIZE_TOKEN;
	}


	/**
	 * @return the mERGE_GUEST_USER
	 */
	public static String getMERGE_GUEST_USER() {
		return MERGE_GUEST_USER;
	}

	
	/**
	 * @return the pRODUCT_FIND_ONE
	 */
	public static String getPRODUCT_FIND_ONE() {
		return PRODUCT_FIND_ONE;
	}

	/**
	 * @param cart_PRODUCT_FIND_ONE the pRODUCT_FIND_ONE to set
	 */
	@Value("${cart.product.api.endpoint}")
	public void setCART_PRODUCT_FIND_ONE(String cART_PRODUCT_FIND_ONE) {
		CART_PRODUCT_FIND_ONE = APP_FCC_BASE_URL + cART_PRODUCT_FIND_ONE;
	}






    
	
	
	
 	

}
