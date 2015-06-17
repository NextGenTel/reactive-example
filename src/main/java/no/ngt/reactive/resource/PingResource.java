package no.ngt.reactive.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

@Path("/ping")
@Produces(MediaType.TEXT_PLAIN)
public class PingResource {

	@GET
	public String echo(@QueryParam("delay") int delay) {
		try {
			Thread.sleep(TimeUnit.MILLISECONDS.convert(delay, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "pong";
	}
}
