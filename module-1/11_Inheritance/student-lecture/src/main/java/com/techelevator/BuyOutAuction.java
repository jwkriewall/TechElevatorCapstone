package com.techelevator;


public class BuyOutAuction extends Auction {
	
	private int buyoutPrice;

	public BuyOutAction (String itemForSale, int buyoutPrice) {
		super(itemForSale);
		this.buyoutPrice = buyoutPrice;
	
	}
}
