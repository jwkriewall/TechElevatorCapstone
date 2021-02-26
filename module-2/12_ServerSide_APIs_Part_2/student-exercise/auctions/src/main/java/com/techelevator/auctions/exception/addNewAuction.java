package com.techelevator.auctions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus( code = HttpStatus.CREATED, reason = "Auction created!")
public class addNewAuction extends Exception{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public addNewAuction(){
		super("Auction Created!");
	    
	}
	
}
