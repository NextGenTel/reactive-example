package no.ngt.reactive;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.junit.Test;
import rx.Observable;

import static org.junit.Assert.assertEquals;

public class BasicHystrixObservableCommandTest extends BaseSetup {

	public static final HystrixCommandGroupKey COMMAND_GROUP_KEY = HystrixCommandGroupKey.Factory.asKey("test");

	@Test
	public void testName() throws Exception {
		HystrixObservableCommand<String> command = new HystrixObservableCommand<String>(COMMAND_GROUP_KEY) {
			@Override
			protected Observable<String> construct() {
				return Observable.just(
						pingTarget
								.request()
								.get(String.class)
				);
			}
		};

		final Observable<String> observable = command.observe();

		assertEquals("pong", observable.toBlocking().first());
	}
}
