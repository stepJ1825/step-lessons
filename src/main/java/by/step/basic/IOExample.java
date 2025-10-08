package by.step.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOExample {
    public static void main(String[] args) {
        try (FileInputStream fin = new FileInputStream("src/main/java/by/step/basic/notes.txt");
             FileOutputStream fos = new FileOutputStream("src/main/java/by/step/basic/notes_new.txt")) {
            byte[] buffer = new byte[256];
            int count;
            // считываем буфер
            while ((count = fin.read(buffer)) != -1) {
                // записываем из буфера в файл
                fos.write(buffer, 0, count);
            }
            System.out.println("File has been written");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
