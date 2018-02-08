package com.nisum.fcc.util;

import com.nisum.common.constant.AppConstant;
import com.nisum.fcc.cassandra.model.Product;
import com.nisum.fcc.dto.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Class Converter.
 */
public class Converter {
	
	
	
	/**
	 * To product dto.
	 *
	 * @param product the product
	 * @return the product DTO
	 */
	public static ProductDTO toProductDto(Product product) {
		ProductDTO productDto = new ProductDTO();
		if (product != null) {
			productDto.setId(product.getCode() +AppConstant.PRODUCT_ID_TOKINIZER +product.getName());
			productDto.setItem(toItemDto(product));
			productDto.setSku(product.getSku());
			productDto.setCategoryName(product.getCategory());
		}
		return productDto;
	}

	/**
	 * To image dto.
	 *
	 * @param product the product
	 * @return the item DTO
	 */
	public static ItemDTO toItemDto(Product product){
		ItemDTO itemDTO = new ItemDTO();
		
		itemDTO.setId(product.getId());
		itemDTO.setName(product.getName());
		itemDTO.setCode(product.getCode());
		itemDTO.setIsBuyable(product.getIsBuyable());
		itemDTO.setDefaultImage(product.getImageDefault());
		itemDTO.setQuantity(product.getQuantity());
		Set<ItemImageDTO> itemImages = new HashSet<>();
		itemImages.addAll(product.getImageList().stream().map(image -> toImageDTO(image)).collect(Collectors.toSet()));
		itemDTO.setItemImages(itemImages);
		
		itemDTO.addItemPrice(new ItemPriceDTO(product.getPrice()));
		
		return itemDTO;
	}

	/**
	 * To image DTO.
	 *
	 * @param image the image
	 * @return the item image DTO
	 */
	public static ItemImageDTO toImageDTO(String image) {
		return new ItemImageDTO(image);
	}
	
	public static ProductDTO toProdcutDtoWithCategoryName(Product product) {
		ProductDTO productDto = toProductDto(product);
		//TODO: figure out how to add Category Name in productDTO
		return productDto;
	}

	public static ShopItemDTO toShopItemDto(Product product) {
		ShopItemDTO itemDto = new ShopItemDTO();
		itemDto.setCode(product.getCode());
		itemDto.setDefaultImage(product.getImageDefault());
		itemDto.setId(product.getId());
		itemDto.setIsBuyable(product.getIsBuyable());
		itemDto.setItemPrice(new ItemPriceDTO(product.getPrice()));
		itemDto.setName(product.getName());
		itemDto.setQuantity(product.getQuantity());
		
		return itemDto;
	}
	
	public static ShopProductDTO toShopProductDto(Product product) {
		ShopProductDTO productDto = new ShopProductDTO();
		if (product != null) {
			productDto.setId(product.getCode() +AppConstant.PRODUCT_ID_TOKINIZER +product.getName());
			productDto.setItem(toShopItemDto(product));
			productDto.setSku(product.getSku());
			productDto.setCategoryName(product.getCategory());
		}
		return productDto;
	}
}