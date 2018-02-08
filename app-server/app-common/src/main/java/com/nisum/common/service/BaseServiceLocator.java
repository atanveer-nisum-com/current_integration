package com.nisum.common.service;

import com.nisum.common.util.BaseDTO;


public interface BaseServiceLocator {
	void save(BaseDTO baseDto);
	void update(BaseDTO baseDTO);
	void delete(BaseDTO baseDTO);
}
