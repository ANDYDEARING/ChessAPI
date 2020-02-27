package com.infy.chessapi.service;

import java.util.List;

import com.infy.chessapi.model.BoardState;

public interface ChessService {
	public Boolean populateTestData();

	public String authenticateCustomer(String username, String password) throws Exception;

	public List<BoardState> getGames(String authToken) throws Exception;

	public BoardState getGame(String authToken, Integer gameId) throws Exception;

	public String getUsernameFromToken(String authToken) throws Exception;

	public Boolean makeMove(String authToken, BoardState boardState) throws Exception;
	
	public Boolean verifyMove(String username, BoardState previousState, BoardState newState) throws Exception;

	public Boolean startGame(String authToken, String targetUserName) throws Exception;
}
