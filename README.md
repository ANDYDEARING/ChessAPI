CHESS API

This API is, at of the time of this writing, is deployed at https://infy-chess-api.herokuapp.com/ChessAPI/ and was written as a companion to the Chess App at https://infy-chess.surge.sh. It is written in Spring Boot and Java and uses a MySQL database for persistence.

The API endpoints are:

/populateTestData (GET)
This will populate the database with two users, "user1" and "user2" with the passwords "password1" and "password2", and a single game where user1 is white and user2 is black with no moves made. This endpoint is not accessible from the front end application and is meant to be adapted for testing purposes during development. No API key or headers are required for this endpoint. If the data is populated sucessfully, this returns a "true" boolean.

/login (POST)
This accepts a JSON object containing the keys "username" and "password" and returns a plain text session-id to be used in the headers of subsequent requests. The session-id expires after 10 minutes, but is refreshed when the user interacts with the API. The password accepted in the request is unencrypted, but is encrypted and then compared with the encrypted password stored in the database. If the username and password do not match, a 401 unauthorized is returned.
