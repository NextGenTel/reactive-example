package no.ngt.reactive;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

public class HystrixRequestCacheTest extends BaseSetup {

	public static final int HYSTRIX_COMMAND_TIMEOUT = 5000;
	public static final int PING_DELAY = 2;

	@Test(timeout = 3000)
	public void testName() throws Exception {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try  {
			String result1 = getHystrixCommand().execute();
			String result2 = getHystrixCommand().execute();
		} finally {
			context.shutdown();
		}
	}

	private HystrixCommand<String> getHystrixCommand() {
		return new HystrixCommand<String>(HystrixCommandGroupKey.Factory.asKey("test"), HYSTRIX_COMMAND_TIMEOUT) {
			@Override
			protected String run() throws Exception {
				return pingTarget.queryParam("delay", PING_DELAY).request().get(String.class);
			}

			@Override
			protected String getCacheKey() {
				return "42";
			}
		};
	}

}
