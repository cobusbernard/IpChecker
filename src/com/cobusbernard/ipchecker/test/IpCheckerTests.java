package com.cobusbernard.ipchecker.test;

import junit.framework.Assert;

import org.junit.Test;

import com.cobusbernard.ipchecker.main.IpChecker;

public class IpCheckerTests {

	@Test
	public void test() {
		//* Supplied test cases
		Assert.assertEquals(true, IpChecker.testIp("198.51.100.0/22", "198.51.100.5"));
		Assert.assertEquals(true, IpChecker.testIp("198.51.10.4/30 ", "198.51.10.5"));
		Assert.assertEquals(false, IpChecker.testIp("198.51.10.4/30", "198.51.10.9"));
	}

}
