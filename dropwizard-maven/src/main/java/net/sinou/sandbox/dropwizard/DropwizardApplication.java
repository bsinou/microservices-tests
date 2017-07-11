package net.sinou.sandbox.dropwizard;

import javax.ws.rs.client.Client;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.sinou.sandbox.dropwizard.resources.GreeterRestResource;
import net.sinou.sandbox.dropwizard.resources.GreeterSayingFactory;
import net.sinou.sandbox.dropwizard.resources.HolaRestResource;

/** Basic entry point for our test DropWizard Application */
public class DropwizardApplication extends Application<DropwizardConfiguration> {

	public static void main(final String[] args) throws Exception {
		new DropwizardApplication().run(args);
	}

	@Override
	public String getName() {
		return "Dropwizard";
	}

	@Override
	public void initialize(final Bootstrap<DropwizardConfiguration> bootstrap) {
		// Enable variable substitution with environment variables
		bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
				bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
	}

	@Override
	public void run(final DropwizardConfiguration configuration, final Environment environment) {
		// basic hello world
		environment.jersey().register(new HolaRestResource(configuration.getSayingFactory().getSaying()));

		// hello world with a backend service
		GreeterSayingFactory greeterSayingFactory = configuration.getGreeterSayingFactory();
		Client greeterClient = new JerseyClientBuilder(environment).using(greeterSayingFactory.getJerseyClientConfig())
				.build("greeterClient");
		environment.jersey().register(new GreeterRestResource(greeterSayingFactory.getSaying(),
				greeterSayingFactory.getHost(), greeterSayingFactory.getPort(), greeterClient));
	}
}
