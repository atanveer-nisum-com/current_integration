package com.nisum.shop.controller;

import com.nisum.shop.model.Tax;
import com.nisum.shop.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * The Class TaxController.
 */
@RestController
@RequestMapping("tax")
public class TaxController extends BaseController {
	
	/** The tax service. */
	@Autowired
	private TaxService taxService;
	
	/**
	 * Gets the by param.
	 *
	 * @param abbrv the abbrv
	 * @param id the id
	 * @param state the state
	 * @return the by param
	 */
	@GetMapping
	public Tax getByParam(@RequestParam(required=false) String abbrv, @RequestParam(required=false) Long id, @RequestParam(required=false) String state) {
		return taxService.findByAttributes(abbrv,id,state);
	}
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@GetMapping("/list")
	public List<Tax> getAll() {
		return taxService.findAll();
	}
	

}
