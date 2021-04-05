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
		String sql = "INSERT INTO tournament (id, tournament_name, organizer_id, max_participants, is_team, is_double) VALUES (DEFAULT, ?, ?, ?, ?, ?)";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, tournament.getName(), tournament.getOrganizerId(), tournament.getMaxParticipants(), tournament.isTeam(), tournament.isDouble());
		
		while (rows.next()) {
			tournament = mapRowToTournament(rows);
		}
		return tournament;
	}

	@Override
	public List<Tournament> listAllTournaments() {
		List<Tournament> tournaments = new ArrayList();
		
		String sql = "SELECT tournament_name, max_participants, is_team, is_double, organizer_first_name, organizer_last_name, organizer_phone, organizer_email FROM tournament JOIN organizer ON organizer.organizer_id = tournament.organizer_id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		while (rows.next()) {
			Tournament tournament = mapRowToTournament(rows);
			tournaments.add(tournament);
		}
		return tournaments;
	}

	
	
	private Tournament mapRowToTournament (SqlRowSet rows) {
		Tournament tournament = new Tournament();
		
		tournament.setName(rows.getString("tournament_name"));
		tournament.setOrganizerId(rows.getInt("organizer_id"));
		tournament.setMaxParticipants(rows.getInt("max_participants"));
		tournament.setTeam(rows.getBoolean("is_team"));
		tournament.setDouble(rows.getBoolean("is_double"));
		
		return tournament;
	}
	
}
