package no.ngt.reactive;

import org.junit.Test;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Arrays;

/**
 * @author zapodot
 */
public class OperatorExamplesTest {

    @Test
    public void testOperators() throws Exception {

        final Observable<Integer> numbers = Observable.range(1, 10);
        final Observable<String> names = Observable.from(
                Arrays.asList(
                        "Nora",
                        "Emma",
                        "Sara",
                        "Sofie",
                        "Emilie",
                        "Anna",
                        "Linnea",
                        "Thea",
                        "Maja",
                        "Sofia"));
        final Observable<String> zipped = numbers.zipWith(names, (number, name) -> String.format("%s: %s", number, name));
        zipped.subscribeOn(Schedulers.immediate()).subscribe(s -> System.out.println(s));

    }
}
