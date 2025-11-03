package HW23;

import HW23.Controller.NavigationController;
import HW23.Model.Directory;
import HW23.Repository.FileSystemRepository;
import HW23.Servise.FileSystemUtils;
import HW23.Servise.FileSystemUtilsImpl;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String path = "src/main/java/HW23/Repository/FileSystem.json";
        FileSystemRepository fileSystemRepository = new FileSystemRepository(path);
        FileSystemUtils fileSystemUtils = new FileSystemUtilsImpl();

        try (Scanner scanner = new Scanner(System.in)) {
            Directory root = fileSystemRepository.load();
            NavigationController navigationController = new NavigationController(root, scanner,
                    fileSystemRepository, fileSystemUtils);
            navigationController.start();
        } catch (Exception e) {
            System.out.println("Ошибка : " + e.getMessage());
        }
    }
}
