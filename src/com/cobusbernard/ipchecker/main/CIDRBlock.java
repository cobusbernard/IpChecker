package com.cobusbernard.ipchecker.main;

import com.cobusbernard.ipchecker.exception.InvalidCidrFormatException;


/**
 * Represents a CIDR block of ip address.
 * 
 * @author Cobus Bernard
 */
public class CIDRBlock {

	/**
	 * Base IP of the range represented by i.e. 192.0.2.0.
	 */
	private IpAddress baseIP;
	
	/**
	 * The leading bits value found after the '/' in the CIDR block.
	 */
	private byte leadingBits;
	
	/**
	 * The value of the starting IP of the block.
	 */
	private IpAddress bottomIp;
	
	/**
	 * The value of the last IP of the block.
	 */
	private IpAddress upperIp;
	
	/**
	 * May not construct object without parameters.  
	 */
	@SuppressWarnings("unused")
	private CIDRBlock() {
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param cidrBlock	the values for the block.
	 */
	public CIDRBlock(String cidrBlock) throws InvalidCidrFormatException {
		super();
		parseBlock(cidrBlock);
	}

	
	/**
	 * Parses the input string into {@link CIDRBlock} object.
	 * 
	 * @param cidrBlock	string representation of the CIDR block.
	 * 
	 * @throws InvalidCidrFormatException
	 */
	private void parseBlock(String cidrBlock) throws InvalidCidrFormatException {
		try {
			
			String[] splitInput = cidrBlock.split("\\/");
			this.baseIP = new IpAddress(splitInput[0]);
			
			this.leadingBits = Byte.parseByte(splitInput[1]);
			if (this.leadingBits < 0 || this.leadingBits > 32) {
				throw new InvalidCidrFormatException("Leading bits must be between 0 and 32.");
			}
			
		} catch (Exception e) {
			throw new InvalidCidrFormatException("Incorrectly formed CIDR. [ " + e.getMessage() + " ]");
		}
	}
	
	/**
	 * @return the baseIP
	 */
	public IpAddress getBaseIP() {
		return this.baseIP;
	}

	/**
	 * @return the leadingBits
	 */
	public byte getLeadingBits() {
		return this.leadingBits;
	}

	/**
	 * @return the bottomIp
	 */
	public IpAddress getBottomIp() {
		return this.bottomIp;
	}

	/**
	 * @return the upperIp
	 */
	public IpAddress getUpperIp() {
		return this.upperIp;
	}
	
}
