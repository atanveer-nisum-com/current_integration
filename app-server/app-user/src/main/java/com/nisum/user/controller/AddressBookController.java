package com.nisum.user.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.user.dto.AddressDTO;
import com.nisum.user.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * The Class AddressBookController.
 */
@RestController
@RequestMapping("users/addresses")
public class AddressBookController extends BaseController {


	/** The address book service. */
	@Autowired
	private AddressBookService addressBookService;

	/**
	 * List.
	 *
	 * @param userId
	 *            the user id
	 * @param pagable
	 *            the pagable
	 * @return the page/
	 */
	@GetMapping()
	public Page<AddressDTO> list(@RequestHeader(value = "userId",required = true) Long userId,
			@RequestParam(required = false) Pageable pagable) {
		Page<AddressDTO> addressBook;
		try {
			addressBook = addressBookService.findAddressListByUserId(userId, pagable);
		} catch (EmptyResultDataAccessException e) {
			throw new RestException(HttpStatus.NOT_FOUND, "No address found", ErrorLevel.ERROR);
		}
		return addressBook;
	}

	/**
	 * Creates the address.
	 *
	 * @param addressDto
	 *            the address dto
	 * @return the page
	 */
	@PostMapping()
	public Page<AddressDTO> createAddress(@RequestBody AddressDTO addressDto,@RequestHeader(value = "userId", required = true)Long userId) {
		if(Objects.isNull(addressDto))
			throw new RestException(HttpStatus.BAD_REQUEST,"Address does not have required information",
					ErrorLevel.ERROR);
		Page<AddressDTO> addressBook;
		try {
			addressBook = addressBookService.save(addressDto,userId);
		} catch (DuplicateKeyException e) {
			throw new RestException(HttpStatus.CONFLICT, "Address already exists", ErrorLevel.ERROR);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new RestException(HttpStatus.INSUFFICIENT_STORAGE, "Address limit reached", ErrorLevel.ERROR);
		}
		return addressBook;
	}

	/**
	 * Update address.
	 *
	 * @param addressDto
	 *            the address dto
	 * @return the page
	 */
	@PutMapping("/{addressId}")
	public Page<AddressDTO> updateAddress(@RequestBody AddressDTO addressDto,@PathVariable Long addressId,@RequestHeader(value = "userId", required = true)Long userId) {
		if(Objects.isNull(addressDto)){
			throw new RestException(HttpStatus.BAD_REQUEST,"Address does not have required information", ErrorLevel.ERROR);
		}
		try {
			addressDto.setId(addressId);
			return addressBookService.update(addressDto,userId);
		} catch (EmptyResultDataAccessException e) {
			throw new RestException(HttpStatus.NOT_FOUND, "Address could not be updated", ErrorLevel.ERROR);
		}
	}

	/**
	 * Removes the address.
	 *
	 * @param addressId
	 *            the address id
	 * @return the page
	 */
	@DeleteMapping("/{addressId}")
	public Page<AddressDTO> removeAddress(@PathVariable Long addressId, @RequestHeader(value = "userId", required = true) Long userId) {
		if(Objects.isNull(addressId)){
			throw new RestException(HttpStatus.BAD_REQUEST,"No address id given", ErrorLevel.ERROR);
		}
		Page<AddressDTO> address = addressBookService.removeAddress(addressId,userId);
		if (Objects.isNull(address)) throw new RestException(HttpStatus.NOT_FOUND, "Address not found for given id",
				ErrorLevel.ERROR);
		return address;
	}
}
