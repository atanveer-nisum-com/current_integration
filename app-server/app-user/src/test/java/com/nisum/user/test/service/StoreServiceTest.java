package com.nisum.user.test.service;

import com.nisum.common.exception.rest.RestException;
import com.nisum.user.dto.StoreDTO;
import com.nisum.user.model.Store;
import com.nisum.user.model.User;
import com.nisum.user.repository.StoreRepository;
import com.nisum.user.service.StoreService;
import com.nisum.user.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

public class StoreServiceTest extends BaseServiceTest {

	/** The Store Service. */
	@Autowired 
	protected StoreService storeService;

	/** The store repository. */
	@MockBean
	protected StoreRepository storeRepository;
	
	@MockBean
	protected UserService userService;
	
	@Autowired
	ExecutorService executorService;
	@Autowired
	ModelMapper modelMapper;
	
	
	@Test
	public void shouldGetStoreById(){
		StoreDTO persistedStore;
		Store mockStore = createMockStore();
		
		Mockito.when(storeRepository.findById(anyLong())).thenReturn(mockStore);
		
		persistedStore = storeService.getStoreById(anyLong());
		
		assert(persistedStore.getName().equals("Macy's Mission Valley Home"));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void shouldThrowExceptionOnGetStoreById(){
		
		Mockito.when(storeRepository.findById(anyLong())).thenReturn(null);

		storeService.getStoreById(anyLong());

	}
	
	@Test
	public void getMockStoreByZipCode() {
		
		Store mockStore = createMockStore();
		
		List<Store> storeList = new ArrayList<Store>();
		storeList.add(mockStore);
		
		Page<Store> mockStorePage = new PageImpl<Store>(storeList);
		
		Page<StoreDTO> persistedStorePage;
		
		Mockito.when(storeRepository.findByZipcode(anyLong(),any())).thenReturn(mockStorePage);
		
		persistedStorePage = storeService.getStoreByZipcode(anyLong(), any());
		
		assertTrue(persistedStorePage.getContent().get(0).getName().equals("Macy's Mission Valley Home"));
		
	}
	
	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionOnStoreByZipCode(){
		
		Mockito.when(storeRepository.findByZipcode(anyLong(),any())).thenReturn(null);

		storeService.getStoreByZipcode(anyLong(), any());

	}
	
	@Test
	public void shouldSaveStore() {
		StoreDTO persistedStore;
		Store mockStore = createMockStore();
		User mockUser = createMockUser();
		
		Mockito.when(userService.findOne(anyLong())).thenReturn(mockUser);
		Mockito.when(storeRepository.findOne(anyLong())).thenReturn(mockStore);		
		Mockito.when(storeRepository.save(mockStore)).thenReturn(mockStore);
		
		persistedStore = storeService.saveStore(2L, 1L);
				
		assert(persistedStore.getName().equals("Macy's Mission Valley Home"));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void shouldThrowExceptionOnSaveStore(){
		
		Mockito.when(userService.findOne(anyLong())).thenReturn(null);
		Mockito.when(storeRepository.findOne(anyLong())).thenReturn(null);		
		//Mockito.when(storeRepository.save(mockStore)).thenReturn(null);
		
		storeService.saveStore(2L, 1L);

	}
	
	@Test
	public void shouldGetStoreByUserId(){
		StoreDTO persistedStore;
		Store mockStore = createMockStore();
		
		Mockito.when(storeRepository.findByUserId(anyLong())).thenReturn(mockStore);
		
		persistedStore = storeService.getStoreByUserId(anyLong());
		
		assert(persistedStore.getName().equals("Macy's Mission Valley Home"));
	}
	
	@Test(expected= EmptyResultDataAccessException.class)
	public void shouldThrowExceptionOnGetStoreByUserId(){
		
		Mockito.when(storeRepository.findByUserId(anyLong())).thenReturn(null);
		
		storeService.getStoreByUserId(anyLong());

	}
	
	@Test
	public void shouldGetStores(){
		Store mockStore = createMockStore();
		List<Store> storeList = new ArrayList<>();
		storeList.add(mockStore);
		Page<StoreDTO> persistedStorePage;
		
		Mockito.when(storeRepository.findAll()).thenReturn(storeList);
		
		persistedStorePage = storeService.getStores();
		
		assert(persistedStorePage.getContent().get(0).getName().equals("Macy's Mission Valley Home"));

	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void shouldThrowExceptionOnGetStores(){
		
		Mockito.when(storeRepository.findAll()).thenReturn(null);
		
		storeService.getStores();

	}
	
	private User createMockUser() {
		User user = new User();
		user.setId(1L);
		user.setFirstName("John");
		return user;
	}

	private Store createMockStore() {
		
		User user = new User();
		user.setId(1L);
		user.setFirstName("John");
		ArrayList<User> users = new ArrayList<>();
		users.add(user);
		
		Store store = new Store();
		store.setId(1L);
		store.setName("Macy's Mission Valley Home");
		store.setZipcode(97500L);
		store.setIsDeleted((byte)0);
		store.setUsers(users);
		
		return store;
	}


	
	
}
