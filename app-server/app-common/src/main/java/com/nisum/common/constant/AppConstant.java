package com.nisum.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * The Class AppConstant.
 */

@Configuration
@PropertySource("classpath:appconstants.properties" )
@Component
public class AppConstant {

	/** The Constant ITEM_NOT_FOUND. */
	
	public static String ITEM_NOT_FOUND;

	/** The Constant IS_NORMAL_USER. */
	
	public static byte IS_NORMAL_USER;

	/** The Constant IS_GUEST_USER. */
	public static byte IS_GUEST_USER;

	/** The Constant NOT_DELETED. */
	public static byte NOT_DELETED;

	/** The Constant IS_DELETED. */
	public static byte IS_DELETED ;

	/** The Constant RESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS. */
	public static int RESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS ;

	/** The Constant LOGIN_TOKEN_VALIDITY_IN_DAYS. */
	public static int LOGIN_TOKEN_VALIDITY_IN_DAYS ;

	/** The Constant LOGIN_TOKEN_VALIDITY_IN_DAYS. */
	public static int LOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS ;

	/** The Constant REST_RESPONSE_SUCCESS. */
	public static String REST_RESPONSE_SUCCESS ;

	/** The Constant REST_RESPONSE_ERROR. */
	public static String REST_RESPONSE_ERROR ;

	/** The Constant CONTEXT_PATH_DEV. */
	public static String CONTEXT_PATH_DEV ;

	/** The Constant API_USER. */
	public static String API_USER;

	/** The Constant PAYPAL_KEY. */
	public static String PAYPAL_KEY;

	/** The Constant PAYPAL_SECRET. */
	public static String PAYPAL_SECRET;

	/** The Constant PAYPAL_ENV. */
	public static String PAYPAL_ENV ;
	
	/** The Constant ZIP_CODE_VALIDATION_SERVICE. */
	public static String ZIP_CODE_VALIDATION_SERVICE;
	
	/** The Constant USER_ORDERS_FOR_LAST_SEVEN_DAYS. */
	public static int USER_ORDERS_FOR_LAST_SEVEN_DAYS ;
	
	/** The Constant DURATION_OF_ONE_DAY. */
	public static int DURATION_OF_ONE_DAY ;
	
	/** The Constant THREADS_COUNT. */
	public static int THREADS_COUNT = 100;
	
	/** The Constant PRODUCT_ID_TOKINIZER. */
	public static String PRODUCT_ID_TOKINIZER;
	
	/** The Constant FRONTEND_FORGET_PASSWORD. */
	public static String FRONTEND_FORGET_PASSWORD;

	/**
	 * @return the iTEM_NOT_FOUND
	 */
	public String getITEM_NOT_FOUND() {
		return ITEM_NOT_FOUND;
	}

	/**
	 * @param iTEM_NOT_FOUND the iTEM_NOT_FOUND to set
	 */
	@Value("${ITEM_NOT_FOUND}")
	public void setITEM_NOT_FOUND(String iTEM_NOT_FOUND) {
		ITEM_NOT_FOUND = iTEM_NOT_FOUND;
	}

	/**
	 * @return the iS_NORMAL_USER
	 */
	public byte getIS_NORMAL_USER() {
		return IS_NORMAL_USER;
	}

	/**
	 * @param iS_NORMAL_USER the iS_NORMAL_USER to set
	 */
	@Value("${IS_NORMAL_USER}")
	public void setIS_NORMAL_USER(byte iS_NORMAL_USER) {
		IS_NORMAL_USER = iS_NORMAL_USER;
	}

	/**
	 * @return the iS_GUEST_USER
	 */
	public byte getIS_GUEST_USER() {
		return IS_GUEST_USER;
	}

	/**
	 * @param iS_GUEST_USER the iS_GUEST_USER to set
	 */
	@Value("${IS_GUEST_USER}")
	public void setIS_GUEST_USER(byte iS_GUEST_USER) {
		IS_GUEST_USER = iS_GUEST_USER;
	}

	/**
	 * @return the nOT_DELETED
	 */
	public byte getNOT_DELETED() {
		return NOT_DELETED;
	}

	/**
	 * @param nOT_DELETED the nOT_DELETED to set
	 */
	@Value("${NOT_DELETED}")
	public void setNOT_DELETED(byte nOT_DELETED) {
		NOT_DELETED = nOT_DELETED;
	}

