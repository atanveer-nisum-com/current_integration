package com.nisum.fcc.api.controller;

import com.nisum.fcc.dto.ProductDTO;
import com.nisum.fcc.dto.ShopProductDTO;
import com.nisum.fcc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/rest/products/")
public class ProductApiController {

	
	/** The product service. */
	@Autowired
	private ProductService productCassandraService;
	
	
	@GetMapping(value="item/{id}")
	public ProductDTO findProductById(@PathVariable UUID id, @RequestParam("iscart") Optional<Boolean> isforCart ) {
		return 	productCassandraService.getProductById(id,isforCart);
	}
	
	@GetMapping("{id}")
	public ShopProductDTO findProductById(@PathVariable UUID id) {
		return productCassandraService.getProductById(id);
	}
}
