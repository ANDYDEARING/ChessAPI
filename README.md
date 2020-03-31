# ChessAPI

This is a web API for database interaction to handle persistence for a browser-based ChessApp. This API is, at of the time of this writing, is deployed at https://infy-chess-api.herokuapp.com/ChessAPI/ and was written as a companion to the Chess App at https://infy-chess.surge.sh. It is written in Spring Boot and Java and uses a MySQL database for persistence.

The API endpoints are:

## /populateTestData (GET)
This endpoint is for creating and manipulating test data. This will populate the database with two users, "user1" and "user2" with the passwords "password1" and "password2", and a single game where user1 is white and user2 is black with no moves made. This endpoint is not accessible from the front end application and is meant to be adapted for testing purposes during development. No API key or headers are required for this endpoint. If the data is populated successfully, this returns a "true" Boolean.

## /login (POST)
This endpoint is for logging in and generating a "session-id". This accepts a JSON object containing the keys "username" and "password" and returns a plain text session-id to be used in the headers of subsequent requests. The session-id expires after 10 minutes, but is refreshed when the user interacts with the API. The password accepted in the request is unencrypted, but is encrypted and then compared with the encrypted password stored in the database. If the username and password do not match, a 401 unauthorized is returned.

## /games (GET)
This endpoint is for retrieving a list of a given user's games. This accepts a "session-id" key/value in the request header where the value is from a successful /login response. If the session-id is expired, this will return http 400 bad request. The session-id is used to look up the user and then a list of BoardState objects are returned in the response. These BoardState objects have a null value for the piecesList because this data is for the "home" screen of the front end and meant to list the games for a given user. This will return all games for the session-id of the user, both active and completed. If BoardState.winner is null, the game is in process, otherwise the value will be the username of the winner.

## /getgame/{gameId} (GET)
This endpoint is for retrieving a specific game. This also accepts a "session-id" key/value in the request header where the value is from a successful /login response. If the session-id is expired, this will return http 400 bad request. Otherwise, the session-id is used to look up the user and a fully populated BoardState Object corresponding to the gameId in the path variable is returned in the response.

## /submit (POST)
This endpoint is for submitting a move. This also accepts a "session-id" for authentication and a BoardState object in the body of the request. This will return true if the move is allowed and false if not. Allowed moves are BoardState objects that have exactly 1 or 2 piece position differences from the previous state or that are identical to the previous state but have a change from null to a user in the boardState.winner. This endpoint does not contain chess logic, so that will need to be handled by the front end. 

## /startgame/{targetUserName} (POST)
This endpoint is for the logged-in user to start a game with another user. This also accepts a "session-id" header for authentication and a targetUserName, the username of the user to be challenged, in the path variable. The request body is empty. It returns true if successful after a case-sensitive comparison of the targetUserName with usernames in the database. It returns false if the user doesn't exist in the database. In the new game, the challenger will be white and have the first move.

## /register (POST)
This endpoint is for registering a new user. This accepts a JSON object containing the keys "username" and "password" and returns a plain text session-id to be used in the headers of subsequent requests. The session-id expires after 10 minutes, but is refreshed when the user interacts with the API. The password accepted in the request is unencrypted, but is encrypted before being stored in the database. If the username already exists (case-insensitive comparison), a 400 bad request is returned.
