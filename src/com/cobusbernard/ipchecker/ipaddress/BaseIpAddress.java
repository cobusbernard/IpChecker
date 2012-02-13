package com.cobusbernard.ipchecker.ipaddress;

import com.cobusbernard.ipchecker.exception.InvalidIpFormatException;


/**
 * Represents an IP address used for the base of a CIDR block. 
 * Will be implementing comparable to allow IP address compares.
 * 
 * @author Cobus Bernard
 */
public class BaseIpAddress extends IpAddress {
	
	
	/**
	 * Default constructor.
	 * 
	 * @param ipAddress	the IP address to to construct the object with.
	 * 
	 * @throws InvalidIpFormatException when the string could not be parsed.
	 */
	public BaseIpAddress(String ipAddress) throws InvalidIpFormatException {
		parseInput(ipAddress);
	}
	
	/**
	 * Will attempt to parse the string into and {@link AbstractIpAddress}.
	 * 
	 * @param ip	the ip string to parse.
	 * 
	 * @throws InvalidIpFormatException when the string could not be parsed.
	 */
	private void parseInput(String ip) throws InvalidIpFormatException {
		try {
			String[] split = ip.split("\\.");

			this.address_A = validateInputRange(split[0], 0, 255);
			this.address_B = validateInputRange(split[1], 0, 255);
			this.address_C = validateInputRange(split[2], 0, 255);
			this.address_D = validateInputRange(split[3], 0, 254);
		} catch (Exception e) {
			throw new InvalidIpFormatException("Could not parse input string. [ " + ip + " ]");
		}
	}
}
