package com.techelevator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.dao.tournamentDAO;
import com.techelevator.model.Tournament;


public class TournamentController {
	
	private tournamentDAO tournamentDAO;
	
	TournamentController(tournamentDAO tournamentDAO){
		this.tournamentDAO = tournamentDAO;
	};
	
	
	@RequestMapping(path="/tournaments", method = RequestMethod.POST)
	public void createTournament(Tournament tournament) {
		tournamentDAO.createTournament(tournament);
	};

}
