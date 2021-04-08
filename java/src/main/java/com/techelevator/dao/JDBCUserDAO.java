package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.User;

public class JDBCUserDAO implements UserDAO {

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findIdByUsername(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean create(String username, String password, String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User updateUser(User user) {
		String sql = "";
		return null;
	}
	
	

}
