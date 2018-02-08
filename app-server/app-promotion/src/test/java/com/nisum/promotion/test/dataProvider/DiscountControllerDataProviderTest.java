package com.nisum.promotion.test.dataProvider;

/**
 * @author Nisum Pakistan
 * 
 * DiscountControllerTestDataProvider
 *
 */
public class DiscountControllerDataProviderTest extends BaseDataProvider {

	private static String promotion_URL=getBase_URL() + "/discount/products/";

	private static String[] ProductIDs=new String[] {"d797f620-c552-11e7-9614-35f7986cc263"};
	
	private static String jsonPathAttribute="$[0].productId";
	
	private static String jsonPathVerifyValue="d797f620-c552-11e7-9614-35f7986cc263";
	
	private static Double orderDiscount=20d;
	
	private static String order_discount_url = getBase_URL() + "/discount/order/3";
	
	private static String categories_product_promotion_URL=getBase_URL() + "/discount/products/categories/";

	
	
	public static String getOrder_discount_url() {
		return order_discount_url;
	}

	public static void setOrder_discount_url(String order_discount_url) {
		DiscountControllerDataProviderTest.order_discount_url = order_discount_url;
	}
	
	public static Double getOrderDisount() {
		return orderDiscount;
	}

	public static void setOrderDisount(Double orderDisount) {
		DiscountControllerDataProviderTest.orderDiscount = orderDisount;
	}

	public static String getJsonPathAttribute() {
		return jsonPathAttribute;
	}

	public static void setJsonPathAttribute(String jsonPathAttribute) {
		DiscountControllerDataProviderTest.jsonPathAttribute = jsonPathAttribute;
	}

	public static String getJsonPathVerifyValue() {
		return jsonPathVerifyValue;
	}

	public static void setJsonPathVerifyValue(String jsonPathVerifyValue) {
		DiscountControllerDataProviderTest.jsonPathVerifyValue = jsonPathVerifyValue;
	}

	public static String[] getProductIDs() {
		return ProductIDs;
	}

	public static void setProductIDs(String[] productIDs) {
		ProductIDs = productIDs;
	}

	public static String getPromotion_URL() {
		return promotion_URL;
	}

	public static void setPromotion_URl(String promotion_URL) {
		DiscountControllerDataProviderTest.promotion_URL = promotion_URL;
	}

	public static String getCategories_product_promotion_URL() {
		return categories_product_promotion_URL;
	}

	public static void setCategories_product_promotion_URL(String categories_product_promotion_URL) {
		DiscountControllerDataProviderTest.categories_product_promotion_URL = categories_product_promotion_URL;
	}





}
