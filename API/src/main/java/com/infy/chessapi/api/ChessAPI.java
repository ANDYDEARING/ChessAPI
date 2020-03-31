package com.infy.chessapi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.chessapi.model.User;
import com.infy.chessapi.model.BoardState;
import com.infy.chessapi.service.ChessService;


@CrossOrigin
@RestController
@RequestMapping("ChessAPI")
public class ChessAPI {
	
	@Autowired
	private ChessService chessService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping(value = "populateTestData")
	public ResponseEntity<Boolean> populateTestData() throws Exception {
		try{
			Boolean result = chessService.populateTestData();
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);			
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "login")
	public ResponseEntity<String> authenticateUser(@RequestBody User user) throws Exception {

		try {
			String sessionId = chessService.authenticateUser(user.getUsername(), user.getPassword());

			return new ResponseEntity<String>(sessionId, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
	}

	@GetMapping(value = "games")
	public ResponseEntity<List<BoardState>> getGames(@RequestHeader("session-id") String sessionId) throws Exception {
		try{
			
			List<BoardState> boardStubList = chessService.getGames(sessionId);
			
			return new ResponseEntity<List<BoardState>>(boardStubList, HttpStatus.OK);			
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value = "getgame/{gameId}")
	public ResponseEntity<BoardState> getGame(
			@RequestHeader("session-id") String sessionId, @PathVariable("gameId") String gameId) throws Exception {
		try{
			
			BoardState game = chessService.getGame(sessionId,Integer.parseInt(gameId));
			
			return new ResponseEntity<BoardState>(game, HttpStatus.OK);			
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "submit")
	public ResponseEntity<Boolean> submitMove(
			@RequestHeader("session-id") String sessionId, 
			@RequestBody BoardState boardState) throws Exception {

		try {
			Boolean result = chessService.makeMove(sessionId, boardState);
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println("**************in catch block****************");
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "startgame/{targetUserName}")
	public ResponseEntity<Boolean> startGame(
			@RequestHeader("session-id") String sessionId,
			@PathVariable("targetUserName") String targetUserName){
		
		try {
			Boolean result = chessService.startGame(sessionId, targetUserName);
			
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "register")
	public ResponseEntity<String> registerUser(@RequestBody User user) throws Exception {

		try {
			String sessionId = chessService.registerUser(user.getUsername(), user.getPassword());

			return new ResponseEntity<String>(sessionId, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
}
