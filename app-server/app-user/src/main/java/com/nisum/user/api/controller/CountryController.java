package com.nisum.user.api.controller;


import com.nisum.user.model.Country;
import com.nisum.user.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("CountryControllerApi")
@RequestMapping("/api/rest/country")

public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/find/{id}")
    public Country findOneById(@PathVariable  Long id){
        return countryService.findById(id);
    }
}
