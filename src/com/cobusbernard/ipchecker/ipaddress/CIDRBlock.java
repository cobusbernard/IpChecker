package com.cobusbernard.ipchecker.ipaddress;

import com.cobusbernard.ipchecker.exception.InvalidCidrFormatException;
import com.cobusbernard.ipchecker.exception.InvalidIpFormatException;


/**
 * Represents a CIDR block of ip address.
 * 
 * @author Cobus Bernard
 */
public class CIDRBlock {

	/**
	 * Base IP of the range represented by i.e. 192.0.2.0.
	 */
	private BaseIpAddress baseIP;
	
	/**
	 * The leading bits value found after the '/' in the CIDR block.
	 */
	private byte leadingBits;
	
	/**
	 * The value of the starting IP of the block.
	 */
	private IpAddress lowerIp;
	
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
			
			if (splitInput.length < 2 || splitInput.length > 2) {
				throw new InvalidCidrFormatException("Please enter in the format xxx.xxx.xxx.xxx/xx.");
			}
			
			this.leadingBits = Byte.parseByte(splitInput[1]);
			if (this.leadingBits < 0 || this.leadingBits > 32) {
				throw new InvalidCidrFormatException("Leading bits must be between 0 and 32.");
			}
			
			determineIpAddresses(splitInput[0], splitInput[1]);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new InvalidCidrFormatException("Incorrectly formed CIDR. [ " + e.getMessage() + " ]");
		}
	}
	
	/**
	 * Determines the base IP to start on one of the blocks, i.e. for /27, the blocks are 31 IPs long and can
	 * start on 0, 32, 64 - the first IP is used to allocate the other IPs, so block would effectively be
	 * 32 long. Assigns the base, upper and lower IPs.
	 * 
	 * @return the correct BaseIp.
	 */
	private void determineIpAddresses(String givenBaseIp, String leadingBits) 
			throws InvalidIpFormatException {

		int totalHosts = ((int) Math.pow(2, 32 - Integer.parseInt(leadingBits)) - 1);

		int netmask = ((255 << 24) 
				+ (255 << 16)
				+ (255 << 8)
				+ (255)) - totalHosts;
		
		int base = (new BaseIpAddress(givenBaseIp)).getBinaryValue() & netmask;
		this.baseIP = new BaseIpAddress(bitValue(base));
		this.lowerIp = new IpAddress(bitValue(base + 1));
		this.upperIp = new IpAddress(bitValue(base + totalHosts - 1));

		//TODO: Find a better / proper way to do this.
		//Special case for loopback - can only determine once range has been calculated.
		if (this.containsIP(new IpAddress("127.0.0.1"))) {
			int totalHosts2 = ((int) Math.pow(2, 32 - Integer.parseInt(leadingBits)));

			int netmask2 = ((255 << 24) 
					+ (255 << 16)
					+ (255 << 8)
					+ (255)) - totalHosts2;
			
			int base2 = (new BaseIpAddress(givenBaseIp)).getBinaryValue() & netmask2;
			this.baseIP = new BaseIpAddress(bitValue(base2));
			this.lowerIp = new IpAddress(bitValue(base2 + 1));
			this.upperIp = new IpAddress(bitValue(base2 + totalHosts2 - 1));
		}
		
	}
	
	/**
	 * Builds up the IP address string from the binary value.
	 * 
	 * @param value the binary value.
	 * 
	 * @return the IP address in the form xxx.xxx.xxx.xxx.
	 */
	private String bitValue(int value) {
		String bitValue = "";
		bitValue += (value >>> 24) + ".";
		bitValue += ((value << 8) >>> 24) + ".";
		bitValue += ((value << 16) >>> 24) + ".";
		bitValue += ((value << 24) >>> 24);
		
		return bitValue;
	}
	
	/**
	 * Determines whether an ip falls within the CIDR block's range.
	 * 
	 * @param ip the IP to test.
	 * 
	 * @return true if the IP falls within the range.
	 */
	public boolean containsIP(IpAddress ip) {
		return (ip.getBinaryValue() >= this.lowerIp.getBinaryValue() 
				&& ip.getBinaryValue() <= this.upperIp.getBinaryValue());
	}
	
	/**
	 * Calculates the number of available host IPs.
	 * 
	 * @return number of available IPs.
	 */
	public int numberOfHosts() {
		return this.upperIp.getBinaryValue() - this.lowerIp.getBinaryValue() + 1;
	}
	
	/**
	 * @return the baseIP
	 */
	public BaseIpAddress getBaseIP() {
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
	public IpAddress getLowerIp() {
		return this.lowerIp;
	}

	/**
	 * @return the upperIp
	 */
	public IpAddress getUpperIp() {
		return this.upperIp;
	}
	
}
