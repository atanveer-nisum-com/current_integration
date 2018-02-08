package com.nisum.user.util;

import com.nisum.common.util.Converter;
import com.nisum.user.dto.SignUpFormDTO;
import com.nisum.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User,SignUpFormDTO>{

	@Override
	public SignUpFormDTO convertToDto(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User convertToEntity(SignUpFormDTO dto) {
		return ETDUtils.toUser(dto);
	}

	@Override
	public Page<SignUpFormDTO> convertEntitiesToDtos(Page<User> entities) {
		// TODO Auto-generated method stub
		return null;
	}

}
