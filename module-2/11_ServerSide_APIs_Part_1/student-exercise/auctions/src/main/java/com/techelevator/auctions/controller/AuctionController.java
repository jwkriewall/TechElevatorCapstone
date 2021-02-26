package com.techelevator.auctions.controller;

import com.techelevator.auctions.DAO.AuctionDAO;
import com.techelevator.auctions.DAO.MemoryAuctionDAO;
import com.techelevator.auctions.auctionNotFoundException.auctionNotFoundException;
import com.techelevator.auctions.model.Auction;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/auctions")
public class AuctionController {

    private AuctionDAO dao;

    public AuctionController() {
        this.dao = new MemoryAuctionDAO();
    }
    
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Auction> list(@RequestParam (required = false, defaultValue = "") String title_like, 
    						  @RequestParam (required = false, defaultValue = "0") double currentBid_lte){
    	//List<Auction> auctions = new ArrayList<Auction>();
    	
    	
    	if (currentBid_lte > 0 && title_like != null) {
    		return dao.searchByTitleAndPrice(title_like, currentBid_lte);
    	}
    	
    	if (currentBid_lte > 0) {
        	return dao.searchByPrice(currentBid_lte);
        }
    	
    	if (title_like != null) {
    		return dao.searchByTitle(title_like);
    	}
        
        return dao.list();
    	
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Auction get(@PathVariable int id) {
    	return dao.get(id);
    }
    
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Auction create(@RequestBody Auction auction) throws auctionNotFoundException {
    	return dao.create(auction);
    }
    
    

}
