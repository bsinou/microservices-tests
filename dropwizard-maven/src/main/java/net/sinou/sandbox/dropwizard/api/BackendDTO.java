package net.sinou.sandbox.dropwizard.api;

/**
 * Dummy backend object to test the Dropwiward approach. Created by ceposta
 * (http://christianposta.com/blog) for RedHat
 */
public class BackendDTO {

	private String greeting;
	private long time;
	private String ip;

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
