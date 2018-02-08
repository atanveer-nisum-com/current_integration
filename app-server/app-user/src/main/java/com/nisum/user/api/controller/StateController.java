package com.nisum.user.api.controller;


import com.nisum.user.model.State;
import com.nisum.user.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("StateControllerApi")
@RequestMapping("/api/rest/state")
public class StateController {

    @Autowired
    StateService stateService;

    @GetMapping("/find/{id}")
    public State findStateById(@PathVariable("id") Long id){
        return stateService.findStateById(id);
    }


}
