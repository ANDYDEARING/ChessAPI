package com.infy.chessapi.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.chessapi.entity.BoardStateEntity;
import com.infy.chessapi.entity.UserEntity;
import com.infy.chessapi.model.BoardState;

@Repository(value = "ChessDAO")
public class ChessDAOImpl implements ChessDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public BoardState getBoardState(String gameId) {
		BoardStateEntity boardEntity = entityManager.find(BoardStateEntity.class, gameId);
		BoardState result = null;
		if(boardEntity != null){
			result = new BoardState();
			result.setBlackUser(boardEntity.getBlackUser().getUsername());
			result.setWhiteUser(boardEntity.getWhiteUser().getUsername());
			result.setGameID(boardEntity.getGameID());
			result.setIsWhiteTurn(boardEntity.getIsWhiteTurn());
			result.setLastMove(boardEntity.getLastMove());
			result.setPiecesList(boardEntity.getPiecesList());
		}
		return result;
	}

	@Override
	public String getPassword(String username) {
		UserEntity userEntity = entityManager.find(UserEntity.class, username);
		String result = null;
		if(userEntity != null){
			result = userEntity.getPassword();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BoardState> getGames(String user) {
		List<BoardState> resultList = null;
		Query query = entityManager.createQuery(
				"select b from board_state b where b.black_user=:user or b.white_user=:user");
		query.setParameter("user", user);
		resultList = query.getResultList();
		return resultList;
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
	public List<BoardState> getBoardStatesAfter(LocalDate timestamp, String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserFromID(String userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Boolean populateTestData(List<String> usernames, List<String> passwords, List<BoardState> boardStates){
		return true;
	}
	
}
