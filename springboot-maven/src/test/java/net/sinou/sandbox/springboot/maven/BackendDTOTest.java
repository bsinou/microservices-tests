package net.sinou.sandbox.springboot.maven;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Dummy tests to play with Jacoco */
public class BackendDTOTest {

	@Test
	public void testGreeting() {
		BackendDTO bdto = new BackendDTO();
		bdto.setGreeting("Hello");
		assertEquals("Hello", bdto.getGreeting());
	}

	@Test
	public void testTime() {
		long now = System.currentTimeMillis();
		BackendDTO bdto = new BackendDTO();
		bdto.setTime(now);
		assertEquals(now, bdto.getTime());
	}

	@Test
	public void testIp() {
		String ip = "192.168.0.1";
		BackendDTO bdto = new BackendDTO();
		bdto.setIp(ip);
		assertEquals(ip, bdto.getIp());
	}

}
