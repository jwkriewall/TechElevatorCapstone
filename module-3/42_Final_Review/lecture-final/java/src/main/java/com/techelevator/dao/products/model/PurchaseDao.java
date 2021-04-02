package com.techelevator.dao.products.model;

import java.util.List;

public interface PurchaseDao {
	
	Purchase addPurchase(String username, List<Product> products);
	
}
