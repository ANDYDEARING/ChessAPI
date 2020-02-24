package com.infy.chessapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.infy.chessapi.model.BoardState;

@Entity
@Table(name="PIECE")
@GenericGenerator(name="idGen",strategy="increment")
public class PieceEntity {
	
	@Id
	@Column(name="PIECE_ID")
	@GeneratedValue(generator="idGen")
	private Integer pieceID;
	
	@Column(name="COLOR")
	private String color;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="X_COORD")
	private Integer xCoord;
	
	@Column(name="Y_COORD")
	private Integer yCoord;

	public Integer getPieceID() {
		return pieceID;
	}

	public void setPieceID(Integer pieceID) {
		this.pieceID = pieceID;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getxCoord() {
		return xCoord;
	}

	public void setxCoord(Integer xCoord) {
		this.xCoord = xCoord;
	}

	public Integer getyCoord() {
		return yCoord;
	}

	public void setyCoord(Integer yCoord) {
		this.yCoord = yCoord;
	}
	
	
}
