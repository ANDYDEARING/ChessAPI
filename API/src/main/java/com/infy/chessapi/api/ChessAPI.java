package com.infy.chessapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.chessapi.service.ChessService;


@CrossOrigin
@RestController
@RequestMapping("ChessAPI")
public class ChessAPI {
	
	@Autowired
	private ChessService customerService;
	
	@Autowired
	private Environment environment;
}
