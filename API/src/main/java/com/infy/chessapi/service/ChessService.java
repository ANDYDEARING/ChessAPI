package com.infy.chessapi.service;

import com.infy.chessapi.model.User;

public interface ChessService {
	public Boolean populateTestData();

	public User authenticateCustomer(String username, String password) throws Exception;
}
