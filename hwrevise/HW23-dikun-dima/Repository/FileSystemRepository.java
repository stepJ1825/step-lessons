package HW23.Repository;

import HW23.Model.Directory;
import HW23.Model.FileSystemComponent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class FileSystemRepository {

    private final String filePath;
    private final ObjectMapper mapper;


    public FileSystemRepository(String filePath) {
        this.filePath = filePath;
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.mapper.registerModule(new JavaTimeModule());

        this.mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
    }

    public Directory load() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Файл не существует, создана новая система");
            return new Directory("root");
        }

        try {
            Directory root = mapper.readValue(file, Directory.class);
            setParents(root);
            return root;
        } catch (Exception e){
            System.out.println("Ошибка при загрузке : " + e.getMessage());
            System.out.println("Создана новая система");
            return new Directory("root");
            }
    }

    public void save(Directory root) throws IOException {
       File file = new File(filePath);
       file.getParentFile().mkdirs();
       mapper.writeValue(file,root);
    }

    private void setParents (Directory directory) {
        for (FileSystemComponent component : directory.getChilderns()) {
            component.setParent(directory);
            if (component instanceof Directory) {
                setParents((Directory) component);
            }
        }
    }
}
