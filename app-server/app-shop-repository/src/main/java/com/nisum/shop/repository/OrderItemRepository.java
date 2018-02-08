package com.nisum.shop.repository;

import com.nisum.shop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The Interface OrderItemRepository.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	public List<OrderItem> findAll();

	/**
	 * Find order item by id.
	 *
	 * @param id the id
	 * @return the order item
	 */
	public OrderItem findOrderItemById(Long id);

	/**
	 * Gets the order item by order id and item id.
	 *
	 * @param orderId the order id
	 * @param itemUUId the item id
	 * @return the order item by order id and item id
	 */
//	@Query("SELECT distinct oi FROM OrderItem oi JOIN oi.order o JOIN oi.item i WHERE o.id = ?1 and i.id = ?2 and oi.isDeleted = 0")
//	public OrderItem getOrderItemByOrderIdAndItemId(Long orderId, Long itemId);

	@Query("SELECT distinct oi FROM OrderItem oi JOIN oi.order o WHERE o.id = ?1 and oi.itemUUID = ?2 and oi.isDeleted = 0")
	public OrderItem getOrderItemByOrderIdAndItemUUId(Long orderId, String itemUUId);

	/**
	 * Gets the order items by order id.
	 *
	 * @param orderId the order id
	 * @return the order items by order id
	 */
	@Query("SELECT distinct oi FROM OrderItem oi JOIN oi.order o WHERE o.id = ?1 and oi.isDeleted = 0")
	public List<OrderItem> getOrderItemsByOrderId(Long orderId);

	/**
	 * Gets the order items count.
	 *
	 * @param orderId the order id
	 * @return the order items count
	 */
	@Query("SELECT COUNT(oi.id) FROM OrderItem oi JOIN oi.order o WHERE o.id = ?1 and oi.isDeleted = 0")
	public Integer getOrderItemsCount(Long orderId);

	/**
	 * Delete all order items.
	 *
	 * @param orderId the order id
	 */
	@Modifying
	@Query("UPDATE OrderItem o SET o.isDeleted = 1 WHERE o.order.id = ?1")
	public void deleteAllOrderItems(Long orderId);
}
