package HW23.Controller;

import HW23.Model.Directory;
import HW23.Model.File;
import HW23.Model.FileSystemComponent;
import HW23.Repository.FileSystemRepository;
import HW23.Servise.FileSystemUtils;
import HW23.Servise.FileSystemUtilsImpl;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class NavigationController {
    @Getter
    @Setter
    private Directory currentDirectory;
    private final Directory root;
    private final Scanner scanner;
    private final FileSystemRepository fSRepository;
    private final FileSystemUtils fileSystemUtils;

    public NavigationController(Directory root, Scanner scanner,
                                FileSystemRepository fSRepository,
                                FileSystemUtils fileSystemUtils) {
        this.root = root;
        this.currentDirectory = root;
        this.scanner = scanner;
        this.fSRepository = fSRepository;
        this.fileSystemUtils = fileSystemUtils;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printCurrentLocation();
            printMenu();
            System.out.println("Выберите команду :");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> toParentDirectory();
                case "2" -> openDirectoryController();
                case "3" -> selectComponent();
                case "4" -> searchFiles();
                case "5" -> save();
                case "0" -> {
                    running = false;
                    System.out.println("Выход из программы");
                }
                default -> {
                    System.out.println("Неизвестная команда");
                    printMenu();
                }
            }
        }
    }

    public void save() {
        try {
            fSRepository.save(root);
            System.out.println("Изменения сохранены");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения - " + e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println("Выберите действие : ");
        System.out.println("1 - Предыдущая директория ");
        System.out.println("2 - Действия в текущей директории");
        System.out.println("3 - Выбрать файл/директорию");
        System.out.println("4 - Поиск файлов по маске");
        System.out.println("5 - Сохранить");
        System.out.println("0 - Выход");
    }

    private void searchFiles() {
        FileSystemUtilsImpl fileSystemUtils = new FileSystemUtilsImpl();
        System.out.println("Введите маску : ");
        String mask = scanner.nextLine().trim();

        List<String> foundFiles = fileSystemUtils.findFilesByMask(currentDirectory, mask);
        if ( foundFiles.isEmpty() ) {
            System.out.println("Файлы по такой маске не найдены");
        } else {
            System.out.println("Найденные файлы : ");
            for (String filePath : foundFiles)
                System.out.println(" " + filePath);
        }
    }

    private void selectComponent() {
        System.out.println("Введите название файла/директории");
        String name = scanner.nextLine().trim();

        FileSystemComponent component = currentDirectory.getComponent(name);
        if ( component == null ) {
            System.out.println("Файл/директория не найдены");
            return;
        }
        if ( component instanceof Directory ) {
            currentDirectory = (Directory) component;
        } else {
            FileController fileController = new FileController(scanner, currentDirectory);
            fileController.startWithFile((File) component);
        }
    }

    private void openDirectoryController() {
        DirectoryController directoryController = new DirectoryController(scanner, currentDirectory,
                (FileSystemUtilsImpl) fileSystemUtils);
        directoryController.start();
    }

    private void toParentDirectory() {
        Directory parent = currentDirectory.getParent();
        if ( parent != null ) {
            currentDirectory = parent;
        } else {
            System.out.println("Возврат из корневой директории невозможен");
        }
    }

    private void printCurrentLocation() {
        System.out.println("\n--- Вы здесь ▽ ");

        if ( currentDirectory == null) {
            System.out.println("root >");
        } else {
            System.out.println(getPathString(currentDirectory));
        }

        if (currentDirectory != null && !currentDirectory.getChilderns().isEmpty()) {
            for (FileSystemComponent component : currentDirectory.getChilderns()) {
                String componentType = (component instanceof Directory) ? "[Directory]" : "[File]";
                System.out.println(" " + componentType + " " + component.getName());
            }
        }else{
            System.out.println("\nДирекория пуста");
        }
        System.out.println();
    }

    private String getPathString(Directory dir) {
        if ( dir == null ) {
            return "\n root > ";
        }

        StringBuilder parentPath = new StringBuilder();
        Directory current = dir;

        while(current != null){
            if ( !parentPath.isEmpty() ){
                parentPath.insert(0," > ");
            }
            parentPath.insert(0,current.getName());
            current = current.getParent();
        }
        return parentPath.toString();
    }
}
