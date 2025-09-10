package by.step.solid;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DipExample {
    public static void main(String[] args) {
        List list  = new ArrayList<>(); // это хорошо
        ArrayList arrayList = new ArrayList(); // это нарушение DIP

        list = new LinkedList();
//        arrayList = new LinkedList<>(); //ошибка компиляции
    }
}
