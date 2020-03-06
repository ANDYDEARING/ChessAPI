package com.infy.chessapi.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="SESSIONS")
public class SessionEntity {

	@Id
	@Column(name="SESSION_ID")
	private String sessionId;
	
	@OneToOne(mappedBy = "session")
	private UserEntity user;
	
	@Column(name="EXPIRATION")
	private LocalDateTime expiration;

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

	public LocalDateTime getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDateTime localDateTime) {
		this.expiration = localDateTime;
	}
	
}
