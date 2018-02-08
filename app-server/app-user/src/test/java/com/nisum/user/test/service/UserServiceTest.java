package com.nisum.user.test.service;

import com.nisum.common.exception.rest.RestException;
import com.nisum.common.integration.user.dto.UserDTO;
import com.nisum.common.util.AESEncryptionUtils;
import com.nisum.common.util.DateUtils;
import com.nisum.user.dto.AddressDTO;
import com.nisum.user.dto.ProfileDTO;
import com.nisum.user.model.Address;
import com.nisum.user.model.State;
import com.nisum.user.model.User;
import com.nisum.user.repository.UserRepository;
import com.nisum.user.service.EmailService;
import com.nisum.user.service.StateService;
import com.nisum.user.service.UserService;
import com.nisum.user.test.mock.models.MockModelFactory;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.*;

public class UserServiceTest extends BaseServiceTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private StateService stateService;
	
	@MockBean
	EmailService emailService;

	@Test
	public void shouldFindOne() {
		User user = MockModelFactory.getMockUser();
		
		Mockito.when(userRepository.findUserById(Matchers.anyLong())).thenReturn(user);
		
		assertEquals(user.getId(), userService.findOne(user.getId()).getId());
	}
	
	@Test
	public void shouldSaveGuest() {
		User user = MockModelFactory.getMockUser();
		
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		
		assertEquals(user.getId(), userService.saveGuest().getId());
	}
	
	@Test
	public void shouldFindAllUsers() {

		List<User> userLists = new ArrayList<User>();
		
		Mockito.when(userRepository.findAll()).thenReturn(userLists);
		
		assertEquals(userLists, userService.findAllUsers());
	}
	
	@Test
	public void shouldCreateUser() {

		User user = new User();
		
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		
		assertEquals(user.getId(), userService.createUser(user).getId());
	}
	
	@Test(expected =DuplicateKeyException.class)
	public void shouldNotCreateUserAndThrowExceptions() {

		User user = MockModelFactory.getMockUser();
		
		List<User> users = new ArrayList<>();
		
		Address address = MockModelFactory.getMockAddress();
		
		address.getCountryBean().setId(1l);
		
		address.setIsDeleted((byte)0);
		address.setIsDefault((byte)0);
		
		users.add(user);
		
		Mockito.when(userRepository.findUserByEmail(Mockito.any(String.class))).thenReturn(users);
		
		userService.createUser(user);
	}
	
	@Test
	public void shouldCreateUserForNonUSCountry() {

		User user = new User();
		
		Address address = MockModelFactory.getMockAddress();
		
		address.getCountryBean().setId(1l);
		
		address.setIsDeleted(null);
		address.setIsDefault(null);
		
		user.addAddress(address);
		
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		
		assertEquals(user.getId(), userService.createUser(user).getId());
	}
	
	@Test
	public void shouldCreateUserForCountryUS() {

		User user = new User();
		
		Address address = MockModelFactory.getMockAddress();
		
		address.setIsDeleted((byte)0);
		address.setIsDefault((byte)0);
		address.setZipcode("99501");
		address.setCity("Anchorage");
		
		user.addAddress(address);
		
		State state = MockModelFactory.getMockState();
		
		Mockito.when(stateService.findStateById(Matchers.anyLong())).thenReturn(state);
		
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		
		assertEquals(user.getId(), userService.createUser(user).getId());
	}
	
	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void shouldThrowRestExceptionForInvalidZipcode() {

		User user = new User();
		
		Address address = MockModelFactory.getMockAddress();
		
		address.setZipcode("11111");
		user.addAddress(address);
		
		
		State state = MockModelFactory.getMockState();
		
		Mockito.when(stateService.findStateById(Matchers.anyLong())).thenReturn(state);
		
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		
		userService.createUser(user);
	}
	
	@Test
	public void shouldUpdateUser() {
		
		User user = MockModelFactory.getMockUser();
		
		Mockito.when(userRepository.save(Matchers.any(User.class))).thenReturn(user);
		
		assertEquals(user.getId(), userService.update(user).getId());
	}
	
	@Test
	public void shouldFindUserByEmailAddressAndPassword() {
		
		User user = MockModelFactory.getMockUser();
		
		Mockito.when(userRepository.findUserByEmailAddressAndPassword(Matchers.anyString(),Matchers.anyString())).thenReturn(user);
		
		assertEquals(user.getId(), userService.findUserByEmailAddressAndPassword(user.getEmailAddress(),user.getPassword()).getId());
	}
	
	@Test
	public void shouldFindActiveUserByEmail() {
		
		User user = MockModelFactory.getMockUser();
		
		List<User> users = new ArrayList<>();
		
		users.add(user);
		
		Mockito.when(userRepository.findActiveUserByEmail(Matchers.anyString())).thenReturn(users);
		
		assertEquals(user.getId(), userService.findActiveUserByEmail(user.getEmailAddress()).getId());
	}
	
	@Test
	public void shouldNotFindActiveUserByEmail() {
		
		User user = MockModelFactory.getMockUser();
		
		List<User> users = new ArrayList<>();
		
		Mockito.when(userRepository.findActiveUserByEmail(Matchers.anyString())).thenReturn(users);
		
		assertEquals(null, userService.findActiveUserByEmail(user.getEmailAddress()));
	}
	
	@Test
	public void shouldFindUserByResetToken() {
		
		User user = MockModelFactory.getMockUser();
		
		List<User> users = new ArrayList<>();
		
		users.add(user);
		
		Mockito.when(userRepository.findUserByResetToken(Matchers.anyString())).thenReturn(users);
		
		assertEquals(user.getId(), userService.findUserByResetToken(user.getResetToken()).getId());
	}
	
	@Test
	public void shouldNotFindUserByResetToken() {
		
		User user = MockModelFactory.getMockUser();
		
		List<User> users = new ArrayList<>();
		
		Mockito.when(userRepository.findActiveUserByEmail(Matchers.anyString())).thenReturn(users);
		
		assertEquals(null, userService.findUserByResetToken(user.getResetToken()));
	}
	
	@Test
	public void shouldFindUserByTokenAndId() {
		
		User user = MockModelFactory.getMockUser();
		
		List<User> users = new ArrayList<>();
		
		users.add(user);
		
		Mockito.when(userRepository.findUserByTokenAndId(Matchers.anyString(),Matchers.anyLong())).thenReturn(users);
		
		assertEquals(user.getId(), userService.findUserByTokenAndId(user.getResetToken(),user.getId()).getId());
	}
	
	@Test
	public void shouldNotFindUserByTokenAndId() {
		
		User user = MockModelFactory.getMockUser();
		
		List<User> users = new ArrayList<>();
		
		Mockito.when(userRepository.findUserByTokenAndId(Matchers.anyString(),Matchers.anyLong())).thenReturn(users);
		
		assertEquals(null, userService.findUserByTokenAndId(user.getResetToken(),user.getId()));
	}
	
	@Test
	public void shouldAlive() {
		
		User user = MockModelFactory.getMockUser();
		
		Mockito.when(userRepository.findOne(Matchers.anyLong(),Matchers.anyString(),eq(DateUtils.getCurrentTime()))).thenReturn(user);
		
		assertEquals(true, userService.isAlive(user.getId().toString(),user.getResetToken()));
	}
	
	@Test
	public void shouldNotAlive() {
		
		User user = MockModelFactory.getMockUser();
		Mockito.when(userRepository.findOne(anyLong(),anyString(),anyLong())).thenReturn(null);
		
		assertFalse(userService.isAlive(user.getId().toString(),user.getResetToken()));
	}
	
	@Test
	public void shouldGetProfile() {
		
		User user = MockModelFactory.getMockUser();
		
		ProfileDTO profileDTO = MockModelFactory.getMockProfileDTO();
		
		Mockito.when(userRepository.findUserById(Matchers.anyLong())).thenReturn(user);
		
		assertEquals(profileDTO.getFirstName(), userService.getProfile(user.getId()).getFirstName());
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void shouldNotGetProfile() {
		
		User user = MockModelFactory.getMockUser();
		
		Mockito.when(userRepository.findUserById(Matchers.anyLong())).thenReturn(null);
		
		userService.getProfile(user.getId());
		
	}
	
	@Test(expected = InvalidPropertyException.class)
	public void shouldGetRestExceptionForCurrentPassword() {
		
		User user = MockModelFactory.getMockUser();
		
		ProfileDTO profileDTO = MockModelFactory.getMockProfileDTO();
		
		user.setEmailAddress("test@nisum.com");
		
		user.setPassword(profileDTO.getCurrentPassword());
		
		Mockito.when(userRepository.findUserById(Matchers.anyLong())).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		
		assertEquals(profileDTO.getFirstName(), userService.updateProfile(profileDTO, user.getId()).getFirstName());
	}
	
	@Test(expected = DuplicateKeyException.class)
	public void shouldGetRestExceptionForEmailAddress() {
		
		User user = MockModelFactory.getMockUser();
		
		ProfileDTO profileDTO = MockModelFactory.getMockProfileDTO();
		
		user.setEmailAddress("test@nisum.com");
		
		List<User> users = new ArrayList<>();
		
		users.add(user);
		
		Mockito.when(userRepository.findUserById(Matchers.anyLong())).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		Mockito.when(userRepository.findUserByEmail(Mockito.anyString())).thenReturn(users);
		
		userService.updateProfile(profileDTO, user.getId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldGetRestExceptionForEmptyNewPassword() {
		
		User user = MockModelFactory.getMockUser();
		
		ProfileDTO profileDTO = MockModelFactory.getMockProfileDTO();
		
		profileDTO.setNewPassword("");
		
		user.setPassword(AESEncryptionUtils.encrypt(profileDTO.getCurrentPassword()));
		
		Mockito.when(userRepository.findUserById(Matchers.anyLong())).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		
		userService.updateProfile(profileDTO, user.getId());
	}
	
	@Test
	public void shouldUpdateProfile() {
		
		User user = MockModelFactory.getMockUser();
		
		ProfileDTO profileDTO = MockModelFactory.getMockProfileDTO();
		
		profileDTO.setAddresses(new HashSet<AddressDTO>());
		
		user.setPassword(AESEncryptionUtils.encrypt(profileDTO.getCurrentPassword()));
		
		Mockito.when(userRepository.findUserById(Matchers.anyLong())).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		Mockito.doNothing().when(emailService).sendProfileUpdateMail(Mockito.any(User.class));
		
		assertEquals(profileDTO.getFirstName(), userService.updateProfile(profileDTO, user.getId()).getFirstName());
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void shouldNotUpdateProfile() {
		
		User user = MockModelFactory.getMockUser();
		
		ProfileDTO profileDTO = MockModelFactory.getMockProfileDTO();
		
		Mockito.when(userRepository.findUserById(Matchers.anyLong())).thenReturn(null);
		
		userService.updateProfile(profileDTO, user.getId());
		
	}
	
	@Test
	public void shouldFindUserByToken() {
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(1l);
		
		Mockito.when(userRepository.findUserByToken(Matchers.anyString())).thenReturn(userDTO);
		
		assertEquals(userDTO.getId(), userService.findUserByToken("tokenValue").getId());
	}
}
