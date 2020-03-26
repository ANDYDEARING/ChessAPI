package com.infy.chessapi.dao;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.chessapi.entity.BoardStateEntity;
import com.infy.chessapi.entity.PieceEntity;
import com.infy.chessapi.entity.SessionEntity;
import com.infy.chessapi.entity.UserEntity;
import com.infy.chessapi.model.BoardState;
import com.infy.chessapi.utility.SecureHashUtility;

@Repository(value = "ChessDAO")
public class ChessDAOImpl implements ChessDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public BoardState getBoardState(Integer gameId) {
		BoardStateEntity boardEntity = entityManager.find(BoardStateEntity.class, gameId);
		BoardState result = null;
		if(boardEntity != null){
			result = new BoardState();
			result.setBlackUser(boardEntity.getBlackUser().getUsername());
			result.setWhiteUser(boardEntity.getWhiteUser().getUsername());
			result.setGameID(boardEntity.getGameID());
			result.setIsWhiteTurn(boardEntity.getIsWhiteTurn());
			result.setLastMove(boardEntity.getLastMove());
			PieceEntity[] pieceEntityList = boardEntity.getPiecesList();
			String[][] pieceStringList = new String[32][4];
			for(int i=0;i<pieceEntityList.length;i++){
				String[] piece = new String[4];
				piece[0]=pieceEntityList[i].getColor();
				piece[1]=pieceEntityList[i].getName();
				if(pieceEntityList[i].getxCoord()!=null) {
					piece[2]=Integer.toString(pieceEntityList[i].getxCoord());
					piece[3]=Integer.toString(pieceEntityList[i].getyCoord());					
				} else {
					piece[2]=null;
					piece[3]=null;
				}
				pieceStringList[i]=piece;
			}
			result.setPiecesList(pieceStringList);
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
	public List<BoardState> getGames(String username) {
		List<BoardState> resultStubList = new ArrayList<BoardState>();
		Query query = entityManager.createQuery(
				"select b from BoardStateEntity b where b.blackUser.username=:username or b.whiteUser.username=:username");
		query.setParameter("username", username);
		List<BoardStateEntity>resultEntityList = query.getResultList();
		for(int i=0;i<resultEntityList.size();i++){
			BoardState boardStub = new BoardState();
			boardStub.setBlackUser(resultEntityList.get(i).getBlackUser().getUsername());
			boardStub.setWhiteUser(resultEntityList.get(i).getWhiteUser().getUsername());
			boardStub.setGameID(resultEntityList.get(i).getGameID());
			boardStub.setIsWhiteTurn(resultEntityList.get(i).getIsWhiteTurn());
			boardStub.setLastMove(resultEntityList.get(i).getLastMove());
			resultStubList.add(boardStub);
		}
		return resultStubList;
	}

	@Override
	public String getUserFromSessionId(String sessionId) {
		SessionEntity sessionEntity = entityManager.find(SessionEntity.class, sessionId);
		if(sessionEntity != null){
			if(sessionEntity.getExpiration().isAfter(LocalDateTime.now())){
				return sessionEntity.getUser().getUsername();				
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Boolean updateBoardState(BoardState board) {
		BoardStateEntity boardEntity = entityManager.find(BoardStateEntity.class, board.getGameID());
		if(boardEntity != null){
			
			boardEntity.setIsWhiteTurn(!board.getIsWhiteTurn());
			boardEntity.setLastMove(LocalDateTime.now());
			
			PieceEntity[] pieceEntityList = boardEntity.getPiecesList();
			String[][] pieceStringList = board.getPiecesList();
			for(int i=0;i<pieceEntityList.length;i++){
				PieceEntity piece = entityManager.find(PieceEntity.class, pieceEntityList[i].getPieceID());
				String xCoordString = pieceStringList[i][2];
				if(xCoordString!=null){
					piece.setxCoord(Integer.parseInt(xCoordString));
					piece.setyCoord(Integer.parseInt(pieceStringList[i][3]));
					entityManager.persist(piece);
				} else {
					piece.setxCoord(null);
					piece.setyCoord(null);
					entityManager.persist(piece);
				}
			}
			if(board.getWinner()==null) {
				boardEntity.setWinner(null);
			} else {
				boardEntity.setWinner(entityManager.find(UserEntity.class, board.getWinner()));			
			}
			entityManager.persist(boardEntity);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean createGame(BoardState boardState) {
		BoardStateEntity boardEntity = new BoardStateEntity();
		boardEntity.setBlackUser(entityManager.find(UserEntity.class, boardState.getBlackUser()));
		boardEntity.setWhiteUser(entityManager.find(UserEntity.class, boardState.getWhiteUser()));
		boardEntity.setIsWhiteTurn(boardState.getIsWhiteTurn());
		boardEntity.setLastMove(boardState.getLastMove());
		if(boardState.getWinner()==null) {
			boardEntity.setWinner(null);
		} else {
			boardEntity.setWinner(entityManager.find(UserEntity.class, boardState.getWinner()));			
		}
		PieceEntity[] pieceEntityList = new PieceEntity[32];
		String[][] piecesStringList = boardState.getPiecesList();
		for(int j=0;j<piecesStringList.length;j++){
			PieceEntity pieceEntity = new PieceEntity();
			pieceEntity.setColor(piecesStringList[j][0]);
			pieceEntity.setName(piecesStringList[j][1]);
			pieceEntity.setxCoord(Integer.parseInt(piecesStringList[j][2]));
			pieceEntity.setyCoord(Integer.parseInt(piecesStringList[j][3]));
			pieceEntityList[j] = pieceEntity;
		}
		boardEntity.setPiecesList(pieceEntityList);			
		entityManager.persist(boardEntity);					
		return true;
	}

	@Override
	public Boolean verifyUserExists(String username) {
		UserEntity userEntity = entityManager.find(UserEntity.class, username);
		return userEntity!=null;
	}
	
	@Override
	public Boolean populateTestData(List<String> usernames, List<String> passwords, List<BoardState> boardStates){
		if(usernames.size() != passwords.size()){
			return false;
		} else {
			for(int i=0;i<usernames.size();i++){
				UserEntity user = new UserEntity();
				user.setUsername(usernames.get(i));
				try {
					user.setPassword(SecureHashUtility.getHashValue(passwords.get(i)));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				entityManager.persist(user);
			}
			for(int i=0;i<boardStates.size();i++){
				BoardState boardState = boardStates.get(i);
				BoardStateEntity boardEntity = new BoardStateEntity();
				boardEntity.setBlackUser(entityManager.find(UserEntity.class, boardState.getBlackUser()));
				boardEntity.setWhiteUser(entityManager.find(UserEntity.class, boardState.getWhiteUser()));
				boardEntity.setIsWhiteTurn(boardState.getIsWhiteTurn());
				boardEntity.setLastMove(boardState.getLastMove());
				if(boardState.getWinner()==null) {
					boardEntity.setWinner(null);
				} else {
					boardEntity.setWinner(entityManager.find(UserEntity.class, boardState.getWinner()));			
				}
				PieceEntity[] pieceEntityList = new PieceEntity[32];
				String[][] piecesStringList = boardState.getPiecesList();
				for(int j=0;j<piecesStringList.length;j++){
					PieceEntity pieceEntity = new PieceEntity();
					pieceEntity.setColor(piecesStringList[j][0]);
					pieceEntity.setName(piecesStringList[j][1]);
					pieceEntity.setxCoord(Integer.parseInt(piecesStringList[j][2]));
					pieceEntity.setyCoord(Integer.parseInt(piecesStringList[j][3]));
					pieceEntityList[j] = pieceEntity;
				}
				boardEntity.setPiecesList(pieceEntityList);
				entityManager.persist(boardEntity);					
			}
		}
		return true;
	}

	@Override
	public String refreshSessionId(String username) {
		try {
			String sessionId = SecureHashUtility.getHashValue(username);
			SessionEntity sessionEntity = entityManager.find(SessionEntity.class, sessionId);
			if(sessionEntity==null){
				sessionEntity = new SessionEntity();
				UserEntity userEntity = entityManager.find(UserEntity.class, username);
				if(userEntity!=null){
					sessionEntity.setUser(userEntity);
					sessionEntity.setSessionId(sessionId);
					userEntity.setSession(sessionEntity);
					entityManager.persist(userEntity);
				} else {
					return null;
				}
			}
			sessionEntity.setExpiration(LocalDateTime.now().plusMinutes(10));
			
			entityManager.persist(sessionEntity);
			return sessionId;
		} catch (NoSuchAlgorithmException e){
			return null;
		}
	}
	
}
