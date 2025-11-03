package HW23.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

        @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
        @JsonSubTypes({
        @JsonSubTypes.Type(value = File.class, name = "file"),
        @JsonSubTypes.Type(value = Directory.class, name = "directory")
})
public interface FileSystemComponent {
    String getName();

    void setName(String newName);

    @JsonIgnore
    Directory getParent();

    void setParent(Directory parent);

    String getPath();

    long getSize();
}
