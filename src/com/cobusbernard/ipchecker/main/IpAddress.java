package com.cobusbernard.ipchecker.main;

import com.cobusbernard.ipchecker.exception.InvalidIpFormatException;


/**
 * Represents an IP address. Will be implementing comparable to allow IP address compares.
 * 
 * @author Cobus Bernard
 */
public class IpAddress {

	/*
	 * Will use short as Java does not have unsigned bytes. Could implement byte and handle the ip values
	 * but too much effort for too little gain. 
	 */
	private short address_A;
	private short address_B;
	private short address_C;
	private short address_D;
	
	/**
	 * May not instantiate class with specifying an IP address.
	 */
	@SuppressWarnings("unused")
	private IpAddress() {
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param ipAddress	the IP address to to construct the object with.
	 * 
	 * @throws InvalidIpFormatException when the string could not be parsed.
	 */
	public IpAddress(String ipAddress) throws InvalidIpFormatException {
		super();
		parseInput(ipAddress);
	}

	/**
	 * Will attempt to parse the string into and {@link IpAddress}.
	 * 
	 * @param ip	the ip string to parse.
	 * 
	 * @throws InvalidIpFormatException when the string could not be parsed.
	 */
	private void parseInput(String ip) throws InvalidIpFormatException {
		try {
			String[] split = ip.split("\\.");

			//Substracting 127 so that it fits inside the byte datatype.
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
	private short validateInput(String input) throws InvalidIpFormatException {
		try {
			short value = Short.parseShort(input);
			
			if (value < 0 || value > 255) {
				throw new InvalidIpFormatException("Numberic must be between 0 and 255. [ " + input + " ]");
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
