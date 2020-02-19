package com.infy.chessapi.dao;

import java.time.LocalDate;

import com.infy.chessapi.model.BoardState;

public interface ChessDAO {

	public BoardState getBoardState(String gameId);
	public String getPassword(String username);
	public BoardState[] getGames(String user);
	public String getUserFromToken(String authToken);
	public Boolean updateBoardState(BoardState board);
	public Boolean createGame(BoardState board);
	public BoardState[] getBoardStatesAfter(LocalDate timestamp, String user);
	public String getUserFromID(String userID);
	
}
