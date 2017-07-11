package net.sinou.sandbox.dropwizard.resources;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Wrap configurations that are under our helloapp configuration in our YAML
 * file, this is instantiated and exposed by the DropwizardConfiguration class
 */
public class HelloSayingFactory {
	@NotEmpty
	private String saying;

	@JsonProperty
	public String getSaying() {
		return saying;
	}

	@JsonProperty
	public void setSaying(String saying) {
		this.saying = saying;
	}
}
