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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
			String authToken = chessService.authenticateCustomer(user.getUsername(), user.getPassword());

			return new ResponseEntity<String>(authToken, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
	}

	@GetMapping(value = "games/{authToken}")
	public ResponseEntity<List<BoardState>> getGames(@PathVariable("authToken") String authToken) throws Exception {
		try{
			
			List<BoardState> boardStubList = chessService.getGames(authToken);
			
			return new ResponseEntity<List<BoardState>>(boardStubList, HttpStatus.OK);			
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value = "getgame/{authToken}/{gameId}")
	public ResponseEntity<BoardState> getGame(
			@PathVariable("authToken") String authToken, @PathVariable("gameId") String gameId) throws Exception {
		try{
			
			BoardState game = chessService.getGame(authToken,Integer.parseInt(gameId));
			
			return new ResponseEntity<BoardState>(game, HttpStatus.OK);			
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "submit/{authToken}")
	public ResponseEntity<Boolean> submitMove(
			@PathVariable("authToken") String authToken, 
			@RequestBody BoardState boardState) throws Exception {

		try {
			Boolean result = chessService.makeMove(authToken, boardState);
			
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PutMapping(value= "startgame/{authToken}/{targetUserName}")
	public ResponseEntity<Boolean> startGame(
			@PathVariable("authToken") String authToken, 
			@PathVariable("targetUserName") String targetUserName){
		
		try {
			Boolean result = chessService.startGame(authToken, targetUserName);
			
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
}
