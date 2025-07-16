package by.step;

import java.math.BigInteger;
import java.time.Instant;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        int i = a * b;
        return i;
    }

    public BigInteger multiply(BigInteger b1, BigInteger b2){
        Instant start = Instant.now();
        BigInteger result = b1.multiply(b2);
        Instant end = Instant.now();
        System.err.println(end.toEpochMilli() - start.toEpochMilli());
        return result;
    }

    public BigInteger powerBigInt(BigInteger bigInteger, int power){
        Instant start = Instant.now();
        BigInteger result = bigInteger.pow(power);
        Instant end = Instant.now();
        System.err.println(end.toEpochMilli() - start.toEpochMilli());
        return result;
    }
}
