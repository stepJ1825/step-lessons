package by.step.bufferedreadwrite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExample {
    public static void main(String[] args) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("notes4.txt"))) {
            String text = "Hello  World!\nHey! Teachers! Leave the kids alone.";
            bw.write(text);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
