package net.sinou.sandbox.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import net.sinou.sandbox.dropwizard.resources.GreeterSayingFactory;
import net.sinou.sandbox.dropwizard.resources.HelloSayingFactory;

public class DropwizardConfiguration extends Configuration {

	private HelloSayingFactory sayingFactory;
	private GreeterSayingFactory greeterSayingFactory;

	@JsonProperty("helloapp")
	public HelloSayingFactory getSayingFactory() {
		return sayingFactory;
	}

	@JsonProperty("helloapp")
	public void setSayingFactory(HelloSayingFactory sayingFactory) {
		this.sayingFactory = sayingFactory;
	}

	@JsonProperty("greeter")
	public GreeterSayingFactory getGreeterSayingFactory() {
		return greeterSayingFactory;
	}

	@JsonProperty("greeter")
	public void setGreeterSayingFactory(GreeterSayingFactory greeterSayingFactory) {
		this.greeterSayingFactory = greeterSayingFactory;
	}
}
