package by.step.filesanddirectories;

import java.io.File;
import java.io.IOException;

public class FileExample {

    public static void main(String[] args) {
        // определяем объект для каталога
        File myFile = new File("C://SomeDir//notes.txt");
        System.out.println("File name: " + myFile.getName());
        System.out.println("Parent folder: " + myFile.getParent());
        if (myFile.exists()) {
            System.out.println("File exists");
        } else {
            System.out.println("File not found");
        }

        System.out.println("File size: " + myFile.length());
        if (myFile.canRead()) {
            System.out.println("File can be read");
        } else {
            System.out.println("File can not be read");
        }

        if (myFile.canWrite()) {
            System.out.println("File can be written");
        } else {
            System.out.println("File can not be written");
        }

        // создадим новый файл
        File newFile = new File("C://SomeDir//MyFile");
        try {
            boolean created = newFile.createNewFile();
            if (created) {
                System.out.println("File has been created");
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
