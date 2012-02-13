package com.cobusbernard.ipchecker.ipaddress;

import com.cobusbernard.ipchecker.exception.InvalidIpFormatException;


/**
 * Common, abstracted values and methods for all IpAddresses.
 * 
 * @author Cobus Bernard
 */
public abstract class AbstractIpAddress {

	/*
	 * Will use short as Java does not have unsigned bytes. Could implement byte and handle the ip values
	 * but too much effort for too little gain. 
	 */
	protected short address_A;
	protected short address_B;
	protected short address_C;
	protected short address_D;
	
	
	/**
	 * May not instantiate class with specifying an IP address.
	 */
	protected AbstractIpAddress() {
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param ipAddress	the IP address to to construct the object with.
	 * 
	 * @throws InvalidIpFormatException when the string could not be parsed.
	 */
	public AbstractIpAddress(String ipAddress) throws InvalidIpFormatException {
		super();
		parseInput(ipAddress);
	}

	/**
	 * Will attempt to parse the string into and {@link AbstractIpAddress}.
	 * 
	 * @param ip	the ip string to parse.
	 * 
	 * @throws InvalidIpFormatException when the string could not be parsed.
	 */
	protected void parseInput(String ip) throws InvalidIpFormatException {
		try {
			String[] split = ip.split("\\.");

			this.address_A = validateInput(split[0]);
			this.address_B = validateInput(split[1]);
			this.address_C = validateInput(split[2]);
			this.address_D = validateInput(split[3]);
		} catch (Exception e) {
			throw new InvalidIpFormatException("Could not parse input string. [ " + ip + " ]");
		}
	}
	
	/**
	 * Will validate that the input is a short and that it falls within 0 - 255;
	 * 
	 * @param input value to evaluate.
	 * 
	 * @return the short value of the input.
	 * 
	 * @throws when the input does not validate to a number between 0 and 255.
	 */
	protected abstract short validateInput(String input) throws InvalidIpFormatException;
	
	/**
	 * Will validate that the input is a short and that it falls within 0 - 254;
	 * 
	 * @param input value to evaluate.
	 * @param lower the lower allowed value of the range (inclusive).
	 * @param upper the upper allowed value of the range (inclusive).
	 * 
	 * @return the short value of the input.
	 * 
	 * @throws when the input does not validate to a number between 0 and 254.
	 */
	protected short validateInputRange(String input, int lower, int upper) throws InvalidIpFormatException {
		try {
			short value = Short.parseShort(input);
			
			if (value < lower || value > upper) {
				throw new InvalidIpFormatException("Numberic must be between " + lower + " and " + 
					+ upper + ". [ " + input + " ]");
			}
			return value;
		} catch (NumberFormatException e) {
			throw new InvalidIpFormatException("Could not parse number. [ " + input + " ]");
		}
	}	
	
	/**
	 * @return the address_A
	 */
	public short getAddress_A() {
		return this.address_A;
	}

	/**
	 * @return the address_B
	 */
	public short getAddress_B() {
		return this.address_B;
	}

	/**
	 * @return the address_C
	 */
	public short getAddress_C() {
		return this.address_C;
	}

	/**
	 * @return the address_D
	 */
	public short getAddress_D() {
		return this.address_D;
	}

}
