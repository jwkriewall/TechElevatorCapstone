package com.techelevator.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.model.Tournament;

@Component
public interface tournamentDAO {
	// find tournament by name - future
	// find tournament by ID
	// find tournaments entered (by username or userID?)
	
	public Tournament createTournament(Tournament tournament);
	public List<Tournament> listAllTournaments();
	public Tournament getTournamentById(int tournamentId);
	public void updateTournament(Tournament tournament, int id);
	public void deleteTournament(int id);
	

//	
//	public List<Tournament> listAllTournamentsByOrganizerId(int organizerId);
//	public List<Tournament> listAllTournamentsByUserId(int userId);
}
