package com.nisum.user.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nisum.common.constant.CommonEndPointConstant;
import com.nisum.common.util.DateUtils;
import com.nisum.user.api.dto.ProductDTO;
import com.nisum.user.dto.WishlistDTO;
import com.nisum.user.dto.WishlistResponseDTO;
import com.nisum.user.model.User;
import com.nisum.user.model.Wishlist;
import com.nisum.user.model.WishlistItem;
import com.nisum.user.repository.WishlistItemRepository;
import com.nisum.user.repository.WishlistRepository;
import com.nisum.user.service.UserService;
import com.nisum.user.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;



// TODO: Auto-generated Javadoc
/**
 * The Class WishlistServiceImpl.
 */
@Service
public class WishlistServiceImpl implements WishlistService {
	
	/** The wishlist repository. */
	@Autowired
	private WishlistRepository wishlistRepository;
	
	/** The wishlist item repository. */
	@Autowired
	private WishlistItemRepository wishlistItemRepository;
	
	/** The user service. */
	@Autowired 
	private UserService userService;
	
	/**  The client for talking between apps. */
	@Autowired
	private RestTemplate restClient;

	/* (non-Javadoc)
	 * @see com.nisum.service.WishlistService#getWishlistById(java.lang.Long)
	 */
	@Override
	public Wishlist getWishlistById(Long id) {

		return wishlistRepository.findWishlistById(id);
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.WishlistService#getWishlistByUserId(java.lang.Long)
	 */
	@Override
	public List<Wishlist> getWishlistByUserId(Long userId) {
		return wishlistRepository.findWishlistByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.WishlistService#saveWishlist(com.nisum.model.Wishlist)
	 */
	@Override
	public Wishlist saveWishlist(WishlistDTO wishlistDTO) {
		
		Wishlist wishlist = wishlistDTO.toWishlist();
		
		if(wishlist.getId()==null) { //i.e. its a new Wishlist
			wishlist.setCreatedAt(DateUtils.getCurrentTime());
					
		}
		else {
			wishlist.setUpdatedAt(DateUtils.getCurrentTime());
			
		}
		wishlist = wishlistRepository.save(wishlist);
		if (null == wishlist) throw new DataIntegrityViolationException("Wishlist not saved");
		if(wishlist.getIsDefault()) this.makeWishlistDefault(wishlist);
		return wishlist;
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.WishlistService#deleteWishlist(com.nisum.model.Wishlist)
	 */
	@Override
	public Boolean deleteWishlist(Wishlist wishlist) {
		wishlistRepository.delete(wishlist);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.WishlistService#deleteWishlistById(java.lang.Long)
	 */
	@Override
	public Boolean deleteWishlistById(Long id) {
		wishlistRepository.delete(id);
		if (!wishlistRepository.findWishlistById(id).getIsDefault())
			throw new DataIntegrityViolationException("Wishlist not deleted");
		return true;
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.WishlistService#makeWishlistDefault(com.nisum.model.Wishlist)
	 */
	@Override
	public Boolean makeWishlistDefault(Wishlist wishlist) {
		List<Wishlist> wishlists = wishlistRepository.findWishlistByUserId(wishlist.getUser().getId());
		
		for(Wishlist wl : wishlists) {
			if(wishlist.getId().equals(wl.getId()))
				wl.setIsDefault(true);
			else
				wl.setIsDefault(false);
			
		}
			
		wishlistRepository.save(wishlists);
		
		return true;
		
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.WishlistService#getWishlistItemById(java.lang.Long)
	 */
	@Override
	public WishlistItem getWishlistItemById(Long id) {
		
		return wishlistItemRepository.findWishlistItemById(id);
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.WishlistService#getWishlistItemsByWishlistId(java.lang.Long)
	 */
	@Override
	public Set<WishlistItem> getWishlistItemsByWishlistId(Long wishlistId) {
		return wishlistItemRepository.findWishlistItemByWishlistId(wishlistId);
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.WishlistService#addToWishlist(java.lang.Long, java.lang.Long)
	 */
	
	@Override
	public WishlistItem addToWishlist(Long userId, Long wishlistId, String itemId) {
		
		WishlistItem wishlistItem = new WishlistItem();
		//Item item;
		Wishlist wishlist;	
		
		//if wishlist id is 0 then get from DB or create a default
		if(wishlistId==null || wishlistId<=0) {
			
			wishlist = this.getDefaultWishlist(userId);
			if(wishlist==null) {//means if it is still null then create
				wishlist = createDefaultWishlist(userId, itemId);
			}
			else { //add items in the existing default wishlist
				
				wishlistItem.setIsDeleted(false);
				wishlistItem.setItemUUID(itemId);
				
				wishlistItem.setWishlist(wishlist);
				wishlist.addWishlistItem(wishlistItem);
									
				wishlist = wishlistRepository.save(wishlist);
			}
		} else { //i.e. wishlist id present so fetch it from DB

			wishlist = wishlistRepository.findWishlistById(wishlistId);
			if(wishlist!=null) { //No wishlist created yet

				wishlistItem.setIsDeleted(false);
				wishlistItem.setItemUUID(itemId);

				wishlistItem.setWishlist(wishlist);
				wishlist.getWishlistItems().add(wishlistItem);

				wishlist = wishlistRepository.save(wishlist);
			}
		}
		if (null == wishlist) throw new DataIntegrityViolationException("Wishlist not saved");
		return wishlistItem;
	}

	
	/* (non-Javadoc)
	 * @see com.nisum.service.WishlistService#getDefaultWishlist(java.lang.Long)
	 */
	public Wishlist getDefaultWishlist(Long userId) {
		List<Wishlist> wishlists = wishlistRepository.findDefaultWishlist(userId);
		
		if(wishlists!=null && wishlists.size()>0) {
			return wishlists.get(0);
		}
		else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.WishlistService#removeFromWishlist(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Boolean removeFromWishList(Long wishlistItemId) {
		wishlistItemRepository.delete(wishlistItemId);
		return true;
	}

	
	/**
	 * Creates the default wishlist.
	 *
	 * @param userId the user id
	 * @param itemId the item id
	 * @return the wishlist
	 */
	private Wishlist createDefaultWishlist(Long userId, String itemId) {
		Wishlist wishlist = new Wishlist();
		User user = userService.findOne(userId);
		WishlistItem wishlistItem;
		
		wishlist.setName(String.format("%s Default List", user.getFirstName()));
		wishlist.setCreatedAt(DateUtils.getCurrentTime());
		wishlist.setIsDefault(true);
		wishlist.setIsDeleted(false);
		
		wishlist.setUser(user);
		
		//Now create the WishlistItem
		wishlistItem = new WishlistItem();
		wishlistItem.setItemUUID(itemId);
		wishlistItem.setIsDeleted(false);
		wishlistItem.setWishlist(wishlist);
		wishlist.setWishlistItems(new HashSet<WishlistItem>());
		wishlist.addWishlistItem(wishlistItem);
		wishlistRepository.save(wishlist);
		return wishlist;
		
	}

	/* (non-Javadoc)
	 * @see com.nisum.user.service.WishlistService#getWishlistByUserId(java.lang.Long, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Wishlist> getWishlistByUserId(Long userId, Pageable pageable) {
		return wishlistRepository.findWishlistByUserId(userId,pageable);
	}

	/* (non-Javadoc)
	 * @see com.nisum.user.service.WishlistService#getWishlistByUserIdWithResponseDTO(java.lang.Long, org.springframework.data.domain.Pageable)
	 */
	@Override
	@HystrixCommand(fallbackMethod = "returnFailSafeWishList")
	public List<WishlistResponseDTO> getWishlistByUserIdWithResponseDTO(Long userid, Pageable pageable) {
		Page<Wishlist> wishlist = wishlistRepository.findWishlistByUserId(userid,pageable);
		if (null != wishlist.getContent() && wishlist.getContent().size() > 0)
			return wishlist.getContent().parallelStream().map(w ->
				new WishlistResponseDTO(w.getId(), w.getName(), w.getIsDefault(), w.getWishlistItems().parallelStream().map( wi ->
					restClient.getForObject(CommonEndPointConstant.PRODUCT_FIND_ONE, ProductDTO.class, wi.getItemUUID()))
						.collect(Collectors.toList())))
					.collect(Collectors.toList());
		throw new EmptyResultDataAccessException(1);
	}
	

	/**
	 * Return fail safe wish list.
	 *
	 * @param userid the userid
	 * @param pageable the pageable
	 * @return the list
	 */
	public List<WishlistResponseDTO> returnFailSafeWishList(Long userid,
			Pageable pageable) {
		
		List<WishlistResponseDTO> lstWishlist = new ArrayList<>();
		WishlistResponseDTO wishlist = new WishlistResponseDTO();
		
		wishlist.setName("An error has occurred!");
		
		
		
		lstWishlist.add(wishlist);
		
		return lstWishlist;
		
	}

	/* (non-Javadoc)
	 * @see com.nisum.user.service.WishlistService#removeFromWishlist(java.lang.Long, java.lang.String)
	 */
	@Override
	public WishlistItem removeFromWishlist(Long wishlistid, String itemid) {
		
		WishlistItem findWishlistItemByUUID = wishlistItemRepository.findWishlistItemByUUID(itemid);
		if (findWishlistItemByUUID != null && findWishlistItemByUUID.getId() != null) {
			wishlistItemRepository.delete(findWishlistItemByUUID.getId());
			if (!Objects.isNull(wishlistItemRepository.findWishlistItemByUUID(itemid)))
				throw new DataIntegrityViolationException("Wishlist item not removed");
		} else throw new EmptyResultDataAccessException(1);
		return findWishlistItemByUUID;
	}

	/* (non-Javadoc)
	 * @see com.nisum.user.service.WishlistService#isItemInWishlist(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Boolean isItemInWishlist(Long userId, Long itemId) {
		// TODO Auto-generated method stub
		return false;
	}

}
