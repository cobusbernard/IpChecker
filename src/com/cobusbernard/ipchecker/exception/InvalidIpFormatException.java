package com.cobusbernard.ipchecker.exception;

/**
 * Represents an exception for when the input is not a valid IP address format.
 *
 * @author Cobus Bernard
 */
@SuppressWarnings("serial")
public class InvalidIpFormatException extends Exception {

	/**
	 * The error message of the exception.
	 */
	private String error;
	
	
	/**
	 * Default constructor
	 */
	public InvalidIpFormatException() {
		super();
		this.error = "Unknown";
	}
	
	/**
	 * Constructor for specific error message.
	 * 
	 * @param error	the error message to set.
	 */
	public InvalidIpFormatException(String error) {
		this.error = error;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	/**
	 * Returns the error message as for a normal exception.
	 * 
	 * @return the error message.
	 */
	public String getMessage() {
		return this.error;
	}
	
}
