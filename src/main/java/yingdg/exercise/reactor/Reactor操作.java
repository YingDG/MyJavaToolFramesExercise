package yingdg.exercise.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author zdm
 * created in 2019/2/27 下午3:35
 */
public class Reactor操作 {

    @Test
    public void bufferDemo() {
        Flux.range(1, 100)
                .buffer(20) // 分组，返回集合
                .subscribe(System.out::println);

        Flux.range(1, 10)
                .bufferUntil(i -> i % 2 == 0) // 断流，返回集合
                .subscribe(System.out::println);

        Flux.range(1, 10)
                .bufferWhile(i -> i % 2 == 0) // 取偶，返回集合
                .subscribe(System.out::println);

        // todo
        Flux.interval(Duration.ofMillis(100)).buffer(Duration.ofMillis(1001))
                .take(3)
                .toStream().forEach(System.out::println);
    }

    @Test
    public void filterDemo() {
        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
    }

    @Test
    public void windowDemo() {
        Flux.range(1, 100).window(20).subscribe(System.out::println);
        Flux.interval(Duration.ofMillis(100)).window(Duration.ofMillis(1001))
                .take(2)
                .toStream().forEach(System.out::println);
    }

    @Test
    public void zipWithDemo() {
        // [a,c]
        // [b,d]
        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"))
                .subscribe(System.out::println);
        // a-c
        // b-d
        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
                .subscribe(System.out::println);
    }

    @Test
    public void takeDemo() {
        Flux.range(1, 100).take(10).subscribe(System.out::println);
        Flux.range(1, 100).takeLast(10).subscribe(System.out::println);
        Flux.range(1, 100).takeWhile(i -> i < 10).subscribe(System.out::println);
        Flux.range(1, 100).takeUntil(i -> i == 10).subscribe(System.out::println);
    }

    @Test
    public void reduceDemo() {
        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);
    }

    @Test
    public void mergeDemo() {
        Flux.merge(
                Flux.interval(Duration.ofMillis(0), Duration.ofMillis(100)).take(5),
                Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream().forEach(System.out::println);
        Flux.mergeSequential(
                Flux.interval(Duration.ofMillis(0), Duration.ofMillis(100)).take(5),
                Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream().forEach(System.out::println);
    }

    @Test
    public void flatMapDemo() {
        Flux.just(5, 10)
                .flatMap(x -> Flux.interval(Duration.ofMillis(x * 10), Duration.ofMillis(100)).take(x))
                .toStream().forEach(System.out::println);
    }

    @Test
    public void concatMapDemo() {
        Flux.just(5, 10)
                .concatMap(x -> Flux.interval(Duration.ofMillis(x * 10), Duration.ofMillis(100)).take(x))
                .toStream().forEach(System.out::println);
    }

    @Test
    public void combineLatestDemo() {
        Flux.combineLatest(
                Arrays::toString,
                Flux.interval(Duration.ofMillis(100)).take(5),
                Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5)
        ).toStream().forEach(System.out::println);
    }

}
