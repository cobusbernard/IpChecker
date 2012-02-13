package com.cobusbernard.ipchecker.test;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import com.cobusbernard.ipchecker.exception.InvalidCidrFormatException;
import com.cobusbernard.ipchecker.exception.InvalidIpFormatException;
import com.cobusbernard.ipchecker.ipaddress.BaseIpAddress;
import com.cobusbernard.ipchecker.ipaddress.CIDRBlock;
import com.cobusbernard.ipchecker.ipaddress.IpAddress;
import com.cobusbernard.ipchecker.main.IpChecker;

public class IpCheckerTests {

	@Test
	public void testIpAddressClass() {
		IpAddress address1 = null;
		try {
			address1 = new IpAddress("198.51.100.5");
		} catch (InvalidIpFormatException e) {
			fail("Could not construct IPAddress object. [ " + e.getMessage() + " ]");
		}
		
		Assert.assertEquals(198, address1.getAddress_A());
		Assert.assertEquals(51, address1.getAddress_B());
		Assert.assertEquals(100, address1.getAddress_C());
		Assert.assertEquals(5, address1.getAddress_D());
	}
	
	@Test
	public void testIpAddressClassWithInvalidIpAddress() {
		try {
			new IpAddress("300.51.100.1");
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
			fail("Could not construct CIDRBlock object. [ " + e.getMessage() + " ]");
		}
		
		//Test BaseIp.
		BaseIpAddress baseIp = cidrblock.getBaseIP();
		Assert.assertEquals(198, baseIp.getAddress_A());
		Assert.assertEquals(51, baseIp.getAddress_B());
		Assert.assertEquals(100, baseIp.getAddress_C());
		Assert.assertEquals(0, baseIp.getAddress_D());
		
		//Test leading bits.
		Assert.assertEquals(22, cidrblock.getLeadingBits());
		
		//Test BottomIP.
		IpAddress bottomIp = cidrblock.getBottomIp();
		Assert.assertEquals(198, bottomIp.getAddress_A());
		Assert.assertEquals(51, bottomIp.getAddress_B());
		Assert.assertEquals(100, bottomIp.getAddress_C());
		Assert.assertEquals(1, bottomIp.getAddress_D());
		
		//Test UpperIP.
		IpAddress upperIp = cidrblock.getBottomIp();
		Assert.assertEquals(198, upperIp.getAddress_A());
		Assert.assertEquals(51, upperIp.getAddress_B());
		Assert.assertEquals(103, upperIp.getAddress_C());
		Assert.assertEquals(254, upperIp.getAddress_D());
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
		Assert.assertEquals(true, IpChecker.testIp("198.51.100.0/22", "198.51.100.5"));
		Assert.assertEquals(true, IpChecker.testIp("198.51.10.4/30 ", "198.51.10.5"));
		Assert.assertEquals(false, IpChecker.testIp("198.51.10.4/30", "198.51.10.9"));
	}

}
