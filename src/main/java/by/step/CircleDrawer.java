package by.step;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CircleDrawer {

    public static void main(String[] args) {
//        circleGasperchuk(-1);
//        circleGasperchuk(1);
//        circleGasperchuk(2);
        System.out.println(circleWithStream(10));
        System.out.println(circle(10));
    }

    public static void drawACircle(int radius) {

    }


    public static String circle(int radius) {
        if (radius < 0) return "";
        if (radius == 0) return "\n";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < radius * 2 - 1; i++) {
            for (int j = 0; j < radius * 2 - 1; j++) {
                stringBuilder.append(Math.hypot(Math.abs(radius - 1 - i), Math.abs(radius - 1 - j)) < radius ? '\u2588' : ' ');
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static String circleWithStream(int radius) {
        return radius < 0 ? "" :
                IntStream.range(-radius + 1, radius)
                        .mapToObj(i -> IntStream.range(-radius + 1, radius)
                                .mapToObj(j -> Math.hypot(i, j) < radius ? "\u2588" : " ")
                                .collect(Collectors.joining()))
                        .collect(Collectors.joining("\n")) + '\n';
    }

    public static void circleGasperchuk(int radius){
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                if (x * x + y * y <= radius * radius) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
