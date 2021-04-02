package com.techelevator.dao.products.model;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcPurchaseDao implements PurchaseDao {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcPurchaseDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Purchase addPurchase(String username, List<Product> products) {
		Purchase purchase = new Purchase();
		purchase.setProducts(products);
		
		String sql = "INSERT INTO purchases (user_id) VALUES ( (SELECT user_id FROM users WHERE username = ?) ) RETURNING id";
		Integer purchaseId = jdbcTemplate.queryForObject(sql, Integer.class, username);
		purchase.setPurchaseId(purchaseId);
		
		for (Product product : products) {
			insertProductForPurchase(purchaseId, product);
			updateQuantity(product);
		}
		
		return purchase;
	}
	
	private void insertProductForPurchase(int purchaseId, Product product) {
		String sql = "INSERT INTO purchase_product (product_id, purchase_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, product.getId(), purchaseId);
	}
	
	private void updateQuantity(Product product) {
		String sql = "UPDATE products SET quantity = quantity - 1 WHERE id = ?";
		jdbcTemplate.update(sql, product.getId());
	}
	
	
	
	
}
