package com.infy.chessapi.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="SESSION")
public class SessionEntity {

	@Id
	@Column(name="SESSION_ID")
	private String sessionId;
	
	@OneToOne(mappedBy = "session")
	private UserEntity user;
	
	@Column(name="EXPIRATION")
	private LocalDate expiration;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}
	
}
