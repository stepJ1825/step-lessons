package by.step.streams;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;


class ManyNumbersCounter implements Runnable {
    static final AtomicReference<BigInteger> accumulator = new AtomicReference<>(BigInteger.valueOf(0));
    private final int factorialArg;

    public ManyNumbersCounter(int factorialArg) {
        this.factorialArg = factorialArg;
    }



    @Override
    public void run() {
//        try {
//            TimeUnit.MILLISECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        accumulator.getAndUpdate(bigInteger -> bigInteger.add(factorial(factorialArg)));
    }

    private BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

}
