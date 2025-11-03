package HW23.Servise;

import HW23.Model.Directory;

public class ValidateUtils {

    public static boolean notNullDirectory(Directory directory) {
        if ( directory == null ) {
            System.out.println("Текущая директория не определена");
            return false;
        }
        return true;
    }

    public static boolean validateName(String name) {
        if ( name == null || name.trim().isEmpty() ) {
            System.out.println("Название не должно быть пустым");
            return false;
        }
        return true;
    }

    public static boolean validatePath(String path){
        if (path == null || path.trim().isEmpty() ){
            System.out.println("Путь не может быть пустым");
            return false;
        } return true;
    }
}
