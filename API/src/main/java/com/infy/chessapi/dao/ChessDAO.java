package com.infy.chessapi.dao;

import java.time.LocalDate;
import java.util.List;

import com.infy.chessapi.model.BoardState;

public interface ChessDAO {

	public BoardState getBoardState(Integer gameId);
	public String getPassword(String username);
	public List<BoardState> getGames(String user);
	public String getUserFromToken(String authToken);
	public Boolean updateBoardState(BoardState board);
	public Boolean createGame(BoardState board);
	public List<BoardState> getBoardStatesAfter(LocalDate timestamp, String user);
	public String getUserFromID(String userID);
	public Boolean populateTestData(List<String> usernames, List<String> passwords, List<BoardState> boardStates);
	
}
