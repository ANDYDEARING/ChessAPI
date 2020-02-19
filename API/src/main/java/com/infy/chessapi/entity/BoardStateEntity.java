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
}
