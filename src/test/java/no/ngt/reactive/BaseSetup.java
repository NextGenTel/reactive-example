package no.ngt.reactive;

import org.junit.Before;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public abstract class BaseSetup {

	Client client;
	WebTarget pingTarget;

	@Before
	public void setUp() throws Exception {
		client = ClientBuilder.newClient();

		pingTarget = client.target("http://127.0.0.1:8080/").path("ping");
	}
}
