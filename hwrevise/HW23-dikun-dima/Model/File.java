package HW23.Model;

import HW23.Servise.ValidateUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

public class File implements FileSystemComponent {

    @Getter
    private String name;

    @JsonIgnore
    @Setter
    @Getter
    private Directory parent;

    @Setter
    @Getter
    private long size;

    @JsonCreator
    public File(@JsonProperty("name") String name,
                @JsonProperty("size") long size,
                @JsonProperty("path") String path) {
        this.name = name;
        this.size = size;
        this.parent = null;
    }

    public File(String name, Directory parent, long size) {
        if (!ValidateUtils.validateName(name)) throw new IllegalArgumentException("Имя файла не должно быть пустым");
        String uncorrectEnd = ".,!?;:/$%@^&*()_-+=<>[]{}";
        char lastChar = name.charAt(name.length()-1);
        if (uncorrectEnd.contains(String.valueOf(lastChar))){
            throw new IllegalArgumentException("Назвение расширения не может заканчиваться на символ "+lastChar);
        }
        if (!name.contains(".")){
            throw new IllegalArgumentException("Введите расширение файла");
        }
        this.name = name;
        this.size = size;
        if ( parent != null ) {
            parent.addComponent(this);
        }
    }

    @Override
    public void setName(String newName) {
        if (!ValidateUtils.validateName(newName)) throw new IllegalArgumentException("Имя файла не должно быть пустым");
        String uncorrectEnd = ".,!?;:/$%@^&*()_-+=<>[]{}";
        char lastChar = newName.charAt(newName.length()-1);
        if (uncorrectEnd.contains(String.valueOf(lastChar))){
            throw new IllegalArgumentException("Назвение расширения не может заканчиваться на символ "+lastChar);
        }
        if (!newName.contains(".")){
            throw new IllegalArgumentException("Введите расширение файла");
        }
        this.name = newName;
    }

    @Override
    public String getPath() {
        if (parent == null){
            return "/"+name;
        }
        return parent.getPath()+"/"+name;
    }

}
