package com.infy.chessapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.chessapi.dao.ChessDAO;
import com.infy.chessapi.model.BoardState;
import com.infy.chessapi.model.User;


@Service(value = "chessService")
@Transactional
public class ChessServiceImpl implements ChessService{
	
	@Autowired
	private ChessDAO chessDAO;

	@Override
	public Boolean populateTestData() {
		
		List<String> usernames = new ArrayList<String>();
		List<String> passwords = new ArrayList<String>();
		List<BoardState> boardStates = new ArrayList<BoardState>();
		
		usernames.add("user1");
		usernames.add("user2");
		
		passwords.add("password1");
		passwords.add("password2");
		
		BoardState boardState1 = new BoardState();
		
		boardState1.setWhiteUser("user1");
		boardState1.setBlackUser("user2");
		boardState1.setLastMove(LocalDate.now());
		boardState1.setIsWhiteTurn(true);
		
		String[][] piecesList = new String[32][4];
		for(int i=0;i<8;i++){
			String[] piece = new String[4];
			piece[0]="WHITE";
			piece[1]="PAWN";
			piece[2]=Integer.toString(i);
			piece[3]="6";
			piecesList[i]=piece;
		}
		for(int i=8;i<16;i++){
			String[] piece = new String[4];
			piece[0]="BLACK"; 
			piece[1]="PAWN";
			piece[2]=Integer.toString(i);
			piece[3]="1";
			piecesList[i]=piece;
		}
		
		String[] piece = new String[4];
		piece[0]="WHITE"; 
		piece[1]="ROOK"; 
		piece[2]="0"; 
		piece[3]="7";
		piecesList[16] = piece;
		
		piece = new String[4];
		piece[0]="WHITE"; 
		piece[1]="ROOK"; 
		piece[2]="7"; 
		piece[3]="7";
		piecesList[17] = piece;
		
		piece = new String[4];
		piece[0]="BLACK"; 
		piece[1]="ROOK"; 
		piece[2]="0"; 
		piece[3]="0";
		piecesList[18] = piece;
		
		piece = new String[4];
		piece[0]="BLACK"; 
		piece[1]="ROOK"; 
		piece[2]="7"; 
		piece[3]="0";
		piecesList[19] = piece;
		
		piece = new String[4];
		piece[0]="WHITE"; 
		piece[1]="KNIGHT"; 
		piece[2]="1"; 
		piece[3]="0";
		piecesList[20] = piece;
		
		piece = new String[4];
		piece[0]="WHITE"; 
		piece[1]="KNIGHT"; 
		piece[2]="6"; 
		piece[3]="0";
		piecesList[21] = piece;
		
		piece = new String[4];
		piece[0]="BLACK"; 
		piece[1]="KNIGHT"; 
		piece[2]="1"; 
		piece[3]="7";
		piecesList[22] = piece;
		
		piece = new String[4];
		piece[0]="BLACK"; 
		piece[1]="KNIGHT"; 
		piece[2]="6"; 
		piece[3]="7";
		piecesList[23] = piece;
		
		piece = new String[4];
		piece[0]="WHITE"; 
		piece[1]="BISHOP"; 
		piece[2]="2"; 
		piece[3]="0";
		piecesList[24] = piece;
		
		piece = new String[4];
		piece[0]="WHITE"; 
		piece[1]="BISHOP"; 
		piece[2]="5"; 
		piece[3]="0";
		piecesList[25] = piece;
		
		piece = new String[4];
		piece[0]="BLACK"; 
		piece[1]="BISHOP"; 
		piece[2]="2"; 
		piece[3]="7";
		piecesList[26] = piece;
		
		piece = new String[4];
		piece[0]="BLACK"; 
		piece[1]="BISHOP"; 
		piece[2]="5"; 
		piece[3]="7";
		piecesList[27] = piece;
		
		piece = new String[4];
		piece[0]="WHITE"; 
		piece[1]="QUEEN"; 
		piece[2]="3"; 
		piece[3]="7";
		piecesList[28] = piece;
		
		piece = new String[4];
		piece[0]="BLACK"; 
		piece[1]="QUEEN"; 
		piece[2]="3"; 
		piece[3]="0";
		piecesList[29] = piece;
		
		piece = new String[4];
		piece[0]="WHITE"; 
		piece[1]="KING"; 
		piece[2]="4"; 
		piece[3]="7";
		piecesList[30] = piece;
		
		piece = new String[4];
		piece[0]="BLACK"; 
		piece[1]="KING"; 
		piece[2]="4"; 
		piece[3]="0";
		piecesList[31] = piece;
		
		boardState1.setPiecesList(piecesList);
		
		boardStates.add(boardState1);
		
		chessDAO.populateTestData(usernames, passwords, boardStates);
		
		return true;
	}

	@Override
	public User authenticateCustomer(String username, String password) throws Exception {
		User user = null;
		String passwordFromDB = chessDAO.getPassword(username);
		if(passwordFromDB!=null){
			if(password.equals(passwordFromDB)){
				user = new User();
				user.setUsername(username);
				user.setPassword(password);
			}
			else{
				throw new Exception ("ChessService.INVALID_CREDENTIALS");				
			}
		} else {
			throw new Exception ("ChessService.INVALID_CREDENTIALS");
		}
		return user;
	}

}
