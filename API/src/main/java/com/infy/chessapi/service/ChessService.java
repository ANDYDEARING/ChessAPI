package com.infy.chessapi.service;


public interface ChessService {
	public Boolean populateTestData();

	public String authenticateCustomer(String username, String password) throws Exception;
}
