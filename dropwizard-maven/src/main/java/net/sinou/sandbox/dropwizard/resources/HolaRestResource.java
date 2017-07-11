package net.sinou.sandbox.dropwizard.resources;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.codahale.metrics.annotation.Timed;

/** Simple hello world with Dropwizard */
@Path("/api")
public class HolaRestResource {

	private String saying;

	public HolaRestResource(final String saying) {
		this.saying = saying;
	}

	@Path("/hola")
	@GET
	// Register a metric for this entry point
	@Timed
	public String hola() throws UnknownHostException {
		String hostname = null;
		try {
			hostname = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			hostname = "unknown";
		}
		return saying + " " + hostname;
	}
}
