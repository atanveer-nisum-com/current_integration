/**
 * 
 */
package com.nisum.shop.dto;

import com.nisum.shop.constant.TransactionStatus;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.OrderTransaction;
import com.nisum.shop.model.PaymentType;
import com.nisum.shop.service.OrderService;
import com.nisum.shop.service.UserService;

import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class TransactionDTO.
 *
 * @author omkhan
 */
public class TransactionDTO implements Serializable {

 /** The transaction id. */
 private String transactionId;
 
 /** The user id. */
 private Long userId;
 
 /** The payment type id. */
 private Long paymentTypeId;
 
 /** The amount. */
 private BigDecimal amount;
 
 /** The currency code. */
 private String currencyCode;
 
 /** The transaction status. */
 private String transactionStatus;
 
 /** The comments. */
 private String comments;
 
 /** The order id. */
 private Long orderId;
   
   /** The Constant serialVersionUID. */
   /* The Constant serialVersionUID. */
    private final static long serialVersionUID = -7189812341982892172L;
 
 /** The order service. */
 @Transient
 private OrderService orderService;
 
 /** The user service. */
 @Transient
 private UserService userService;
 
 
 
 /**
  * Gets the user id.
  *
  * @return the userId
  */
 public Long getUserId() {
  return userId;
 }
 
 /**
  * Sets the user id.
  *
  * @param userId the userId to set
  */
 public void setUserId(Long userId) {
  this.userId = userId;
 }
 
 /**
  * Gets the payment type id.
  *
  * @return the paymentTypeId
  */
 public Long getPaymentTypeId() {
  return paymentTypeId;
 }
 
 /**
  * Sets the payment type id.
  *
  * @param paymentTypeId the paymentTypeId to set
  */
 public void setPaymentTypeId(Long paymentTypeId) {
  this.paymentTypeId = paymentTypeId;
 }
 
 /**
  * Gets the amount.
  *
  * @return the amount
  */
 public BigDecimal getAmount() {
  return amount;
 }
 
 /**
  * Sets the amount.
  *
  * @param amount the amount to set
  */
 public void setAmount(BigDecimal amount) {
  this.amount = amount;
 }
 
 /**
  * Gets the currency code.
  *
  * @return the currencyCode
  */
 public String getCurrencyCode() {
  return currencyCode;
 }
 
 /**
  * Sets the currency code.
  *
  * @param currencyCode the currencyCode to set
  */
 public void setCurrencyCode(String currencyCode) {
  this.currencyCode = currencyCode;
 }
 
 /**
  * Gets the transaction status.
  *
  * @return the transactionStatus
  */
 public String getTransactionStatus() {
  return transactionStatus;
 }
 
 /**
  * Sets the transaction status.
  *
  * @param transactionStatus the transactionStatus to set
  */
 public void setTransactionStatus(String transactionStatus) {
  this.transactionStatus = transactionStatus;
 }
 
 /**
  * Gets the comments.
  *
  * @return the comments
  */
 public String getComments() {
  return comments;
 }
 
 /**
  * Sets the comments.
  *
  * @param comments the comments to set
  */
 public void setComments(String comments) {
  this.comments = comments;
 }
 
 
 /**
  * Gets the transaction id.
  *
  * @return the transactionId
  */
 public String getTransactionId() {
  return transactionId;
 }
 
 /**
  * Sets the transaction id.
  *
  * @param transactionId the transactionId to set
  */
 public void setTransactionId(String transactionId) {
  this.transactionId = transactionId;
 }
 
 
 
 
 /**
  * Gets the order id.
  *
  * @return the orderId
  */
 public Long getOrderId() {
  return orderId;
 }
 
 /**
  * Sets the order id.
  *
  * @param orderId the orderId to set
  */
 public void setOrderId(Long orderId) {
  this.orderId = orderId;
 }
 
 
 
 
 /**
  * Sets the order service.
  *
  * @param orderService the orderService to set
  */
 public void setOrderService(OrderService orderService) {
  this.orderService = orderService;
 }
 
 
 
 /**
  * Gets the serialversionuid.
  *
  * @return the serialversionuid
  */
 public static Long getSerialversionuid() {
  return serialVersionUID;
 }
 
 /**
  * Sets the user service.
  *
  * @param userService the userService to set
  */
 public void setUserService(UserService userService) {
  this.userService = userService;
 }
 
 /**
  * To order transaction.
  *
  * @return the order transaction
  */
 public OrderTransaction toOrderTransaction() {
  
  OrderTransaction orderTransaction = new OrderTransaction();
  Set<Order> orderSet = new HashSet<Order>();
  
  //Get Order against user id :
  Order order = orderService.getActiveOrderByUserId(this.userId);
  
  orderSet.add(order);
  this.orderId=order.getId();
  orderTransaction.setUser(userService.findOne(this.userId));
  
  orderTransaction.setOrders(orderSet);
  orderTransaction.setComments(this.comments);
  orderTransaction.setCurrency(this.currencyCode);
  orderTransaction.setCurrencyCode(this.currencyCode);
  orderTransaction.setPayment(this.amount);
  orderTransaction.setTransactionId(this.transactionId);
  
  
  //TODO change this
  PaymentType paymentType = new PaymentType();
  paymentType.setId(this.paymentTypeId);
  
  orderTransaction.setPaymentType(paymentType);
  orderTransaction.setTransactionStatus(TransactionStatus.SUCCESS.getValue());
  
  return orderTransaction;
  
 }
 
 
 
 
}