package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Tournament;

public interface tournamentDAO {
	// find tournament by name - future
	// find tournament by ID
	// find tournaments entered (by username or userID?)
	
	public Tournament createTournament(Tournament tournament);
	public List<Tournament> listAllTournaments();
	

}
