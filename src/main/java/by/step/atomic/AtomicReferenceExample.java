package by.step.atomic;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class AtomicReferenceExample {
    public static void main(String[] args) {
        AtomicReference<AtomicCounter> atomicReference =
                new AtomicReference<>(new AtomicCounter());

        Stream.generate(() -> 1).limit(1_000_000)
                .parallel()
                .forEach(integer -> {
//                    AtomicCounter atomicCounter = atomicReference.get();
//                    atomicCounter.setCount(atomicCounter.getCount() + 1);
//                    atomicReference.set(atomicCounter);
                    atomicReference.getAndAccumulate(atomicReference.get(),
                            (atomicCounter, atomicCounter2) -> {
                                int i = atomicCounter.getCount() + atomicCounter2.getCount();
                                atomicCounter.setCount(i);
                                return atomicCounter;
                            });
                });

        System.out.println();
    }
}

@Data
class AtomicCounter {
    Integer count = Integer.valueOf(0);
    String counterName = "default";
}
