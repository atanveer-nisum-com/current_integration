package com.nisum.user.api.controller;

//import com.nisum.common.shop.dto.RegistrationDTO;
import com.nisum.user.dto.UserRegistrationDTO;
import com.nisum.user.model.User;
import com.nisum.user.service.CountryService;
import com.nisum.user.service.StateService;
import com.nisum.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/rest/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CountryService countryService;

    @Autowired
    StateService stateService;

    @GetMapping("/find/{id}")
    public User findUserById(@PathVariable("id")  Long id){
        return userService.findOne(id);
    }

    @GetMapping("/alive/{userId}/{token}")
    public Boolean isAlive(@PathVariable("userId") String userId,@PathVariable("token") String token){

        return userService.isAlive(userId,token);

    }


    /**
     *
     * @return userid
     */
    @GetMapping("/create/guest")
    public User getGuestUserId() {
        return userService.saveGuest();
    }


    /**
     * Save nisum.
     *
     * @param registrationDTO the registration DTO
     * @return the boolean
     */
    @PostMapping("/checkout/saveuser")
    public Boolean saveUser(@RequestBody UserRegistrationDTO registrationDTO) {
        //set services to the DTO
        registrationDTO.setCountryService(countryService);
        registrationDTO.setStateService(stateService);
        registrationDTO.setUserService(userService);

        //update the nisum with the given information
        User userToUpdate = registrationDTO.toUser();
        userService.update(userToUpdate);

        return true;
    }




}
