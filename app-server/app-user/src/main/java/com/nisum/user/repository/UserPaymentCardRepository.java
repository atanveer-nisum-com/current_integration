package com.nisum.user.repository;

import com.nisum.user.model.UserPaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPaymentCardRepository extends JpaRepository<UserPaymentCard,Long>{
	
	
	@Query("SELECT c from UserPaymentCard c WHERE c.user.id = ?1 and c.isDefault = 1")
	public UserPaymentCard findDefaultCardByUserId(Long userId);
	
	@Query("SELECT c from UserPaymentCard c WHERE c.cardNumber = ?1")
	public UserPaymentCard findCardByNumber(Long cardNumber);
	
	@Query("SELECT c from UserPaymentCard c WHERE c.user.id = ?1")
	public List<UserPaymentCard> findAllCardByUserId(Long userId);
	
	@Modifying
	@Query("Update UserPaymentCard i SET i.isDeleted = 1 where i.id=?1")
	public void delete(Long cardId);
	
	
}
