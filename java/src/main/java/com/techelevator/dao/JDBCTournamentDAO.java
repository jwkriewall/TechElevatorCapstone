package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Tournament;
import com.techelevator.model.UserAlreadyExistsException;

@Component
public class JDBCTournamentDAO implements TournamentDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCTournamentDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	};

	@Override
	public Tournament createTournament(Tournament tournament) {
		String sql = "INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id, tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, tournament.getName(), tournament.getOrganizerId(), tournament.getMaxParticipants(), tournament.isTeam(), tournament.isDouble(), tournament.getStartDate(), tournament.getEndDate());
		while (rows.next()) {
			tournament = mapRowToTournament(rows);
		}
		return tournament;
	}

	@Override
	public List<Tournament> listAllTournaments() {
		List<Tournament> tournaments = new ArrayList<Tournament>();
		
		String sql = "SELECT id, tournament_name, organizer.organizer_id, max_participants, is_team, is_double, organizer_first_name, organizer_last_name, organizer_phone, organizer_email, start_date, end_date FROM tournament JOIN organizer ON organizer.organizer_id = tournament.organizer_id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		while (rows.next()) {
			Tournament tournament = mapRowToTournament(rows);
			tournaments.add(tournament);
		}
		return tournaments;
	}

	@Override
	public Tournament getTournamentById(int tournamentId) {
		String sql = "SELECT id, tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date FROM tournament WHERE id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, tournamentId);
		
		if(rows.next()) {
			return mapRowToTournament(rows);
		}
		return null;
	}	

	@Override
	public void updateTournament(Tournament tournament) {
		String sql = "UPDATE tournament SET tournament_name = ?, organizer_id = ?, max_participants = ?, is_team = ?, is_double = ?, start_date = ?, end_date = ? " + 
				"WHERE id = ?";
		jdbcTemplate.update(sql, tournament.getName(), tournament.getOrganizerId(), tournament.getMaxParticipants(), tournament.isTeam(), tournament.isDouble(), tournament.getStartDate(), tournament.getEndDate(), tournament.getId());
		
	}
	
	@Override
	public void addUserToTournament(int id, long userId) {
		String sqlStatement = "SELECT user_id FROM tournament_user WHERE tournament_id = ? AND user_id = ?";
		SqlRowSet userIdInTournament = jdbcTemplate.queryForRowSet(sqlStatement, id, userId);
		
		if (!userIdInTournament.next()) {
				String sql = "INSERT INTO tournament_user VALUES (?, ?, ?, ?)";
				jdbcTemplate.update(sql, id, userId, null, "NICKNAME"); 
		} else throw new UserAlreadyExistsException();
		
	}
	
	
	@Override
	public void deleteTournament(int id) {
		String sql = "DELETE FROM tournament WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
	
	
	@Override
	public List<Tournament> listAllTournamentsByOrganizerId(int organizerId) {
		List<Tournament> organizerTournaments = new ArrayList<Tournament>();
		String sql = "SELECT id, tournament_name, tournament.organizer_id, max_participants, is_team, is_double, start_date, end_date FROM tournament " + 
				"JOIN organizer ON organizer.organizer_id = tournament.organizer_id " + 
				"WHERE organizer.organizer_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, organizerId);
		while(rows.next()) {
			Tournament tournament = mapRowToTournament(rows);
			organizerTournaments.add(tournament);
		}
		return organizerTournaments;
	}


	@Override
	public List<Tournament> listAllTournamentsByUserId(long userId) {
		List<Tournament> userTournaments = new ArrayList<Tournament>();
		String sql = "SELECT id, tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date FROM tournament " + 
				"JOIN tournament_user ON tournament.id = tournament_user.tournament_id " + 
				"JOIN users ON tournament_user.user_id = users.user_id " + 
				"WHERE users.user_id = ?";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userId);
		while(rows.next()) {
			Tournament tournament = mapRowToTournament(rows);
			userTournaments.add(tournament);
		}
		return userTournaments;
	}
	
	private Tournament mapRowToTournament (SqlRowSet rows) {
		Tournament tournament = new Tournament();
		tournament.setId(rows.getInt("id"));
		tournament.setName(rows.getString("tournament_name"));
		tournament.setOrganizerId(rows.getInt("organizer_id"));
		tournament.setMaxParticipants(rows.getInt("max_participants"));
		tournament.setTeam(rows.getBoolean("is_team"));
		tournament.setDouble(rows.getBoolean("is_double"));
		tournament.setStartDate(rows.getDate("start_date").toLocalDate());
		tournament.setEndDate(rows.getDate("end_date").toLocalDate());
		return tournament;
	}
	


	
}
