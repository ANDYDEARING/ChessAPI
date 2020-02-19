package com.infy.chessapi.dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.chessapi.model.BoardState;

@Repository(value = "ChessDAO")
public class ChessDAOImpl implements ChessDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public BoardState getBoardState(String gameId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardState[] getGames(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserFromToken(String authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateBoardState(BoardState board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean createGame(BoardState board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardState[] getBoardStatesAfter(LocalDate timestamp, String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserFromID(String userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
