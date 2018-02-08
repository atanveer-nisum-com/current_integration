//package com.nisum.shop.util;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import com.nisum.shop.api.dto.ProductDTO;
//import com.nisum.shop.dto.OrderItemDTO;
//import com.nisum.shop.model.OrderItem;
//
///**
// * The Class ETDUtils.
// */
//public class ETDUtils {
//	
//	/**
//	 * Convert category to category DT os.
//	 *
//	 * @param categories the categories
//	 * @return the array list
//	 */
////	public static ArrayList<CategoryDTO> convertCategoryToCategoryDTOs(List<Category> categories) {
////		HashMap<String, CategoryDTO> categoriesMap = new HashMap<String, CategoryDTO>();
////		for (Category category : categories) {
////			
////			//TODO: remove all "" from this code once cassendra is integrated for Category Listings
////			
////			String id = "" +category.getId();
////			String name = category.getName();
////			
////			if (category.getParent() == null) {
////				categoriesMap.put(id, new CategoryDTO(id, name));
////			} else {
////				CategoryDTO categoryDTO = categoriesMap.get("" +category.getParent().getId());
////				categoryDTO.addSubCategory(id, name);
////			}
////		}
////		
////		return new ArrayList<CategoryDTO>(categoriesMap.values());
////	}
//	
//	/**
//	 * Convert products to product DTOs.
//	 *
//	 * @param products the products
//	 * @return the list
//	 */
///*	public static List<ProductDTO> convertProductsToProductDTOs(Page<Product> products){
//		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
//		
//		for (Product product : products) {
//			ProductDTO singleProduct = new ProductDTO();
//			ItemDTO item = new ItemDTO();
//			singleProduct.setSku(product.getSku());
//			
//			item.setId(product.getItem().getId());
//			item.setName(product.getItem().getName());
//			item.setIsBuyable(product.getItem().getIsBuyable());
//			
//			Set<ItemPriceDTO> itemPrice = new HashSet<ItemPriceDTO>();
//			
//			for (Price price : product.getItem().getItemPrice()) {
//				ItemPriceDTO itemPriceDTO = new ItemPriceDTO();
//				itemPriceDTO.setPrice(price.getPrice());
//				
//				itemPrice.add(itemPriceDTO);
//			}
//			item.setItemPrice(itemPrice);
//			
//			Set<ItemImageDTO> itemImages = new HashSet<ItemImageDTO>();
//			
//			for (ItemImage image : product.getItem().getItemImages()) {
//				ItemImageDTO itemImageDTO = new ItemImageDTO();
//				itemImageDTO.setImagePath(image.getImagePath());
//				
//				itemImages.add(itemImageDTO);
//			}
//			item.setItemImages(itemImages);
//			
//			singleProduct.setItem(item);
//			
//			productDTOs.add(singleProduct);
//		}
//
//		return productDTOs;
//	}*/
//	
//	/*public static Page<AddressDTO> convertAddressesToAddressesDTOPage(Page<Address> addressEntity) {
//		List<AddressDTO> addressesList = new ArrayList<>();
//		
//		for(Address address: addressEntity) {
//			
//			Long id = address.getId();
//			String addressLine1 = address.getAddressLine1();
//			String addressLine2 = address.getAddressLine2();
//			Byte addressType = address.getAddressType();
//			String city = address.getCity();
//			State state = address.getStateBean();
//			Country country = address.getCountryBean();
//			String phoneNumber = address.getPhoneNumber();
//			String zipCode = address.getZipcode();
//			Byte isDefault = address.getIsDefault();
//			
//			CountryBeanDTO countryDTO = new CountryBeanDTO(country.getId().intValue(), country.getName());
//			StateBeanDTO stateDTO = null;
//			if(state != null) {
//				stateDTO = new StateBeanDTO(state.getId().intValue(), state.getName());
//			}
//			
//			
//			addressesList.add(new AddressDTO(id, addressLine1, addressLine2, addressType, city, phoneNumber, zipCode, stateDTO, countryDTO, isDefault));
//		}
//		
//		Page<AddressDTO> addressesPage = new PageImpl<AddressDTO>(addressesList);
//		return addressesPage;
//	}*/
//	
//	public static Set<OrderItemDTO> toOrderItemDTO(Set<OrderItem> orderItemSet,List<ProductDTO> productDTOs){
//		int i=0;
//		Set<OrderItemDTO> orderDTOSet= new HashSet<OrderItemDTO>();
//		for(OrderItem order: orderItemSet){
//			OrderItemDTO orderItemDto = new OrderItemDTO(order.getId(), order.getInventoryStatus(), order.getPrice(), 
//					order.getSubTotalPrice(), order.getQuantity(), order.getTaxation(), order.getTotalPrice(),productDTOs.get(i++));
//			orderDTOSet.add(orderItemDto);
//		}
//		return orderDTOSet;
//		
//	}
//
//
//}
