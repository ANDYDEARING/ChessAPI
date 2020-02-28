package com.infy.chessapi.service;

import java.util.List;

import com.infy.chessapi.model.BoardState;

public interface ChessService {
	public Boolean populateTestData();

	public String authenticateCustomer(String username, String password) throws Exception;

	public List<BoardState> getGames(String sessionId) throws Exception;

	public BoardState getGame(String sessionId, Integer gameId) throws Exception;

	public String getUsernameFromSessionId(String sessionId) throws Exception;

	public Boolean makeMove(String sessionId, BoardState boardState) throws Exception;
	
	public Boolean verifyMove(String username, BoardState previousState, BoardState newState) throws Exception;

	public Boolean startGame(String sessionId, String targetUserName) throws Exception;
}
