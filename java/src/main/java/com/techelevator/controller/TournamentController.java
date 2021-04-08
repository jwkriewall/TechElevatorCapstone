package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.tournamentDAO;
import com.techelevator.model.Tournament;
import com.techelevator.model.User;

@CrossOrigin
@RestController
public class TournamentController {
	
	private tournamentDAO tournamentDAO;
	
	TournamentController(tournamentDAO tournamentDAO) {
		this.tournamentDAO = tournamentDAO;
	}
	
	// FIXED -----------
	// Below is message we are getting on Postman...
	// It is modifying the database but returning a 500 error: 
	// No results were returned by the query.; nested exception is org.postgresql.util.PSQLException: No results were returned by the query.
	
	
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
	
	@RequestMapping(path="tournaments/organizers/{id}", method = RequestMethod.GET)
	public List<Tournament> getAllTournamentsByOrganizerId(@PathVariable int organizerId) {
		return tournamentDAO.listAllTournamentsByOrganizerId(organizerId);
	}
	
	@RequestMapping(path="users/tournaments/", method = RequestMethod.GET)
	public List<Tournament> getAllTournamentsByUserId(@RequestBody User user){
		return tournamentDAO.listAllTournamentsByUserId(user.getId());
	}
	
	

}
 