/**
 * 
 */
package com.cobusbernard.ipchecker.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter the CIDR block: ");

		CIDRBlock block = null;
		
		while (block == null)
		{
			System.out.print("Please enter the CIDR block: ");
			try {
				block = new CIDRBlock(br.readLine());
			} catch (IOException ioe) {
				System.out.println("IO error trying to read CIDR block!");
			} catch (InvalidCidrFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		
		IpAddress ip = null;
		
		while (ip == null)
		{
			System.out.print("Please enter the IP address: ");
			try {
				ip = new IpAddress(br.readLine());
			} catch (IOException ioe) {
				System.out.println("IO error trying to read CIDR block!");
			} catch (InvalidIpFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		
		System.out.println("\nThe ip " + ip.toString() + " is " + (block.containsIP(ip) ? "" : "not ")
				+ "contained in the CIDR block between " + block.getLowerIp().toString() 
				+ " and "+ block.getUpperIp().toString() + ".");
		
	}
}
