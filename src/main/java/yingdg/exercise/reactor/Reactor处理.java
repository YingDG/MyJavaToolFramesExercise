package yingdg.exercise.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author zdm
 * created in 2019/2/27 下午4:35
 */
public class Reactor处理 {

    @Test
    public void errorHandle() {
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .subscribe(System.out::println, System.err::println);

        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn(0)
                .subscribe(System.out::println);
    }

    @Test
    public void retry() {
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .retry(1)
                .subscribe(System.out::println, System.err::println);
    }

    @Test
    public void scheduler() {
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream().forEach(System.out::println);
    }

    @Test
    public void debug() {
//        StepVerifier.create(Flux.just("a", "b"))
//                .expectNext("a")
//                .expectNext("b")
//                .verifyComplete();

//        StepVerifier.withVirtualTime(() -> Flux.interval(Duration.ofHours(4), Duration.ofDays(1)).take(2))
//                .expectSubscription()
//                .expectNoEvent(Duration.ofHours(4))
//                .expectNext(0L)
//                .thenAwait(Duration.ofDays(1))
//                .expectNext(1L)
//                .verifyComplete();

        Flux.just(1, 0).map(x -> 1 / x).checkpoint("test").subscribe(System.out::println);
        Flux.range(1, 2).log("Range").subscribe(System.out::println);
    }

    @Test
    public void test() {
        Hooks.onOperatorDebug();

//        final TestPublisher<String> testPublisher = TestPublisher.create();
//        testPublisher.next("a");
//        testPublisher.next("b");
//        testPublisher.complete();
//
//        StepVerifier.create(testPublisher)
//                .expectNext("a")
//                .expectNext("b")
//                .expectComplete();
    }

    @Test
    public void warmAndCold() throws InterruptedException {
        final Flux<Long> source = Flux.interval(Duration.ofMillis(1000))
                .take(10)
                .publish()
                .autoConnect();
        source.subscribe();

        TimeUnit.SECONDS.sleep(5);
        source.toStream().forEach(System.out::println);
    }

}
