package com.nisum.common.util;

import org.springframework.data.domain.Page;

public interface Converter<E extends BaseEntity,D extends BaseDTO> {

	public D convertToDto(E entity);
	
	public E convertToEntity(D dto);
	
	public Page<D> convertEntitiesToDtos(Page<E> entities);
	
}
