package com.infy.chessapi.model;

import java.time.LocalDate;

public class BoardState {
	
	private String whiteUser;
	private String blackUser;
	private Integer gameID;
	private LocalDate lastMove;
	private Boolean isWhiteTurn;
	private String[][] piecesList;
	
	
	public String getWhiteUser() {
		return whiteUser;
	}
	public void setWhiteUser(String whiteUser) {
		this.whiteUser = whiteUser;
	}
	public String getBlackUser() {
		return blackUser;
	}
	public void setBlackUser(String blackUser) {
		this.blackUser = blackUser;
	}
	public Integer getGameID() {
		return gameID;
	}
	public void setGameID(Integer gameID) {
		this.gameID = gameID;
	}
	public LocalDate getLastMove() {
		return lastMove;
	}
	public void setLastMove(LocalDate lastMove) {
		this.lastMove = lastMove;
	}
	public Boolean getIsWhiteTurn() {
		return isWhiteTurn;
	}
	public void setIsWhiteTurn(Boolean isWhiteTurn) {
		this.isWhiteTurn = isWhiteTurn;
	}
	public String[][] getPiecesList() {
		return piecesList;
	}
	public void setPiecesList(String[][] piecesList) {
		this.piecesList = piecesList;
	}
	
	
}
