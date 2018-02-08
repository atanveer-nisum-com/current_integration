package com.nisum.user.test.controller;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nisum.user.controller.AddressBookController;
import com.nisum.user.dto.AddressDTO;
import com.nisum.user.service.AddressBookService;

@WebMvcTest(value = AddressBookController.class, secure = false)
public class AddressBookControllerTest extends BaseControllerTest {
	
	@MockBean
	private AddressBookService addressBookService;

	private final  String ADDRESS_BOOK_URL = BaseControllerTest.BASE_URL_APP_USERS + "/addresses/?userid=3";
	private final  String UPDATE_ADDRESS_BOOK_URL =BaseControllerTest.BASE_URL_APP_USERS+"/addresses/1";
	private final  String ADDRESS_BOOK_URL_DELETE = BaseControllerTest.BASE_URL_APP_USERS + "/addresses/4?userid=3";
	private final String mockAddress = "{\"addressLine1\":\"khipro\", \"addressLine2\":\"sindh\",\"city\":\"karachi\", \"phoneNumber\":\"\" }";
	
	@Test
	public void shouldReturnAddressList() throws Exception{
		Page<AddressDTO> addressesPage = this.getMockPage();
		Mockito.when(addressBookService.findAddressListByUserId(Mockito.anyLong(), Mockito.any(Pageable.class))).thenReturn(addressesPage);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ADDRESS_BOOK_URL).accept(MediaType.APPLICATION_JSON)
					.header("userid", eq(anyLong()));
		mvc.perform(requestBuilder).andExpect(status().is2xxSuccessful()).andReturn();
	}
	
	@Test
	public void shouldReturnAddressDTOListAfterCreate() throws Exception{
		Page<AddressDTO> addressesPage = this.getMockPage();
		Mockito.when(addressBookService.save(Mockito.any(AddressDTO.class),Mockito.anyLong())).thenReturn(addressesPage);
		mvc.perform(MockMvcRequestBuilders.post(ADDRESS_BOOK_URL)
				.accept(MediaType.APPLICATION_JSON).content(mockAddress)
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", 1L))
				.andExpect(status().is2xxSuccessful()).andReturn();
	}
	
	@Test
	public void shouldReturnAddressDTOListAfterDelete() throws Exception{

		Page<AddressDTO> addressesPage = this.getMockPage();
		Mockito.when(addressBookService.removeAddress(Mockito.anyLong(), Mockito.anyLong())).thenReturn(addressesPage);
		mvc.perform(MockMvcRequestBuilders.delete(ADDRESS_BOOK_URL_DELETE)
				.accept(MediaType.APPLICATION_JSON)
				.header("userId", 1L))
				.andExpect(status().is2xxSuccessful()).andReturn();
	}

	@Test
	public void shouldReturnAddressDTOListAfterUpdate() throws Exception{
		Page<AddressDTO> addressesPage = this.getMockPage();
		Mockito.when(addressBookService.update(Mockito.any(AddressDTO.class),Mockito.anyLong())).thenReturn(addressesPage);
		mvc.perform(MockMvcRequestBuilders.put(UPDATE_ADDRESS_BOOK_URL)
				.accept(MediaType.APPLICATION_JSON).content(mockAddress)
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", 1L))
		.andExpect(status().isOk());
		         
	}
	
	private Page<AddressDTO> getMockPage(){
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressLine1("test1");
		addressDTO.setAddressLine2("test2");
		addressDTO.setCity("USA");
		addressDTO.setId(1L);
		addressDTO.setCountryBean(null);
		addressDTO.setIsDefault(1);
		addressDTO.setStateBean(null);
		ArrayList<AddressDTO> AddressDTOList = new ArrayList<AddressDTO>();
		AddressDTOList.add(addressDTO);
		return new PageImpl<AddressDTO>(AddressDTOList);
	}
	
}
