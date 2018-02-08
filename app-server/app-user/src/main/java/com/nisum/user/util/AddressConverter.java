package com.nisum.user.util;

import com.nisum.common.util.Converter;
import com.nisum.user.dto.AddressDTO;
import com.nisum.user.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter implements Converter<Address,AddressDTO>{

	@Override
	public AddressDTO convertToDto(Address entity) {
		return ETDUtils.toAddressDTO(entity);
	}

	@Override
	public Address convertToEntity(AddressDTO dto) {
		return ETDUtils.toAddress(dto);
	}

	@Override
	public Page<AddressDTO> convertEntitiesToDtos(Page<Address> entities) {
		return ETDUtils.convertAddressesToAddressesDTOPage(entities);
	}

}
