package com.infy.chessapi.dao;

import java.util.List;

import com.infy.chessapi.model.BoardState;

public interface ChessDAO {

	public BoardState getBoardState(Integer gameId);
	public String getPassword(String username);
	public List<BoardState> getGames(String user);
	public String getUserFromSessionId(String sessionId);
	public Boolean updateBoardState(BoardState board);
	public Boolean createGame(BoardState board);
	public Boolean verifyUserExists(String username);
	public Boolean populateTestData(List<String> usernames, List<String> passwords, List<BoardState> boardStates);
	
}
