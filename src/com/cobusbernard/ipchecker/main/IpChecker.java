/**
 * 
 */
package com.cobusbernard.ipchecker.main;

import com.cobusbernard.ipchecker.exception.InvalidCidrFormatException;
import com.cobusbernard.ipchecker.exception.InvalidIpFormatException;
import com.cobusbernard.ipchecker.ipaddress.CIDRBlock;
import com.cobusbernard.ipchecker.ipaddress.IpAddress;


/**
 * Command line app to check if an IP falls within the range of a CIDR block.
 * 
 * @author Cobus Bernard
 */
public class IpChecker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			CIDRBlock block = new CIDRBlock(args[0]);
			IpAddress ip = new IpAddress(args[1]);
			
			System.out.println("\nThe ip " + ip.toString() + " is " + (block.containsIP(ip) ? "" : "not ")
					+ "contained in the CIDR block between " + block.getLowerIp().toString() 
					+ " and "+ block.getUpperIp().toString() + ".");
			
		} catch (InvalidCidrFormatException e) {
			System.out.println(e.getMessage());
		} catch (InvalidIpFormatException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
