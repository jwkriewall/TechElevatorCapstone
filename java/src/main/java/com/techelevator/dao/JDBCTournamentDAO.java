package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Tournament;

@Component
public class JDBCTournamentDAO implements tournamentDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCTournamentDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	};

	@Override
	public Tournament createTournament(Tournament tournament) {
		// updated query to return all items needed for tournament including tournament id
		// String sql = "INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double) VALUES (?, ?, ?, ?, ?)";
		String sql = "INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id, tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, tournament.getName(), tournament.getOrganizerId(), tournament.getMaxParticipants(), tournament.isTeam(), tournament.isDouble(), tournament.getStartDate(), tournament.getEndDate());
		while (rows.next()) {
			tournament = mapRowToTournament(rows);
		}
		return tournament;
	}

	@Override
	public List<Tournament> listAllTournaments() {
		List<Tournament> tournaments = new ArrayList();
		
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
		String sql = "UPDATE tournament SET tournament_name = ?, max_participants = ?, is_team = ?, is_double = ?, start_date = ?, end_date = ? " + 
				"WHERE id = ?";
		jdbcTemplate.update(sql, tournament.getName(), tournament.getMaxParticipants(), tournament.isTeam(), tournament.isDouble(), tournament.getStartDate(), tournament.getEndDate(), tournament.getId());
		
	}
	
	@Override
	public void deleteTournament(int id) {
		String sql = "DELETE FROM tournament WHERE id = ?";
		jdbcTemplate.update(sql, id);
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
