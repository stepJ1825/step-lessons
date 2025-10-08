package by.step.bufferedreadwrite;

import java.io.*;

public class BufferedReaderExample {
    public static void main(String[] args) {
        readByChars();
        readByLines();
        //        readFromConsole();
    }

    private static void readByLines() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("notes4.txt"))) {
            //чтение построчно
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void readByChars() {
        try (BufferedReader br = new BufferedReader(new FileReader("notes4.txt"))) {
            // чтение посимвольно
            int c;
            while ((c = br.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void readFromConsole() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new FileWriter("notes5.txt"))) {
            // чтение построчно
            String text;
            while (!(text = br.readLine()).equals("ESC")) {

                bw.write(text + "\n");
                bw.flush();
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
