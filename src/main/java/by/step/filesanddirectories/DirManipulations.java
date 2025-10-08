package by.step.filesanddirectories;

import java.io.File;

public class DirManipulations {
    public static void main(String[] args) {

        // определяем объект для каталога
        File dir = new File("C://SomeDir//NewDir");
        boolean created = dir.mkdir();
        if (created) {
            System.out.println("Folder has been created");
        }
        // переименуем каталог
        File newDir = new File("C://SomeDir//NewDirRenamed");
        dir.renameTo(newDir);
        // удалим каталог
        boolean deleted = newDir.delete();
        if (deleted) {
            System.out.println("Folder has been deleted");
        }
    }
}
