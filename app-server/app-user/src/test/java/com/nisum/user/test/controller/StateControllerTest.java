package com.nisum.user.test.controller;

import com.nisum.user.controller.StateController;
import com.nisum.user.model.State;
import com.nisum.user.service.StateService;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = StateController.class, secure = false)
public class StateControllerTest extends BaseControllerTest {
	private final String STATE_URL_GET = "/states/230";
	private final String STATE_URL_VALIDATE = "/states/validateZip/92018/SanDiego/3/200";
	
	@MockBean
	private StateService stateService;
	
	@Test
	public void shoudlReturnStateList() throws Exception {
		State calfornia = new State();
		calfornia.setAbbreviation("CA");
		calfornia.setName("Calafornia");
		calfornia.setId(5l);
		ArrayList<State> stateList = new ArrayList<State>();
		stateList.add(calfornia);
		String expected = "[{id:5,name:\"Calafornia\", abbreviation: \"CA\"}]";
		Mockito.when(stateService.getStateByCountryIdOrName(Mockito.anyLong(),Mockito.anyLong(), Mockito.anyString())).thenReturn(stateList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(STATE_URL_GET).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);	
	}
	
	@Test
	public void ShouldReturnString() throws Exception{
		String mokeRespone = "{error:true}";
		Mockito.when(stateService.validateZipCode(Mockito.anyString(), Mockito.anyString(),Mockito.anyString(), Mockito.anyString())).thenReturn(mokeRespone);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(STATE_URL_VALIDATE).accept(org.springframework.http.MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		assertEquals(mokeRespone,result.getResponse().getContentAsString());
	}
	

}
