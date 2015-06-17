package no.ngt.reactive;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ExampleApplication extends Application<ExampleConfig> {
	@Override
	public void run(ExampleConfig configuration, Environment environment) throws Exception {

	}

	public static void main(String[] args) throws Exception {
		new ExampleApplication().run(args);
	}
}
