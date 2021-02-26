package com.techelevator.auctions.auctionNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.NOT_FOUND, reason = "Auction Not Found.")
public class auctionNotFoundException extends Exception {


	private static final long serialVersionUID = 1L;

	public auctionNotFoundException() {
		super("Auction Not Found");
	}
	
	
}
