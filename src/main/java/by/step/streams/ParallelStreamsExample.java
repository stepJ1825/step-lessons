package by.step.streams;

import java.math.BigInteger;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamsExample {


    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(0);

        Stream.generate(() -> 1).limit(1_000_000)
                .parallel()
                .forEach(integer -> counter.getCount().incrementAndGet());

        System.out.println("counter: " + counter.getCount());

    }
}

class Counter{

    AtomicInteger count = new AtomicInteger();

    public Counter(int initialCount) {
        new AtomicInteger(initialCount);
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }
}
