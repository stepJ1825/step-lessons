package by.step.classwork2.examples.parallel;

import java.math.BigInteger;
import java.util.Date;
import java.util.stream.IntStream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        Date start = new Date();
        int startInclusive = 100000;
        int endInclusive = 100010;
        IntStream.rangeClosed(startInclusive, endInclusive)
                 .mapToObj(x -> {
                     BigInteger result = BigInteger.valueOf(1);
                     for (int i = 1; i <= x; i++) {
                         result = result.multiply(BigInteger.valueOf(i));
                     }
                     return result;
                 })
                 .forEach(x1 -> System.out.println("integer length: " + String.valueOf(x1).length()));
        Date end = new Date();
        System.out.println("time spent: " + (end.getTime() - start.getTime()));

        Date start2 = new Date();
        IntStream.rangeClosed(startInclusive, endInclusive)
                 .parallel()
                 .mapToObj(x -> {
                     BigInteger result = BigInteger.valueOf(1);
                     for (int i = 1; i <= x; i++) {
                         result = result.multiply(BigInteger.valueOf(i));
                     }
                     return result;
                 })
                 .forEach(x1 -> System.out.println("integer length: " + String.valueOf(x1).length()));
        Date end2 = new Date();
        System.out.println("time spent (parallel): " + (end2.getTime() - start2.getTime()));
    }
}
