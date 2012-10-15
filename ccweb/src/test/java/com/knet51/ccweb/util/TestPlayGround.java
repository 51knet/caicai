package com.knet51.ccweb.util;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class TestPlayGround {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFormat() {
		String format = "delete from %s x where x.%s = %d";
		String arg1 = "User";
		String arg2 = "id";
		Long arg3 = 1l;
		String result = String.format(format, arg1, arg2, arg3);
		Assert.assertEquals("delete from User x where x.id = 1", result);
	}

}
