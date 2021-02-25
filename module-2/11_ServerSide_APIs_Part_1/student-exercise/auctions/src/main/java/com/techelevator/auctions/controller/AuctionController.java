package com.techelevator.auctions.controller;

import com.techelevator.auctions.DAO.AuctionDAO;
import com.techelevator.auctions.DAO.MemoryAuctionDAO;
import com.techelevator.auctions.model.Auction;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/request")
public class AuctionController {

    private AuctionDAO dao;

    public AuctionController() {
        this.dao = new MemoryAuctionDAO();
    }
    
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Auction> list(){
    	
    	List<Auction> allAuctions = new ArrayList<Auction>();
    	List<Auction> auctions = dao.list();
    	
    	for (Auction auction : auctions) {
    		allAuctions.add(auction);
    	}
    	return allAuctions;
    }

}
