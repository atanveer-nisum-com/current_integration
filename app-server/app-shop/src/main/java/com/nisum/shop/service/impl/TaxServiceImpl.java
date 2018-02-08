/**
 * 
 */
package com.nisum.shop.service.impl;

import com.nisum.shop.model.Tax;
import com.nisum.shop.repository.TaxRepository;
import com.nisum.shop.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author mabdullah
 *
 */
@Service("taxService")
public class TaxServiceImpl implements TaxService {

	@Autowired
	TaxRepository taxRepository;
	
	@Override
	public Tax findOne(Long id) {
		return taxRepository.findOne(id);
	}

	@Override
	public Tax findByAbbrv(String name) {
		return taxRepository.findByAbbrv(name);
	}

	@Override
	public Tax findByAttributes(String abbr, Long id, String state) {
		if (StringUtils.hasLength(abbr)) 
			return this.findByAbbrv(abbr);
		else if (id != null) 
			return this.findOne(id);
		else if (StringUtils.hasLength(state)) 
			return this.findByState(state);
		
		return null;
	}
	
	@Override
	public Tax findByState(String state) {
		return taxRepository.findByState(state);
	}

	@Override
	public List<Tax> findAll() {
		return taxRepository.findAll();
	}

}
