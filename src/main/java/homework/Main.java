package homework;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String absolutePath = "E:\\idea_projects\\step-lessons\\src\\main\\resources\\new_text_file.txt";
        String pathFromContentRoot = "src/main/resources/new_text_file.txt";
        String pathFromSourceRoute = "new_text_file.txt";

        try(FileReader reader = new FileReader(pathFromSourceRoute))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){

                System.out.print((char)c);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
