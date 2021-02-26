package com.techelevator.auctions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NO_CONTENT)
public class noAuctionFound extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public noAuctionFound() {
		super("Auction deleted.");
	}
}
