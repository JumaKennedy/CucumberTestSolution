package com.test.framework;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextEncryptor {
	
	 private static Logger logger = LoggerFactory.getLogger(TextEncryptor.class);
    
		/**
		 * <h1>decodedString</h1>
		 * This decodedString method decodes the base64 encoded strings and returns it
		 */
		public static String decodedString(String encodedString) {
			byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedString);
			String decodedString = new String(decodedBytes);
			return decodedString;
		}

		/**
		 * <h1>encodedString</h1>
		 * This encodedString method encodes the string in base64 encoding and returns it
		 */
		public static String encodedString(String actualString) {
			String encodedString = Base64.getEncoder().encodeToString(actualString.getBytes());
			return encodedString;
		}
    
    public static void main(String[] args) {
    	String password ="invalid@data.hacker.io";    	
    	logger.info("\nOriginal pswd: {} \nEncrypted pswd: {} \nDecryptd pswd: {} ", password, encodedString(password), decodedString(encodedString(password)));
    	
    }
    
    
}
