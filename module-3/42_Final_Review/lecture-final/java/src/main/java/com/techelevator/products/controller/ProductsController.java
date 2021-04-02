package com.techelevator.products.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.products.model.Product;
import com.techelevator.dao.products.model.ProductDao;

@CrossOrigin
@RestController
@RequestMapping("/products")
@PreAuthorize("isAuthenticated()")
public class ProductsController {

	private ProductDao productDao;
	
	public ProductsController(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@RequestMapping(path="", method=RequestMethod.GET)
	public List<Product> list() {
		return productDao.getAllProducts();
	}
	
}
