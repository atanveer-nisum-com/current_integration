package com.nisum.user.service.impl;

import com.nisum.common.constant.AppConstant;
import com.nisum.common.integration.user.dto.UserDTO;
import com.nisum.common.util.AESEncryptionUtils;
import com.nisum.common.util.DateUtils;
import com.nisum.common.validation.ValidationUtility;
import com.nisum.user.dto.AddressDTO;
import com.nisum.user.dto.ProfileDTO;
import com.nisum.user.model.Address;
import com.nisum.user.model.State;
import com.nisum.user.model.User;
import com.nisum.user.repository.UserRepository;
import com.nisum.user.service.EmailService;
import com.nisum.user.service.StateService;
import com.nisum.user.service.UserService;
import com.nisum.user.util.AddressConverter;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/** The email service. */
	@Autowired
	private EmailService emailService;
	@Autowired
	private StateService stateService;
	
	@Autowired
	AddressConverter addressConverter;
	
	/* (non-Javadoc)
	 * @see com.nisum.service.UserService#findOne(java.lang.Long)
	 */
	public User findOne(Long id) {
		return userRepository.findUserById(id);
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.UserService#saveGuest()
	 */
	public User saveGuest() {
		User user = new User();
		user.setPassword("guest");
		user.setCreatedAt(DateUtils.getCurrentTime());
		user.setEmailAddress("guest@nisum.com");
		user.setFirstName("Guest");
		user.setLastName("Guest");
		user.setIsDeleted(AppConstant.NOT_DELETED);
		user.setIsGuest(AppConstant.IS_GUEST_USER);
		user.setCreatedAt(DateUtils.getCurrentTime());
		user.setAuthToken(UUID.randomUUID().toString());
		user.setTokenValidity(DateUtils.getCurrentTime() + DateUtils.convertDaysIntoSeconds(AppConstant.LOGIN_TOKEN_VALIDITY_IN_DAYS));
		user.setTokenCreatedAt(user.getCreatedAt());
		
		return userRepository.save(user);
	}
	
	/* (non-Javadoc)
	 * @see com.nisum.service.UserService#findAllUsers()
	 */
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.UserService#createUser(com.nisum.model.User)
	 */
	@Override
	public User createUser(User user) {
		if(user.getAddresses()!=null && user.getAddresses().size()>0) {
			//Validation of address;
			for(Address address : user.getAddresses()) {
				if(address.getCountryBean().getId() == 230 ) {
					State state =stateService.findStateById(address.getStateBean().getId());
					if(!ValidationUtility.isZipCodeValid(state.getAbbreviation(),address.getZipcode())) {
						throw new InvalidDataAccessApiUsageException("Zipcode invalid");
					}
				}
				if(address.getIsDeleted()==null){
					address.setIsDeleted((byte)0);	
				}
				if(address.getIsDefault()==null){
					address.setIsDefault((byte)0);	
				}
				address.setCreatedAt(DateUtils.getCurrentTime());
			}
			user.assignUserToAddresses();
			//Set Default user fields
			user.setCreatedAt(DateUtils.getCurrentTime());
			user.setIsDeleted((byte) 0);
			user.setIsGuest((byte) 0);
			
			user.setPassword(AESEncryptionUtils.encrypt(user.getPassword()));
			
			// Set token fields
			user.setAuthToken(UUID.randomUUID().toString());
			user.setTokenValidity(DateUtils.getCurrentTime() + DateUtils.convertDaysIntoSeconds(AppConstant.LOGIN_TOKEN_VALIDITY_IN_DAYS));
			user.setTokenCreatedAt(user.getCreatedAt());
		}
		
		//Validation of user email address
		if(findUserByEmail(user.getEmailAddress())!=null) {
			throw new DuplicateKeyException("Email exists");
		}
		
		//End User Validation
		
		return userRepository.save(user);
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.UserService#findUserByEmail(java.lang.String)
	 */
	@Override
	public User findUserByEmail(String email) {
		List<User> users = userRepository.findUserByEmail(email);
		
		if(users != null && users.size() > 0) 
			return users.get(0); 
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.UserService#update(com.nisum.model.User)
	 */
	@Override
	public User update(User user) {
		
		user.setUpdatedAt(System.currentTimeMillis() / 1000L);
		return userRepository.save(user);
		
	}
	
	/* (non-Javadoc)
	 * @see com.nisum.service.UserService#findUserByEmailAddressAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public User findUserByEmailAddressAndPassword(String emailAddress, String password) {
		User user = userRepository.findUserByEmailAddressAndPassword(emailAddress, password);
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.nisum.service.UserService#findActiveUserByEmail(java.lang.String)
	 */
	@Override
	public User findActiveUserByEmail(String email) {
		List<User> users = userRepository.findActiveUserByEmail(email);
		
		if(users != null && users.size() > 0) 
			return users.get(0); 
		
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see com.nisum.service.UserService#findUserByResetToken(java.lang.String)
	 */
	@Override
	public User findUserByResetToken(String resetToken) {
		List<User> users = userRepository.findUserByResetToken(resetToken);
		
		if(users != null && users.size() > 0) 
			return users.get(0); 
		
		return null;
	}
	
	@Override
	public User findUserByTokenAndId(String token, Long id) {
		List<User> users = userRepository.findUserByTokenAndId(token, id);
		
		if(users != null && users.size() > 0) 
			return users.get(0); 
		
		return null;
		
	}

	@Override
	public Boolean isAlive(String userId, String accessToken) {
		return userRepository.findOne(new Long(userId), accessToken, DateUtils.getCurrentTime()) == null 
				? Boolean.FALSE : Boolean.TRUE;
	}

	public ProfileDTO getProfile(Long id) {
		User user = this.findOne(id);
		if(Objects.isNull(user)) throw new EmptyResultDataAccessException(1);
		ProfileDTO profileDTO = new ProfileDTO();
//		profileDTO.setAddresses(ETDUtils.toAddressDTO(user.getAddresses()));
		profileDTO.setAddresses(convertEntitesToDtos(user.getAddresses()));
		profileDTO.setEmailAddress(user.getEmailAddress());
		profileDTO.setFirstName(user.getFirstName());
		profileDTO.setLastName(user.getLastName());
		return profileDTO;
	}
	
	
	
	@Override
	public ProfileDTO updateProfile(ProfileDTO profileDTO, long userId) throws IllegalArgumentException {
		
		User user = this.findOne(userId);
		if(Objects.isNull(user)) throw new EmptyResultDataAccessException(1);
		Set<AddressDTO> addressSet = profileDTO.getAddresses();
		Address address = null;

		for (AddressDTO anAddressSet : addressSet) {
			address = addressConverter.convertToEntity(anAddressSet);
			address.setUser(user);
			if (address.getId() == null) {
				user.addAddress(address);
			} else {
				Set<Address> userAddressSet = user.getAddresses();
				for (Address userAddress : new HashSet<Address>(userAddressSet)) {
					if (userAddress.getId().equals(address.getId())) {
						address.setIsDefault(userAddress.getIsDefault());
						address.setIsDeleted(userAddress.getIsDeleted());
						user.removeAddress(userAddress);
						user.addAddress(address);
					}
				}
			}
		}
		//Validation of user email address
		if (!user.getEmailAddress().equals(profileDTO.getEmailAddress())) {
			if(findUserByEmail(profileDTO.getEmailAddress())!=null) {
				throw new DuplicateKeyException("Email already exists");
			}else {
				user.setEmailAddress(profileDTO.getEmailAddress());
			}
		}
		if (profileDTO.getCurrentPassword() != null && !profileDTO.getCurrentPassword().equalsIgnoreCase("")) {
			if (!user.getPassword().equals(AESEncryptionUtils.encrypt(profileDTO.getCurrentPassword()))) {
				throw new InvalidPropertyException(User.class, "password", "Invalid current password");
			} else {
				if (profileDTO.getNewPassword().equals(profileDTO.getConfirmPassword())) {
					user.setPassword(AESEncryptionUtils.encrypt(profileDTO.getNewPassword()));
					
				}else {
					throw new IllegalArgumentException("New password mismatch");
				}
			}
		}

		user.setFirstName(profileDTO.getFirstName());
		user.setLastName(profileDTO.getLastName());
		user = userRepository.save(user);
		ProfileDTO updatedProfileDTO = new ProfileDTO();
//		updatedProfileDTO.setAddresses(ETDUtils.toAddressDTO(user.getAddresses()));
		Set<AddressDTO> addressDTOList = convertEntitesToDtos(user.getAddresses());
		updatedProfileDTO.setAddresses(addressDTOList);
		updatedProfileDTO.setEmailAddress(user.getEmailAddress());
		updatedProfileDTO.setFirstName(user.getFirstName());
		updatedProfileDTO.setLastName(user.getLastName());
		emailService.sendProfileUpdateMail(user);
		return updatedProfileDTO;
	}

	private Set<AddressDTO> convertEntitesToDtos(Set<Address> addressSet) {
		Set<AddressDTO> addressDTOList= new HashSet<AddressDTO>();
		for(Address cAddress: addressSet){
			addressDTOList.add(addressConverter.convertToDto(cAddress));
		}
		return addressDTOList;
	}

	@Override
	public UserDTO findUserByToken(String token) {
		return userRepository.findUserByToken(token);
	}
}