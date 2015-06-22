package no.ngt.reactive;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HystrixRequestCacheTest extends BaseSetup {

	public static final int HYSTRIX_COMMAND_TIMEOUT = 5000;
	public static final int PING_DELAY = 2;
	public static final HystrixCommandGroupKey HYSTRIX_COMMAND_GROUP_KEY = HystrixCommandGroupKey.Factory.asKey("test");

	@Test(timeout = 3000)
	public void testName() throws Exception {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try  {
			String result1 = getHystrixCommandWithDelay().execute();
			String result2 = getHystrixCommandWithDelay().execute();

			assertEquals(result1, result2);
		} finally {
			context.shutdown();
		}
	}

	private HystrixCommand<String> getHystrixCommandWithDelay() {
		return new HystrixCommand<String>(HYSTRIX_COMMAND_GROUP_KEY, HYSTRIX_COMMAND_TIMEOUT) {
			@Override
			protected String run() throws Exception {
				return pingTarget
						.queryParam("delay", PING_DELAY)
						.request()
						.get(String.class);
			}

			@Override
			protected String getCacheKey() {
				return "42";
			}
		};
	}

}
