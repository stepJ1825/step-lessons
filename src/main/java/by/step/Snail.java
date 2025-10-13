package by.step;

public class Snail {
    public static int snail(int column, int day, int night) {
//        int height = 0;
//        int result = 0;
//        while (height < column) {
//            height = height + day;
//            result++;
//            if (height < column) {
//                height -= night;
//            }
//        }
//        return result;

        if (day >= column) {
            return 1;
        }

        int currentHeight = 0;
        int days = 0;

        while (currentHeight < column) {
            days++;
            currentHeight += day;

            if (currentHeight >= column) {
                break;
            }
            currentHeight -= night;
        }
        return days;
    }


}
