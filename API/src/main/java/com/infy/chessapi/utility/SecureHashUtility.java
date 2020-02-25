package com.infy.chessapi.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecureHashUtility {
	
	public static String getHashValue(String stringToHash) throws NoSuchAlgorithmException {
		String result = null;
		try {
			// Create MessageDigest instance for SHA-256
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        
	        //Add password bytes to digest
	        md.update(stringToHash.getBytes());
	        
	        //Get the hash's bytes 
	        byte[] bytes = md.digest();
	        
	        //This bytes[] has bytes in decimal format;
	        //Convert it to hexadecimal format
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++)
	        {
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        //Get complete hashed password in hex format
	        result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
	        throw new NoSuchAlgorithmException();
	    }
		return result;
	}
}
    