package net.sinou.tutorials.springbatch.dummy;

import org.junit.Test;

import net.sinou.tutorials.springbatch.dummy.LogbackDemo;

public class LogbackDemoTest {

	@Test
	public void test() {
		LogbackDemo ld = new LogbackDemo();
		ld.performTask();
	}
}
