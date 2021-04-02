package com.techelevator.products.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.products.model.Product;
import com.techelevator.dao.products.model.Purchase;
import com.techelevator.dao.products.model.PurchaseDao;

@RestController
@CrossOrigin
@RequestMapping("/purchases")
@PreAuthorize("isAuthenticated()")
public class PurchaseController {

	private PurchaseDao purchaseDao;
	
	public PurchaseController(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	@RequestMapping(path="", method = RequestMethod.POST)
	public Purchase add(Principal principal, @RequestBody List<Product> products) {
		return purchaseDao.addPurchase(principal.getName(), products);
	}
}
