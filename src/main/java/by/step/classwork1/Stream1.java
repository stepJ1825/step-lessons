package by.step.classwork1;

public class Stream1 {
    public static void main(String[] args) {
//        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
//        long count = stream
//                .filter(integer -> integer > 0)
//                .peek(integer -> System.out.println(integer))
//                .count();
//        System.out.println("count: " + count);
//
//        List<Integer> integers = List.of(5, 4, 3, 21);
//        Stream<Integer> stream1 = integers.stream();
//
//        Stream<String> streamOfArray = Arrays.stream(new String[]{"a", "b", "c"});
//        List<String> list = streamOfArray.toList();
//        long count1 = streamOfArray.count();
//
//        Stream.generate(() -> 100);


//        Stream.iterate(40, n -> n + 2).limit(20).forEach(System.out::println);
//
//        for (int i = 0; i < 40; i = i + 2) {
//            System.out.println("---" + (40 + i));
//        }
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//        }
//
//        IntStream.range(0, 10)
//                .filter(value -> value > 0)
//                .forEach(System.out::println);

//        new Random().doubles(3).forEach(System.out::println);

        "abcABCабвАБВ".chars().forEach(System.out::println);
        System.out.println((int)'a');
    }
}
