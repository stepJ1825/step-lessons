package by.step.filesanddirectories;

import java.io.File;

public class DirManipulations {
    public static void main(String[] args) {
        // определяем объект для каталога
        File dir = new File(
                "src/main/java/by/step/filesanddirectories/new-folder");
        boolean created = dir.mkdir();
        if (created) {
            System.out.println("Folder has been created");
        }
        // переименуем каталог
        File newDir = new File(
                "src/main/java/by/step/filesanddirectories/new-folder-renamed");
        dir.renameTo(newDir);
//        // удалим каталог
//        boolean deleted = newDir.delete();
//        if (deleted) {
//            System.out.println("Folder has been deleted");
//        }
    }
}
