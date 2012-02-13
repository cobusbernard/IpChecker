package com.cobusbernard.ipchecker.ipaddress;

import com.cobusbernard.ipchecker.exception.InvalidIpFormatException;


/**
 * Represents an IP address used for the base of a CIDR block. 
 * Will be implementing comparable to allow IP address compares.
 * 
 * @author Cobus Bernard
 */
public class BaseIpAddress extends AbstractIpAddress {
	
	/**
	 * Default constructor.
	 * 
	 * @param ipAddress	the IP address to to construct the object with.
	 * 
	 * @throws InvalidIpFormatException when the string could not be parsed.
	 */
	public BaseIpAddress(String ipAddress) throws InvalidIpFormatException {
		super(ipAddress);
	}
	
	/**
	 * Will validate that the input is a short and that it falls within 0 - 254;
	 * 
	 * @param input value to evaluate.
	 * 
	 * @return the short value of the input.
	 * 
	 * @throws when the input does not validate to a number between 0 and 254.
	 */
	protected short validateInput(String input) throws InvalidIpFormatException {
		return validateInputRange(input, 0, 254);
	}

}
