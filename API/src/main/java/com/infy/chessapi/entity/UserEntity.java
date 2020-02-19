package com.infy.chessapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class UserEntity {
	@Id
	@Column(name="USERNAME")
	private String username;
	@Column(name="PASSWORD")
	private String password;
}