	/**
	 * @return the iS_DELETED
	 */
	public byte getIS_DELETED() {
		return IS_DELETED;
	}

	/**
	 * @param iS_DELETED the iS_DELETED to set
	 */
	@Value("${IS_DELETED}")
	public void setIS_DELETED(byte iS_DELETED) {
		IS_DELETED = iS_DELETED;
	}

	/**
	 * @return the rESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS
	 */
	public int getRESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS() {
		return RESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS;
	}

	/**
	 * @param rESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS the rESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS to set
	 */
	@Value("${RESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS}")
	public void setRESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS(int rESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS) {
		RESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS = rESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS;
	}

	/**
	 * @return the lOGIN_TOKEN_VALIDITY_IN_DAYS
	 */
	public int getLOGIN_TOKEN_VALIDITY_IN_DAYS() {
		return LOGIN_TOKEN_VALIDITY_IN_DAYS;
	}

	/**
	 * @param lOGIN_TOKEN_VALIDITY_IN_DAYS the lOGIN_TOKEN_VALIDITY_IN_DAYS to set
	 */
	@Value("${LOGIN_TOKEN_VALIDITY_IN_DAYS}")
	public void setLOGIN_TOKEN_VALIDITY_IN_DAYS(int lOGIN_TOKEN_VALIDITY_IN_DAYS) {
		LOGIN_TOKEN_VALIDITY_IN_DAYS = lOGIN_TOKEN_VALIDITY_IN_DAYS;
	}

	/**
	 * @return the lOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS
	 */
	public int getLOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS() {
		return LOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS;
	}

	/**
	 * @param lOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS the lOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS to set
	 */
	@Value("${LOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS}")
	public void setLOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS(int lOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS) {
		LOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS = lOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS;
	}

	/**
	 * @return the rEST_RESPONSE_SUCCESS
	 */
	public String getREST_RESPONSE_SUCCESS() {
		return REST_RESPONSE_SUCCESS;
	}

	/**
	 * @param rEST_RESPONSE_SUCCESS the rEST_RESPONSE_SUCCESS to set
	 */
	@Value("${REST_RESPONSE_SUCCESS}")
	public void setREST_RESPONSE_SUCCESS(String rEST_RESPONSE_SUCCESS) {
		REST_RESPONSE_SUCCESS = rEST_RESPONSE_SUCCESS;
	}

	/**
	 * @return the rEST_RESPONSE_ERROR
	 */
	public String getREST_RESPONSE_ERROR() {
		return REST_RESPONSE_ERROR;
	}

	/**
	 * @param rEST_RESPONSE_ERROR the rEST_RESPONSE_ERROR to set
	 */
	@Value("${REST_RESPONSE_ERROR}")
	public void setREST_RESPONSE_ERROR(String rEST_RESPONSE_ERROR) {
		REST_RESPONSE_ERROR = rEST_RESPONSE_ERROR;
	}

	/**
	 * @return the cONTEXT_PATH_DEV
	 */
	public String getCONTEXT_PATH_DEV() {
		return CONTEXT_PATH_DEV;
	}

	/**
	 * @param cONTEXT_PATH_DEV the cONTEXT_PATH_DEV to set
	 */
	@Value("${CONTEXT_PATH_DEV}")
	public void setCONTEXT_PATH_DEV(String cONTEXT_PATH_DEV) {
		CONTEXT_PATH_DEV = cONTEXT_PATH_DEV;
	}

	/**
	 * @return the aPI_USER
	 */
	public String getAPI_USER() {
		return API_USER;
	}

	/**
	 * @param aPI_USER the aPI_USER to set
	 */
	@Value("${API_USER}")
	public void setAPI_USER(String aPI_USER) {
		API_USER = aPI_USER;
	}

	/**
	 * @return the pAYPAL_KEY
	 */
	public String getPAYPAL_KEY() {
		return PAYPAL_KEY;
	}

	/**
	 * @param pAYPAL_KEY the pAYPAL_KEY to set
	 */
	@Value("${PAYPAL_KEY}")
	public void setPAYPAL_KEY(String pAYPAL_KEY) {
		PAYPAL_KEY = pAYPAL_KEY;
	}

