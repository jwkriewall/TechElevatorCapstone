package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.tournamentDAO;
import com.techelevator.model.Tournament;

@CrossOrigin
@RestController
public class TournamentController {
	
	private tournamentDAO tournamentDAO;
	
	TournamentController(tournamentDAO tournamentDAO){
		this.tournamentDAO = tournamentDAO;
	};
	
	
	@RequestMapping(path="tournaments", method = RequestMethod.POST)
	public void createTournament(Tournament tournament) {
		tournamentDAO.createTournament(tournament);
	};
	
	@RequestMapping(path="tournaments", method = RequestMethod.GET)
	public List<Tournament> getAllTournaments() {
		return tournamentDAO.listAllTournaments();
	}

}
