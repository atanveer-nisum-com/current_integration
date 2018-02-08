package com.nisum.user.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.user.api.dto.ProductDTO;
import com.nisum.user.dto.WishlistDTO;
import com.nisum.user.dto.WishlistDefaultNameDTO;
import com.nisum.user.dto.WishlistResponseDTO;
import com.nisum.user.service.WishlistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/wishlists")
@EnableCircuitBreaker
public class WishlistController extends BaseController {
	
	@Autowired
	private WishlistService wishlistService;
	
	@Autowired
	private ModelMapper mapper;
	
	/**
	 * @return the wishlistService
	 */
	public WishlistService getWishlistService() {
		return wishlistService;
	}

	/**
	 * @param wishlistService the wishlistService to set
	 */
	public void setWishlistService(WishlistService wishlistService) {
		this.wishlistService = wishlistService;
	}

	
	@GetMapping
	public Page<WishlistResponseDTO> getUsersWishlists(@RequestHeader("userId") String userId,
											@PageableDefault(size = PAGE_SIZE_12) Pageable pageable) {
		try {
			return new PageImpl<>(wishlistService.getWishlistByUserIdWithResponseDTO(Long.valueOf(userId), pageable));
		} catch (EmptyResultDataAccessException e) {
			throw new RestException(HttpStatus.NOT_FOUND, "No wishlist found", ErrorLevel.ERROR);
		}
	}
	
	
	@PostMapping
	public WishlistResponseDTO createWishlist(@RequestBody WishlistDefaultNameDTO wishlistDefaultNameDTO,
			@RequestHeader(value = "userId", required = true) Long userid) {
		WishlistDTO wishlistDTO = mapper.map(wishlistDefaultNameDTO, WishlistDTO.class);
		wishlistDTO.setUserId(userid);
		try {
			return mapper.map(wishlistService.saveWishlist(wishlistDTO), WishlistResponseDTO.class);
		} catch (DataIntegrityViolationException e) {
			throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Wishlist not saved", ErrorLevel.ERROR);
		}
	}
	
	@PutMapping(value = "/{wishlistid}")
	public WishlistDefaultNameDTO updateWishlist(@RequestBody WishlistDefaultNameDTO wishlistDefaultNameDTO,
			@RequestHeader(value = "userId", required = true) Long userid,
												@PathVariable("wishlistid") Long wishlistid) {
		WishlistDTO wishlistDTO = mapper.map(wishlistDefaultNameDTO, WishlistDTO.class);
		wishlistDTO.setUserId(userid);
		wishlistDTO.setWishlistId(wishlistid);
		try {
			return mapper.map(wishlistService.saveWishlist(wishlistDTO), WishlistDefaultNameDTO.class);
		} catch (DataIntegrityViolationException e) {
			throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Wishlist not updated", ErrorLevel.ERROR);
		}
	}
	
	@DeleteMapping(value = "/{wishlistid}")
	public WishlistResponseDTO deleteWishlist(@RequestHeader(value = "userId", required = true) Long userid,
											@PathVariable("wishlistid") Long wishlistid) {
		WishlistResponseDTO response = mapper.map(wishlistService.getWishlistById(wishlistid),
													WishlistResponseDTO.class);
		try {
			wishlistService.deleteWishlistById(wishlistid);
		} catch (DataIntegrityViolationException e) {
			throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Wishlist not deleted", ErrorLevel.ERROR);
		}
		return response;
	}
	
	@PostMapping(value = "/{wishlistid}/items/{itemid}")
	public ProductDTO addToWishlist(@RequestHeader(value = "userId", required = true) Long userId,
								@PathVariable("wishlistid") Long wishlistid, @PathVariable("itemid") String itemid) {
		try {
			return mapper.map(wishlistService.addToWishlist(userId, wishlistid, itemid), ProductDTO.class);
		} catch (DataIntegrityViolationException e) {
			throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Wishlist item not added", ErrorLevel.ERROR);
		}
	}
	
	@DeleteMapping("/{wishlistid}/items/{itemid}")
	public ProductDTO removeFromWishlist(@PathVariable("wishlistid") Long wishlistid, @PathVariable("itemid") String itemid) {
		try {
			return mapper.map(wishlistService.removeFromWishlist(wishlistid, itemid), ProductDTO.class);
		} catch (EmptyResultDataAccessException e) {
			throw new RestException(HttpStatus.NOT_FOUND, "Wishlist item not found", ErrorLevel.ERROR);
		} catch (DataIntegrityViolationException e) {
			throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Wishlist item not removed", ErrorLevel.ERROR);
		}
	}
	
	public Boolean isItemInWishlist(@RequestParam Long userid, @RequestParam Long itemid) {
		return wishlistService.isItemInWishlist(userid,itemid);
	}
}
