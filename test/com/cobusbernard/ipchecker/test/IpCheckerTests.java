package com.cobusbernard.ipchecker.test;

import junit.framework.Assert;

import org.junit.Test;

import com.cobusbernard.ipchecker.exception.InvalidCidrFormatException;
import com.cobusbernard.ipchecker.exception.InvalidIpFormatException;
import com.cobusbernard.ipchecker.ipaddress.BaseIpAddress;
import com.cobusbernard.ipchecker.ipaddress.CIDRBlock;
import com.cobusbernard.ipchecker.ipaddress.IpAddress;

/**
 * Tests to ensure that the IpChecker program functions correctly.
 * 
 * @author Cobus Bernard
 */
public class IpCheckerTests {

	@Test
	public void testIpAddressClass() {
		IpAddress address1 = null;
		try {
			address1 = new IpAddress("198.51.100.5");
		} catch (InvalidIpFormatException e) {
			Assert.fail("Could not construct IPAddress object. [ " + e.getMessage() + " ]");
		}
		
		//Test BottomIP.
		testIp(address1, 198, 51, 100, 5);
	}
	
	@Test
	public void testIpAddressClassWithInvalidIpAddress() {
		try {
			new IpAddress("300.51.100.1");
			Assert.assertEquals("Invalid ip address.", false, true);
		} catch (InvalidIpFormatException e) {
			Assert.assertEquals(true, true);
		}

		try {
			new IpAddress("200.51.100.0");
			Assert.assertEquals("Invalid ip address.", false, true);
		} catch (InvalidIpFormatException e) {
			Assert.assertEquals(true, true);
		}
	}
	
	@Test
	public void testCidrBlockClass() {
		CIDRBlock cidrblock = null;
		try {
			cidrblock = new CIDRBlock("198.51.100.0/22");
		} catch (InvalidCidrFormatException e) {
			Assert.fail("Could not construct CIDRBlock object. [ " + e.getMessage() + " ]");
		}
		
		//Test BaseIp.
		testIp(cidrblock.getBaseIP(), 198, 51, 100, 0);
		
		//Test available IPs.
		Assert.assertEquals(1022, cidrblock.numberOfHosts());
		
		//Test leading bits.
		Assert.assertEquals(22, cidrblock.getLeadingBits());
		
		//Test BottomIP.
		testIp(cidrblock.getLowerIp(), 198, 51, 100, 1);
		
		//Test UpperIP.
		testIp(cidrblock.getUpperIp(), 198, 51, 103, 254);
	}
	
	@Test
	public void testCidrBlockClassZeroZeroZeroZeroSlashOne() {
		CIDRBlock cidrblock = null;
		try {
			cidrblock = new CIDRBlock("0.0.0.0/1");
		} catch (InvalidCidrFormatException e) {
			Assert.fail("Could not construct CIDRBlock object. [ " + e.getMessage() + " ]");
		}
		//Test BaseIp.
		testIp(cidrblock.getBaseIP(), 0, 0, 0, 0);
		
		//Test available IPs.
		Assert.assertEquals(2147483646, cidrblock.numberOfHosts());
		
		//Test leading bits.
		Assert.assertEquals(1, cidrblock.getLeadingBits());
		
		//Test BottomIP.
		testIp(cidrblock.getLowerIp(), 0, 0, 0, 1);
		
		//Test UpperIP.
		testIp(cidrblock.getUpperIp(), 127, 255, 255, 254);
	}
	
	@Test
	public void testCidrBlockClassOneOneOneOneSlashOne() {
		CIDRBlock cidrblock = null;
		try {
			cidrblock = new CIDRBlock("1.1.1.1/1");
		} catch (InvalidCidrFormatException e) {
			Assert.fail("Could not construct CIDRBlock object. [ " + e.getMessage() + " ]");
		}
		//Test BaseIp.
		testIp(cidrblock.getBaseIP(), 0, 0, 0, 0);
		
		//Test available IPs.
		Assert.assertEquals(2147483646, cidrblock.numberOfHosts());
		
		//Test leading bits.
		Assert.assertEquals(1, cidrblock.getLeadingBits());
		
		//Test BottomIP.
		testIp(cidrblock.getLowerIp(), 0, 0, 0, 1);
		
		//Test UpperIP.
		testIp(cidrblock.getUpperIp(), 127, 255, 255, 254);
	}
	
	
	@Test
	public void testCidrBlockClassOneOneOneOneSlashTwo() {
		CIDRBlock cidrblock = null;
		try {
			cidrblock = new CIDRBlock("1.1.1.0/2");
		} catch (InvalidCidrFormatException e) {
			Assert.fail("Could not construct CIDRBlock object. [ " + e.getMessage() + " ]");
		}
		//Test BaseIp.
		testIp(cidrblock.getBaseIP(), 0, 0, 0, 0);
		
		//Test available IPs.
		Assert.assertEquals(1073741822, cidrblock.numberOfHosts());
		
		//Test leading bits.
		Assert.assertEquals(2, cidrblock.getLeadingBits());
		
		//Test BottomIP.
		testIp(cidrblock.getLowerIp(), 0, 0, 0, 1);
		
		//Test UpperIP.
		testIp(cidrblock.getUpperIp(), 63, 255, 255, 254);
	}
	
