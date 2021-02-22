package com.techelevator.services;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.models.Auction;

public class AuctionService {

    public static final String BASE_URL = "http://localhost:3000/auctions";
    public RestTemplate restTemplate = new RestTemplate();
    private final ConsoleService console = new ConsoleService();


    public Auction[] listAllAuctions() {
    	String endUrl = BASE_URL;
        Auction[] auction = restTemplate.getForObject(endUrl, Auction[].class);
        return auction;
    }

    public Auction listDetailsForAuction(int id) {
        String endUrl = BASE_URL + "/" + id;
        Auction listDetails = restTemplate.getForObject(endUrl, Auction.class);
        return listDetails;
    }

    public Auction[] findAuctionsSearchTitle(String title) {
    	String endUrl = "http://localhost:3000/auctions?title_like=";
    	
        Auction []findAuction = restTemplate.getForObject(endUrl + title, Auction[].class);
        return findAuction;
    }

    public Auction[] findAuctionsSearchPrice(double price) {
        String endUrl = "http://localhost:3000/auctions?currentBid_lte=";
        
        Auction[] findAuctionByPrice = restTemplate.getForObject(endUrl + price, Auction[].class);
        return findAuctionByPrice;
    }

}
