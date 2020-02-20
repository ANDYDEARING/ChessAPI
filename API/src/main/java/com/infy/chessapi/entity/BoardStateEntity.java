package com.infy.chessapi.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="BOARD_STATE")
@GenericGenerator(name="idGen",strategy="increment")
public class BoardStateEntity {
	@Id
	@Column(name="GAME_ID")
	@GeneratedValue(generator="idGen")
	private Integer gameID;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="WHITE_USER")
	private UserEntity whiteUser;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="BLACK_USER")
	private UserEntity blackUser;
	
	@Column(name="LAST_MOVE")
	private LocalDate lastMove;
	
	@Column(name="IS_WHITE_TURN")
	private Boolean isWhiteTurn;
	
	@Column(name="PIECES_LIST")
	private String[][] piecesList;

	public Integer getGameID() {
		return gameID;
	}

	public void setGameID(Integer gameID) {
		this.gameID = gameID;
	}

	public UserEntity getWhiteUser() {
		return whiteUser;
	}

	public void setWhiteUser(UserEntity whiteUser) {
		this.whiteUser = whiteUser;
	}

	public UserEntity getBlackUser() {
		return blackUser;
	}

	public void setBlackUser(UserEntity blackUser) {
		this.blackUser = blackUser;
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
