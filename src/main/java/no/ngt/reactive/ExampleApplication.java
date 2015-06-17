package no.ngt.reactive;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import no.ngt.reactive.resource.PingResource;

public class ExampleApplication extends Application<ExampleConfig> {
	@Override
	public void run(ExampleConfig configuration, Environment environment) throws Exception {
		environment.jersey().register(new PingResource());
	}

	public static void main(String[] args) throws Exception {
		new ExampleApplication().run(args);
	}
}
