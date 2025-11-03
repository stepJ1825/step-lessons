package HW23.Controller;

import HW23.Model.Directory;
import HW23.Model.File;
import HW23.Model.FileSystemComponent;
import HW23.Servise.FileSystemUtilsImpl;
import HW23.Servise.ValidateUtils;

import java.util.Scanner;

public class DirectoryController {

    private final Scanner scanner;
    private final Directory currentDirectory;
    private final FileSystemUtilsImpl fileSystemUtils;

    public DirectoryController(Scanner scanner, Directory currentDirectory,FileSystemUtilsImpl fileSystemUtils) {
        this.scanner = scanner;
        this.currentDirectory = currentDirectory;
        this.fileSystemUtils = fileSystemUtils;
    }

    public void start() {

        boolean running = true;
        while (running) {

            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> createDirectory();
                case "2" -> createFile();
                case "3" -> showStructure();
                case "4" -> deleteComponent();
                case "5" -> countFiles();
                case "6" -> getSize();
                case "7" -> moveComponents();
                case "8" -> getPath();
                case "0" -> running = false;
                default -> System.out.println("Неизвестная команда");
            }
        }
    }

    public void printMenu() {
        System.out.println("\n--- Управление директорией " + currentDirectory);
        System.out.println("1 - Добавить директорию");
        System.out.println("2 - Добавить файл");
        System.out.println("3 - Показать структуру директории");
        System.out.println("4 - Удалить компонент");
        System.out.println("5 - Посчитать количество файлов");
        System.out.println("6 - Узнать размер директории");
        System.out.println("7 - Переместить файлы в другую директорию");
        System.out.println("8 - Узнать путь директории");
        System.out.println("0 - Назад");
    }

    private void createDirectory() {
        if (!ValidateUtils.notNullDirectory(currentDirectory)) return;
        System.out.println("Введите название новой директории");
        String name = scanner.nextLine().trim();

        for (FileSystemComponent component : currentDirectory.getChilderns()) {
            if ( component.getName().equals(name) ) {
                System.out.println("Директория с таким названием уже существует в этой ветке");
                return;
            }
        }
        Directory newDir = new Directory(name, currentDirectory);
        currentDirectory.getChilderns().add(newDir);
        System.out.println("Директория создана");
    }

    private void createFile() {
        if (!ValidateUtils.notNullDirectory(currentDirectory)) return;

        System.out.println("Введите название файла и расширение : ");
        String name = scanner.nextLine().trim();
        if (!ValidateUtils.validateName(name)) return;

        for (FileSystemComponent component : currentDirectory.getChilderns()) {
            if (component.getName().equals(name) ) {
                System.out.println("Файл с таким названием уже существует");
                return;
            }
        }

        System.out.println("Введите размер файла : ");
        try {
            long size = Long.parseLong(scanner.nextLine().trim());
            File newFile = new File(name, currentDirectory, size);
            System.out.println("Файл создан");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат размера");
        }
    }

    private void deleteComponent() {
        if (!ValidateUtils.notNullDirectory(currentDirectory)) return;

        System.out.println("Введите название компонента для удаления : ");
        String name = scanner.nextLine().trim();
        if (!ValidateUtils.validateName(name)) return;

        boolean remove = currentDirectory.removeComponent(name);
        if (remove) {
            System.out.println("Компонент удалён");
        } else {
            System.out.println("Компонент не найден");
        }
    }

    private void countFiles() {
        if (!ValidateUtils.notNullDirectory(currentDirectory)) return;

        int count = currentDirectory.countFiles();
        System.out.println("Файлов в директории : " + count);
    }

    private void getSize() {
        if (!ValidateUtils.notNullDirectory(currentDirectory)) return;

        long size = currentDirectory.getSize();
        System.out.println("Размер директории : " + size + "B");
    }

    private void moveComponents() {
        if (!ValidateUtils.notNullDirectory(currentDirectory)) return;

        System.out.println("Введите путь для перемещения : ");
        String targetPath = scanner.nextLine().trim();
        if (!ValidateUtils.validatePath(targetPath)) return;

        Directory targetDir = fileSystemUtils.getDirectoryByPath(currentDirectory,targetPath);
        try {
            fileSystemUtils.moveComponentsTo(currentDirectory,targetDir);
            System.out.println("Компоненты перемещены");
        } catch (Exception e) {
            System.out.println("Директория не найдена");
        }
    }

    private void printStructure(Directory directory, int level) {
        if (!ValidateUtils.notNullDirectory(currentDirectory)) return;

        String levelSpace = "-".repeat(level);
        for (FileSystemComponent component : directory.getChilderns()) {
            if (component instanceof Directory) {
                System.out.println(levelSpace + "[Directory]" + component.getName());
                printStructure((Directory) component, (level + 1));
            } else {
                System.out.println(levelSpace + "[File]" + component.getName() +
                        component.getSize() + "B");
            }
        }
    }

    private void showStructure() {
        if (!ValidateUtils.notNullDirectory(currentDirectory)) return;

        System.out.println("\nСтруктура директории " + currentDirectory.getName() + " :");
        printStructure(currentDirectory,0);
    }

    private void getPath(){
        if (!ValidateUtils.notNullDirectory(currentDirectory)) return;

        System.out.println("Путь этой директории : " +currentDirectory.getPath());
    }

}
