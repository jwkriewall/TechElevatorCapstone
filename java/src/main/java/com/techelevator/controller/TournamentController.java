package com.techelevator.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.TournamentDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Tournament;
import com.techelevator.model.User;
import com.techelevator.model.UserRanking;

@RestController
@CrossOrigin

public class TournamentController {
	
	private TournamentDAO tournamentDAO;
	private UserDAO userDAO;
	
	TournamentController(TournamentDAO tournamentDAO, UserDAO userDAO) {
		this.tournamentDAO = tournamentDAO;
		this.userDAO = userDAO;
	}
	
	@RequestMapping(path="/tournaments", method = RequestMethod.GET)
	public List<Tournament> getAllTournaments() {
		return tournamentDAO.listAllTournaments();
	}
	
	@RequestMapping(path="/tournaments/{id}", method = RequestMethod.GET)
	public Tournament getTournamentById(@PathVariable int id) {
		return tournamentDAO.getTournamentById(id);
	}
	
	@RequestMapping(path="/tournaments", method = RequestMethod.POST)
	public Tournament createTournament(@RequestBody Tournament tournament) {
		return tournamentDAO.createTournament(tournament);
	}
	
	@RequestMapping(path="/tournaments/{id}", method = RequestMethod.PUT)
	public void updateTournament(@RequestBody Tournament tournament) {
		tournamentDAO.updateTournament(tournament);
	}
	
	@RequestMapping(path="/tournaments/{id}", method = RequestMethod.DELETE)
	public void deleteTournament(@PathVariable int id) {
		tournamentDAO.deleteTournament(id);
	}
	
	
	// this is not working
	@RequestMapping(path="/tournaments/organizers/{id}", method = RequestMethod.GET)
	public List<Tournament> getAllTournamentsByOrganizerId(@PathVariable int id) {
		return tournamentDAO.listAllTournamentsByOrganizerId(id);
	}
	
	@RequestMapping(path="/users/tournaments/", method = RequestMethod.GET)
	public List<Integer> listAllTournamentsByUserId(Principal principal){
		int userID = userDAO.findIdByUsername(principal.getName());
		return tournamentDAO.listAllTournamentsByUserId(userID);
	}
	
	@RequestMapping(path="/tournaments/{id}/registration/", method = RequestMethod.POST)
	public void addUserToTournament(Principal principal, @PathVariable int id) {
		int userID = userDAO.findIdByUsername(principal.getName());
		tournamentDAO.addUserToTournament(id, userID);
	}
	
	@RequestMapping(path="/tournaments/{id}/rankings/", method = RequestMethod.GET)
	public List<UserRanking> getUserRankingByTournamentId (@PathVariable int id){
		return tournamentDAO.getUserRankingByTournamentId(id);
	}

}
 