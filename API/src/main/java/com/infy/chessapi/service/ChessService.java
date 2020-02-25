package com.infy.chessapi.service;

import java.util.List;

import com.infy.chessapi.model.BoardState;

public interface ChessService {
	public Boolean populateTestData();

	public String authenticateCustomer(String username, String password) throws Exception;

	public List<BoardState> getGames(String authToken) throws Exception;

	public BoardState getGame(String authToken, String gameId) throws Exception;

	public String authenticateAuthToken(String authToken) throws Exception;
}
