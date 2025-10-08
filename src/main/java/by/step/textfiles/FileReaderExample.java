package by.step.textfiles;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileReaderExample {
    public static void main(String[] args) {
        readByChars();
        readByBuffer();
    }

    private static void readByBuffer() {
        try (FileReader reader = new FileReader("notes3.txt")) {
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {

                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    private static void readByChars() {
        try (FileReader reader = new FileReader("notes3.txt")) {
            // читаем посимвольно
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
