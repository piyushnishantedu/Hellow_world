package com.devopssample.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class TestClass extends TestCase {

	public int add(int a, int b) {
		return a + b;
	}

	public int sub(int a, int b) {
		return a - b;
	}

	@Test
	public void testAdd() {
		int total = 8;
		int sum = add(4, 4);
		assertEquals(sum, total);
	}

	@Test
	public void testFailedAdd() {
		int total = 9;
		int sum = add(10, 5);
		assertNotSame(sum, total);
	}

	@Test
	public void testSub() {
		int total = 0;
		int sub = sub(4, 4);
		assertEquals(sub, total);
	}
}
