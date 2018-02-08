package com.nisum.user.test.controller;

import com.nisum.user.controller.ProfileController;
import com.nisum.user.dto.ProfileDTO;
import com.nisum.user.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProfileController.class, secure = false)
public class ProfileControllerTest extends BaseControllerTest{
	
	@MockBean
	protected UserService userService;
	
	private final String PROFILE_URL = BaseControllerTest.BASE_URL_APP_USERS + "/profile?userid=3";
	
	@Test
	public void shoudlReturnProfileDTO() throws Exception{
	ProfileDTO profileDTO = new ProfileDTO();
	profileDTO.setEmailAddress("abcd@gmail.com");
	profileDTO.setFirstName("abcd");
	profileDTO.setLastName("khatri");
	Mockito.when(userService.getProfile(Mockito.anyLong())).thenReturn(profileDTO);
	RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PROFILE_URL).accept(MediaType.APPLICATION_JSON)
			.header("userid", "3");
	mvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("firstName").value("abcd")).andReturn();
	}
	
	@Test
	public void shouldReturnProfileDTOAfterUpdate() throws Exception{
	
		ProfileDTO profileDTO = new ProfileDTO();
		profileDTO.setEmailAddress("abcd@gmail.com");
		profileDTO.setFirstName("abcd");
		profileDTO.setLastName("tulsi");
		 String mokeProfile = "{\r\n" + 
				   "\"id\": 3,\r\n" + 
				   "\"firstName\": \"abcd\",\r\n" + 
				   "\"lastName\": \"tulsi\",\r\n" + 
				   "\"addresses\": [\r\n" + 
				   "  {\r\n" + 
				   "\"id\": 2,\r\n" + 
				   "\"addressLine1\": \"khipro\",\r\n" + 
				   "\"addressLine2\": \"sanghar\",\r\n" + 
				   "\"addressType\": 1,\r\n" + 
				   "\"city\": \"karachi\",\r\n" + 
				   "\"phoneNumber\": \"03322222222\",\r\n" + 
				   "\"stateBean\": {\r\n" + 
				   "\"id\": 13,\r\n" + 
				   "\"name\": \"Iowa\",\r\n" + 
				   "\"abbreviation\": \"IA\"\r\n" + 
				   "},\r\n" + 
				   "\"zipcode\": \"92108\",\r\n" + 
				   "\"countryBean\": {\r\n" + 
				   "\"id\": 230,\r\n" + 
				   "\"name\": \"United States\",\r\n" + 
				   "\"abbreviation\": \"US\"\r\n" + 
				   "},\r\n" + 
				   "\"isDefault\": 0,\r\n" + 
				   "\"createdAt\": null,\r\n" + 
				   "\"updatedAt\": null,\r\n" + 
				   "\"isDeleted\": 0\r\n" + 
				   "}\r\n" + 
				   "],\r\n" + 
				   "\"emailAddress\": \"tdas@nisum.com\",\r\n" + 
				   "\"currentPassword\": null,\r\n" + 
				   "\"newPassword\": null,\r\n" + 
				   "\"confirmPassword\": null\r\n" + 
				   "}";
 
		Mockito.when(userService.updateProfile(Mockito.anyObject(),Mockito.anyLong())).thenReturn(profileDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(PROFILE_URL).accept(MediaType.APPLICATION_JSON).content(mokeProfile).contentType(MediaType.APPLICATION_JSON)
					.header("userid", eq(anyLong()));
		mvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("firstName").value("abcd")).andReturn();
		
	}
	
	@Test
	public
	void serviceShouldNotBeNull(){
		assertNotNull(userService);
	}
	
	
}
