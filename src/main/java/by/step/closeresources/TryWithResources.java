package by.step.closeresources;

import java.io.FileInputStream;
import java.io.IOException;

public class TryWithResources {
    public static void main(String[] args) {
//                FileInputStream fin = new FileInputStream("notes.txt");
//                try(fin){
        try (FileInputStream fin = new FileInputStream("notes.txt")) {
            int i = -1;
            while ((i = fin.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}