	@Test
	public void testCidrBlockClassTenZeroZeroZeroSlashTwentyFour() {
		CIDRBlock cidrblock = null;
		try {
			cidrblock = new CIDRBlock("10.0.0.0/24");
		} catch (InvalidCidrFormatException e) {
			Assert.fail("Could not construct CIDRBlock object. [ " + e.getMessage() + " ]");
		}
		//Test BaseIp.
		testIp(cidrblock.getBaseIP(), 10, 0, 0, 0);
		
		//Test available IPs.
		Assert.assertEquals(254, cidrblock.numberOfHosts());
		
		//Test leading bits.
		Assert.assertEquals(24, cidrblock.getLeadingBits());
		
		//Test BottomIP.
		testIp(cidrblock.getLowerIp(), 10, 0, 0, 1);
		
		//Test UpperIP.
		testIp(cidrblock.getUpperIp(), 10, 0, 0, 254);
	}
	
	@Test
	public void testCidrBlockClassNonZeroBaseAddress() {
		CIDRBlock cidrblock = null;
		try {
			cidrblock = new CIDRBlock("198.51.100.50/28");
		} catch (InvalidCidrFormatException e) {
			Assert.fail("Could not construct CIDRBlock object. [ " + e.getMessage() + " ]");
		}
		
		//Test BaseIp.
		testIp(cidrblock.getBaseIP(), 198, 51, 100, 48);
		
		//Test leading bits.
		Assert.assertEquals(28, cidrblock.getLeadingBits());
		
		//Test BottomIP.
		testIp(cidrblock.getLowerIp(), 198, 51, 100, 49);
		
		//Test UpperIP.
		testIp(cidrblock.getUpperIp(), 198, 51, 100, 62);
	}
	
	@Test
	public void testCidrBlockClassWithInvalidLeadingBits() {
		try {
			new CIDRBlock("198.51.100.0/33");
			Assert.assertEquals("Invalid leading bits.", false, true);
		} catch (InvalidCidrFormatException e) {
			Assert.assertEquals(true, true);
		}
	}
	
	@Test
	public void testApplication() {
		
		//* Supplied test cases
		try {
			CIDRBlock block1 = new CIDRBlock("198.51.100.0/22");
			Assert.assertEquals(true, block1.containsIP(new IpAddress("198.51.100.5")));
		} catch (InvalidCidrFormatException e) {
			Assert.fail("Could not create CIDR block.");
		} catch (InvalidIpFormatException e) {
			Assert.fail("Could not create Ip Address to compare.");
		}
		
		try {
			CIDRBlock block1 = new CIDRBlock("198.51.10.4/30");
			Assert.assertEquals(true, block1.containsIP(new IpAddress("198.51.10.5")));
		} catch (InvalidCidrFormatException e) {
			Assert.fail("Could not create CIDR block.");
		} catch (InvalidIpFormatException e) {
			Assert.fail("Could not create Ip Address to compare.");
		}
		
		try {
			CIDRBlock block1 = new CIDRBlock("198.51.10.4/30");
			Assert.assertEquals(false, block1.containsIP(new IpAddress("198.51.10.9")));
		} catch (InvalidCidrFormatException e) {
			Assert.fail("Could not create CIDR block.");
		} catch (InvalidIpFormatException e) {
			Assert.fail("Could not create Ip Address to compare.");
		}
	}
	
	private void testIp(BaseIpAddress baseIp, int a, int b, int c, int d) {
		//Test BaseIp.
		Assert.assertEquals(a, baseIp.getAddress_A());
		Assert.assertEquals(b, baseIp.getAddress_B());
		Assert.assertEquals(c, baseIp.getAddress_C());
		Assert.assertEquals(d, baseIp.getAddress_D());
	}
	
	private void testIp(IpAddress baseIp, int a, int b, int c, int d) {
		//Test Ip.
		Assert.assertEquals(a, baseIp.getAddress_A());
		Assert.assertEquals(b, baseIp.getAddress_B());
		Assert.assertEquals(c, baseIp.getAddress_C());
		Assert.assertEquals(d, baseIp.getAddress_D());
	}
	
}
