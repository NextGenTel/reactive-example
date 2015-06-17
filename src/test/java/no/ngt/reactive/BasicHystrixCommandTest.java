package no.ngt.reactive;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicHystrixCommandTest extends BaseSetup {

	@Test
	public void testHystrixCommand() throws Exception {
		HystrixCommand<String> hystrixCommand = new HystrixCommand<String>(HystrixCommandGroupKey.Factory.asKey("test")) {
			@Override
			protected String run() throws Exception {
				return pingTarget.request().get(String.class);
			}
		};

		assertEquals("pong", hystrixCommand.execute());
	}

	@Test(expected = HystrixRuntimeException.class)
	public void testHystrixCommand_withTimeout() throws Exception {
		HystrixCommand<String> hystrixCommand = new HystrixCommand<String>(HystrixCommandGroupKey.Factory.asKey("test")) {
			@Override
			protected String run() throws Exception {
				return pingTarget.queryParam("delay", 2).request().get(String.class);
			}
		};

		hystrixCommand.execute();
	}

	@Test
	public void testHystrixCommand_withFallbackAndTimeout() throws Exception {
		HystrixCommand<String> hystrixCommand = new HystrixCommand<String>(HystrixCommandGroupKey.Factory.asKey("test")) {
			@Override
			protected String run() throws Exception {
				return pingTarget.queryParam("delay", 2).request().get(String.class);
			}

			@Override
			protected String getFallback() {
				return "fallback";
			}
		};

		assertEquals("fallback", hystrixCommand.execute());
	}
}
