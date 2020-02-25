package com.infy.chessapi.dao;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.chessapi.entity.BoardStateEntity;
import com.infy.chessapi.entity.PieceEntity;
import com.infy.chessapi.entity.UserEntity;
import com.infy.chessapi.model.BoardState;
import com.infy.chessapi.utility.SecureHashUtility;

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
			PieceEntity[] pieceEntityList = boardEntity.getPiecesList();
			String[][] pieceStringList = new String[32][4];
			for(int i=0;i<pieceEntityList.length;i++){
				String[] piece = new String[4];
				piece[0]=pieceEntityList[i].getColor();
				piece[1]=pieceEntityList[i].getName();
				piece[2]=Integer.toString(pieceEntityList[i].getxCoord());
				piece[3]=Integer.toString(pieceEntityList[i].getyCoord());
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
	public String getUserFromToken(String authToken) {
		String username = authToken.split("-")[0];
		UserEntity userEntity = entityManager.find(UserEntity.class, username);
		if(userEntity != null){
			return userEntity.getUsername();
		} else {
			return null;
		}
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
				try{
					entityManager.persist(boardEntity);					
				} catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
		}
		return true;
	}
	
}
