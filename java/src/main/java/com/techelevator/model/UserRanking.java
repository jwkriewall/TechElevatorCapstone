package com.techelevator.model;

public class UserRanking {
	
	private int tournamentId;
	private int userId;
	private int userSeeding;
	private String userNickname;
	
	
	public int getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserSeeding() {
		return userSeeding;
	}
	public void setUserSeeding(int userSeeding) {
		this.userSeeding = userSeeding;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

}
