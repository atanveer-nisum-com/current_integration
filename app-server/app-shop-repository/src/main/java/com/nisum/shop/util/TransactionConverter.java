package com.nisum.shop.util;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.nisum.common.shop.dto.TransactionDTO;
import com.nisum.common.util.Converter;
import com.nisum.shop.model.Transaction;

@Component
public class TransactionConverter implements Converter<Transaction, TransactionDTO> {


	@Override
	public TransactionDTO convertToDto(Transaction entity) {
		
		return null;
	}

	@Override
	public Transaction convertToEntity(TransactionDTO dto) {
		
//		TODO: return ETDUtils.toOrderTransaction(dto);
		return null;
	}

	@Override
	public Page<TransactionDTO> convertEntitiesToDtos(Page<Transaction> entities) {

		return null;
	}

}
