package com.xt.sentense;

import junit.framework.TestCase;

public class OtherTest extends TestCase{
	public void test01(){
		assertTrue("Duplicate entry '77ff思意' for key 'UK_4gpl8787ecnv7fv7unn2mp'"
				.matches("Duplicate entry .* for key .*"));
	}
}
