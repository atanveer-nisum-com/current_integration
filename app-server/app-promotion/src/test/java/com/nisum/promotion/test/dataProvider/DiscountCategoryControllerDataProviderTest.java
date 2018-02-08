package com.nisum.promotion.test.dataProvider;

/**
 * @author Nisum Pakistan
 * 
 * DiscountControllerTestDataProvider
 *
 */
public class DiscountCategoryControllerDataProviderTest extends BaseDataProvider {

	private static String promotion_URL = getBase_URL() + "/discount/categories/";

	private static String[] categoriesName =new String[] {"women-activewear"};
	
	private static String jsonPathAttribute="$[0].categoryName";
	
	private static String jsonPathVerifyValue="women-activewear";

	public static String getPromotion_URL() {
		return promotion_URL;
	}

	public static void setPromotion_URL(String promotion_URL) {
		DiscountCategoryControllerDataProviderTest.promotion_URL = promotion_URL;
	}

	public static String[] getCategoriesName() {
		return categoriesName;
	}

	public static void setCategoriesName(String[] categoriesName) {
		DiscountCategoryControllerDataProviderTest.categoriesName = categoriesName;
	}

	public static String getJsonPathAttribute() {
		return jsonPathAttribute;
	}

	public static void setJsonPathAttribute(String jsonPathAttribute) {
		DiscountCategoryControllerDataProviderTest.jsonPathAttribute = jsonPathAttribute;
	}

	public static String getJsonPathVerifyValue() {
		return jsonPathVerifyValue;
	}

	public static void setJsonPathVerifyValue(String jsonPathVerifyValue) {
		DiscountCategoryControllerDataProviderTest.jsonPathVerifyValue = jsonPathVerifyValue;
	}
	
}
