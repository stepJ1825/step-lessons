package by.step.printstreams;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class PrintStreamWriteExample {
    public static void main(String[] args) {
        printStreamExec();
        printWriterExec();
    }

    private static void printStreamExec() {
        try (PrintStream printStream = new PrintStream("notes3.txt")) {
            printStream.print("Hello World!");
            printStream.println("Welcome to Java!");

            printStream.printf("Name: %s Age: %d \n", "Tom", 34);

            String message = "PrintStream";
            byte[] message_toBytes = message.getBytes();
            printStream.write(message_toBytes);

            System.out.println("The file has been written");
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    private static void printWriterExec() {
        try (PrintWriter printWriter = new PrintWriter(System.out)) {
            printWriter.print("Hello World!");
            printWriter.println("Welcome to Java!");

            printWriter.printf("Name: %s Age: %d \n", "Tom", 34);

            String message = "PrintStream";
            byte[] message_toBytes = message.getBytes();
            printWriter.write(Arrays.toString(message_toBytes));

            System.out.println("The file has been written");
        }
    }
}
