package com.nisum.user.util;

import com.nisum.common.util.Converter;
import com.nisum.user.dto.UserPaymentCardDTO;
import com.nisum.user.model.UserPaymentCard;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UserPaymentCardConverter implements Converter<UserPaymentCard, UserPaymentCardDTO> {

	
	@Override
	public UserPaymentCardDTO convertToDto(UserPaymentCard entity) {
		return ETDUtils.toUserPaymentCardDTO(entity);
		
	}

	@Override
	public UserPaymentCard convertToEntity(UserPaymentCardDTO dto) {
		return ETDUtils.toUserPaymentCard(dto);
	}

	@Override
	public Page<UserPaymentCardDTO> convertEntitiesToDtos(Page<UserPaymentCard> entities) {
		// TODO Auto-generated method stub
		return null;
	}


}
