package com.nisum.shop.repository;

import com.nisum.shop.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The Interface OrderRepository.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {// , Specification<Order> {

	/* (non-Javadoc)
 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
 */
public List<Order> findAll();

	/**
	 * Find order by id.
	 *
	 * @param id
	 *            the id
	 * @return the order
	 */
	@Query("SELECT distinct oo FROM Order oo WHERE oo.orderStatus = 0 AND oo.id = ?1")
	public Order findOrderById(Long id);

	/**
	 * Gets the active order by user id.
	 *
	 * @param userId
	 *            the user id
	 * @return the active order by user id
	 */
//	@Query("SELECT distinct oo FROM Order oo JOIN oo.user u WHERE u.id = ?1 AND u.isDeleted = 0 AND oo.orderStatus = 0")
//	public Order getActiveOrderByUserId(Long userId);

	@Query("SELECT distinct oo FROM Order oo WHERE oo.userId = ?1 AND oo.orderStatus = 0")
	public Order getActiveOrderByUserId(Long userId);

	/**
	 * Gets the all active order.
	 *
	 * @return the all active order
	 */
	@Query("SELECT distinct oo FROM Order oo WHERE oo.orderStatus = 0")
	public List<Order> getAllActiveOrder();

	/**
	 * Gets the order history by user id.
	 *
	 * @param userId the user id
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the order history by user id
	 */
//	@Query("SELECT distinct oo FROM Order oo JOIN oo.user u WHERE u.id = ?1 AND u.isDeleted = 0 AND oo.orderStatus NOT IN (0 , 12) AND oo.updatedAt BETWEEN ?2 AND ?3 ORDER BY oo.updatedAt DESC ")
	@Query("SELECT distinct oo FROM Order oo WHERE oo.userId = ?1 AND oo.orderStatus NOT IN (0 , 12) AND oo.updatedAt BETWEEN ?2 AND ?3 ORDER BY oo.updatedAt DESC ")
	public Page<Order> getOrderHistoryByUserId(Long userId, Long startDate, Long endDate, Pageable page);

	/**
	 * Gets the order by user id and order id.
	 *
	 * @param userId the user id
	 * @param orderId the order id
	 * @return the order by user id and order id
	 */
	@Query("SELECT distinct oo FROM Order oo WHERE oo.userId = ?1 AND oo.id = ?2 AND oo.orderStatus NOT IN (0 , 12) ")
	public Order getOrderByUserIdAndOrderId(Long userId, Long orderId);

}