	/**
	 * @return the pAYPAL_SECRET
	 */
	public String getPAYPAL_SECRET() {
		return PAYPAL_SECRET;
	}

	/**
	 * @param pAYPAL_SECRET the pAYPAL_SECRET to set
	 */
	@Value("${PAYPAL_SECRET}")
	public void setPAYPAL_SECRET(String pAYPAL_SECRET) {
		PAYPAL_SECRET = pAYPAL_SECRET;
	}

	/**
	 * @return the pAYPAL_ENV
	 */
	public String getPAYPAL_ENV() {
		return PAYPAL_ENV;
	}

	/**
	 * @param pAYPAL_ENV the pAYPAL_ENV to set
	 */
	@Value("${PAYPAL_ENV}")
	public void setPAYPAL_ENV(String pAYPAL_ENV) {
		PAYPAL_ENV = pAYPAL_ENV;
	}

	/**
	 * @return the zIP_CODE_VALIDATION_SERVICE
	 */
	public String getZIP_CODE_VALIDATION_SERVICE() {
		return ZIP_CODE_VALIDATION_SERVICE;
	}

	/**
	 * @param zIP_CODE_VALIDATION_SERVICE the zIP_CODE_VALIDATION_SERVICE to set
	 */
	@Value("${ZIP_CODE_VALIDATION_SERVICE}")
	public void setZIP_CODE_VALIDATION_SERVICE(String zIP_CODE_VALIDATION_SERVICE) {
		ZIP_CODE_VALIDATION_SERVICE = zIP_CODE_VALIDATION_SERVICE;
	}

	/**
	 * @return the uSER_ORDERS_FOR_LAST_SEVEN_DAYS
	 */
	public int getUSER_ORDERS_FOR_LAST_SEVEN_DAYS() {
		return USER_ORDERS_FOR_LAST_SEVEN_DAYS;
	}

	/**
	 * @param uSER_ORDERS_FOR_LAST_SEVEN_DAYS the uSER_ORDERS_FOR_LAST_SEVEN_DAYS to set
	 */
	@Value("${USER_ORDERS_FOR_LAST_SEVEN_DAYS}")
	public void setUSER_ORDERS_FOR_LAST_SEVEN_DAYS(int uSER_ORDERS_FOR_LAST_SEVEN_DAYS) {
		USER_ORDERS_FOR_LAST_SEVEN_DAYS = uSER_ORDERS_FOR_LAST_SEVEN_DAYS;
	}

	/**
	 * @return the dURATION_OF_ONE_DAY
	 */
	public static int getDURATION_OF_ONE_DAY() {
		return DURATION_OF_ONE_DAY;
	}

	/**
	 * @param dURATION_OF_ONE_DAY the dURATION_OF_ONE_DAY to set
	 */
	@Value("${DURATION_OF_ONE_DAY}")
	public void setDURATION_OF_ONE_DAY(int dURATION_OF_ONE_DAY) {
		DURATION_OF_ONE_DAY = dURATION_OF_ONE_DAY;
	}

	/**
	 * @return the tHREADS_COUNT
	 */
	public int getTHREADS_COUNT() {
		return THREADS_COUNT;
	}

	/**
	 * @param tHREADS_COUNT the tHREADS_COUNT to set
	 */
	@Value("${THREADS_COUNT}")
	public void setTHREADS_COUNT(int tHREADS_COUNT) {
		THREADS_COUNT = tHREADS_COUNT;
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
	@Value("${PRODUCT_ID_TOKINIZER}")
	public void setPRODUCT_ID_TOKINIZER(String pRODUCT_ID_TOKINIZER) {
		PRODUCT_ID_TOKINIZER = pRODUCT_ID_TOKINIZER;
	}
	
	/**
	 * @return the FRONTEND_FORGET_PASSWORD
	 */
	public String getFRONTEND_FORGET_PASSWORD() {
		return FRONTEND_FORGET_PASSWORD;
	}
	
	/**
	 * @param FRONTEND_FORGET_PASSWORD the fRONTEND_FORGET_PASSWORD to set
	 */
	@Value("${FRONTEND_FORGET_PASSWORD}")
	public void setFRONTEND_FORGET_PASSWORD(String fRONTEND_FORGET_PASSWORD) {
		FRONTEND_FORGET_PASSWORD = fRONTEND_FORGET_PASSWORD;
	}
	

}
