package HW23.Controller;

import HW23.Model.Directory;
import HW23.Model.File;
import HW23.Model.FileSystemComponent;
import HW23.Servise.ValidateUtils;

import java.util.Scanner;

public class FileController {

    private final Scanner scanner;
    private final Directory currentDirectory;
    private File currentFile;

    public FileController(Scanner scanner, Directory currentDirectory) {
        this.scanner = scanner;
        this.currentDirectory = currentDirectory;
    }

    public void start() {
        if ( !ValidateUtils.notNullDirectory(currentDirectory) ) return;

        System.out.println("Введите название файла : ");
        String fileName = scanner.nextLine().trim();
        if ( !ValidateUtils.validateName(fileName) ) return;

        FileSystemComponent component = currentDirectory.getComponent(fileName);
        if ( component instanceof File ) {
            startWithFile((File) component);
        } else {
            System.out.println("Файл не найден");
        }
    }

    public void printMenu() {
        System.out.println("\n--- Управление файлами");
        System.out.println("1 - Удалить файл");
        System.out.println("2 - Информация о файле");
        System.out.println("3 - Переименовать файл");
        System.out.println("0 - Назад");
    }

    public void startWithFile(File file) {
        this.currentFile = file;
        printFileInfo();
        boolean running = true;

        while (running && currentFile != null) {

            printMenu();
            System.out.println("Выберите действие : ");
            String choise = scanner.nextLine().trim();

            switch (choise) {
                case "1" -> deleteFile();
                case "2" -> getSize();
                case "3" -> renameFile();
                case "0" -> running = false;
                default -> System.out.println("Неизвестная команда");
            }
        }
    }

    private void printFileInfo() {
        System.out.println("Файл : " + currentFile.getName());
        System.out.println("Путь : " + currentFile.getPath());
        System.out.println("Размер : " + currentFile.getSize() + "B");
    }

    private void deleteFile() {
        if ( !ValidateUtils.notNullDirectory(currentDirectory) || currentFile == null ) return;

        String fileName = currentFile.getName();
        boolean remove = currentDirectory.removeComponent(fileName);
        if ( remove ) {
            System.out.println("Файл удалён");
            currentFile = null;
        } else {
            System.out.println("Ошибка при удалении");
        }
    }

    private void getSize() {
        if ( currentFile == null ) {
            System.out.println("Файл не выбран");
            return;
        }
        System.out.println("Размер " + currentFile + " " + currentFile.getSize() + "B");
    }

    private void renameFile() {
        if ( !ValidateUtils.notNullDirectory(currentDirectory) || currentFile == null ) return;

        System.out.println("Введите новое название : ");
        String newName = scanner.nextLine().trim();
        if ( !ValidateUtils.validateName(newName) ) return;

        for (FileSystemComponent component : currentDirectory.getChilderns()) {
            if ( component.getName().equals(newName) && component != currentFile ) {
                System.out.println("Такой файл уже сущетсвует");
                return;
            }
        }
        currentFile.setName(newName);
        System.out.println("Файл переименован");
    }

}
