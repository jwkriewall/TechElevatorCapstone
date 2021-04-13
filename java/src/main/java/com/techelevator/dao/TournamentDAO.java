package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.model.Tournament;
import com.techelevator.model.User;
import com.techelevator.model.UserRanking;

@Component
public interface TournamentDAO {
	// find tournament by name - future
	// find tournament by ID
	// find tournaments entered (by username or userID?)
	
	public Tournament createTournament(Tournament tournament);
	public List<Tournament> listAllTournaments();
	public Tournament getTournamentById(int tournamentId);
	public void updateTournament(Tournament tournament);
	public void deleteTournament(int id);
	
	public List<Tournament> listAllTournamentsByOrganizerId(int organizerId);
	public List<Integer> listAllTournamentsByUserId(long userId);
	
	public void addUserToTournament(UserRanking user, int tournamentID);

	// get user ranking by tournament ID
	public List<UserRanking> getUserRankingByTournamentId(int tournamentId);
	
	public List<UserRanking> getEmailOptInUsers(int tournamentId);

}
