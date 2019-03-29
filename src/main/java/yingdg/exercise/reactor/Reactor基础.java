package yingdg.exercise.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zdm
 * created in 2019/2/27 下午2:39
 */
public class Reactor基础 {

    @Test
    public void test0() {
        Mono.just("Hello Reactor").subscribe(System.out::println);
    }

    @Test
    public void test1() {
        Flux.just(0, 1, 2, 3)
                .map(e -> 1 / e)
                .subscribe(
                        System.out::println,
                        throwable -> System.err.println(throwable),
                        () -> System.out.println("complete")
                );
    }

    @Test
    public void fluxDemo() {
        Flux.just("Hello", "World").subscribe(System.out::println);
        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux<Object> never = Flux.never();
        Flux.range(1, 10).subscribe(System.out::println);
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
    }

    @Test
    public void fluxDemo2() {
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);

        final AtomicInteger integer = new AtomicInteger();
        Flux.generate(
                ArrayList::new,
                (list, sink) -> { // 同步模式
                    int value = integer.getAndIncrement();
                    list.add(value);
                    sink.next(value);

//                    if (list.size() == 9) {
//                        sink.error(new RuntimeException("error"));
//                    }
                    if (list.size() == 10) {
                        sink.complete();
                    }

                    return list;
                },
                list -> System.out.println("size:" + list.size())
        )
                .subscribe(System.out::println);

        Flux.create(
                sink -> {
                    for (int i = 0; i < 10; i++) {
                        sink.next(i);
                    }
                    sink.complete();
                },

                FluxSink.OverflowStrategy.ERROR
        )
                .subscribe(System.out::println);
    }

    @Test
    public void monoDemo() {
        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
    }

    @Test
    public void returnResult() throws ExecutionException, InterruptedException {
        Mono<String> mono = Mono.just("Hello Reactor");
        String s = mono.toFuture().get();
        System.out.println(s);

        Flux<Integer> just = Flux.just(1, 2, 3).map(e -> e + 1);
        Stream<Integer> integers = just.toStream();
//        just.map(e -> e * 2);
        System.out.println(integers.collect(Collectors.toList()));
    }

}
