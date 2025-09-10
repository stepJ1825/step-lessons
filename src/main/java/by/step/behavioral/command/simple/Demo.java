package by.step.behavioral.command.simple;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        MyCommand myCommand = null;

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        switch (i) {
            case 1 -> myCommand = new Print1Command();
            case 2 -> myCommand = new Print2Command();
            case 3 -> myCommand = new Print3Command();
        }

        myCommand.exec();
    }
}
