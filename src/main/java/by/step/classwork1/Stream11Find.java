package by.step.classwork1;

import java.util.LongSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Stream11Find {
    public static void main(String[] args) {
        int anySeq = IntStream.range(4, 65536)
                .parallel()
                .findFirst()
                .getAsInt();
// anySeq: 4
        System.out.println(anySeq);

        LongSummaryStatistics stats = LongStream.range(2, 16)
                .summaryStatistics();


    }
}
