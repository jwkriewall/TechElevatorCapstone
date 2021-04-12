package com.techelevator.dao;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Tournament;
import com.techelevator.model.User;
import com.techelevator.model.UserAlreadyExistsException;
import com.techelevator.model.UserRanking;

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
		String sql = "UPDATE tournament SET tournament_name = ?, organizer_id = ?, max_participants = ?, is_team = ?, is_double = ?, start_date = ?, end_date = ?, ended = ? " + 
				"WHERE id = ?";
		jdbcTemplate.update(sql, tournament.getName(), tournament.getOrganizerId(), tournament.getMaxParticipants(), tournament.isTeam(), tournament.isDouble(), tournament.getStartDate(), tournament.getEndDate(), tournament.isEnded(), tournament.getId());
		
	}
	
	@Override
	public void addUserToTournament(UserRanking user, int tournamentID) {
		// Checking to see if user is already in tournament
		String sqlStatement = "SELECT user_id FROM tournament_user WHERE tournament_id = ? AND user_id = ?";
		SqlRowSet userIdInTournament = jdbcTemplate.queryForRowSet(sqlStatement, tournamentID, user.getUserId());
		
		// if not already in tournament
		if (!userIdInTournament.next()) {
				String sql = "INSERT INTO tournament_user VALUES (?, ?, ?, ?, ?)";
				jdbcTemplate.update(sql, tournamentID, user.getUserId(), user.getUserSeeding(), user.getUserNickname(), user.isNotify()); 
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
		String sql = "SELECT id, tournament_name, tournament.organizer_id, max_participants, is_team, is_double, start_date, end_date, ended FROM tournament " + 
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
	public List<Integer> listAllTournamentsByUserId(long userId) {
		List<Integer> userTournaments = new ArrayList<Integer>();
		String sql = "SELECT tournament_id FROM tournament_user " 
					+ "WHERE user_id = ?";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userId);
		while(rows.next()) {
			userTournaments.add(rows.getInt("tournament_id"));
		}
		return userTournaments;
	}
	
	
	@Override
	public List<UserRanking> getUserRankingByTournamentId(int tournamentId) {
		List<UserRanking> tournamentRankings = new ArrayList<UserRanking>();
		
		String sql = "SELECT tournament_id, users.user_id, user_seeding, tournament_user.user_nickname, user_first_name, user_last_name, notify FROM tournament_user " + 
				"JOIN users ON users.user_id = tournament_user.user_id " + 
				"WHERE tournament_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, tournamentId);
		
		while(rows.next()) {
			tournamentRankings.add(mapUserRanking(rows));
		}
		return tournamentRankings;
	}
	

	
	private UserRanking mapUserRanking(SqlRowSet rows) {
		UserRanking userRanking = new UserRanking();
		
		userRanking.setTournamentId(rows.getInt("tournament_id"));
		userRanking.setUserId(rows.getInt("user_id"));
		userRanking.setUserSeeding(rows.getInt("user_seeding"));
		userRanking.setUserNickname(rows.getString("user_nickname"));
		
		if (rows.getString("user_first_name") != null) {
			userRanking.setFirstName(rows.getString("user_first_name"));
		}
		if (rows.getString("user_last_name") != null) {
			userRanking.setLastName(rows.getString("user_last_name"));
		}
		if (rows.getBoolean("notify")) {
			userRanking.setNotify(rows.getBoolean("notify"));
		}
		return userRanking;
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
		if(rows.getBoolean("ended")) {
			tournament.setEnded(rows.getBoolean("ended"));
		}
		return tournament;
	}





	
}
