package com.nisum.user.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;

public class StoreDTO extends BaseDTO implements Serializable {
	
	private static final long serialVersionUID = -7429970585396428938L;
	private String name;
	private Long zipcode;
	private String address;
	private Long id;
	public StoreDTO() {
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getZipcode() {
		return zipcode;
	}

	public void setZipcode(Long zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public StoreDTO(Long id,String name,Long zipcode,String address) {
		
		this.id = id;
		this.name = name;
		this.zipcode = zipcode;
		this.address = address;
		
	}
	
	

}
