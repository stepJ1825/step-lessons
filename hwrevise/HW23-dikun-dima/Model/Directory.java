package HW23.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Directory implements FileSystemComponent {
    private String name;
    @JsonIgnore
    private Directory parent;
    private List<FileSystemComponent> childerns;

    @JsonCreator
    public Directory(@JsonProperty("name") String name,
                     @JsonProperty("children") List<FileSystemComponent> children) {
        this.name = name;
        this.childerns = children != null ? children : new ArrayList<>();
        this.parent = null;
        this.setParents();
    }

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.childerns = new ArrayList<>();
        if (parent != null) {
            parent.addComponent(this);
        }
    }

    public Directory(String name) {
        this.name = name;
        this.parent = null;
        this.childerns = new ArrayList<>();
    }

    public void addComponent(FileSystemComponent component) {
        childerns.add(component);
        component.setParent(this);
    }

    public boolean removeComponent(String name) {
        return childerns.removeIf(component ->
                component.getName()
                        .equals(name));
    }

    public FileSystemComponent getComponent(String name) {
        return childerns.stream()
                .filter(component ->
                        component.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public int countFiles() {
        int count = 0;
        for (FileSystemComponent component : childerns) {
            if (component instanceof File) {
                count++;
            } else if (component instanceof Directory) {
                count += ((Directory) component).countFiles();
            }
        }
        return count;
    }

    public void setParents(){
        for (FileSystemComponent component : this.getChilderns()){
            component.setParent(this);
            if (component instanceof Directory){
               ((Directory) component).setParents();
            }
        }
    }

    @Override
    public String getPath() {
        if ( parent == null ) {
            return "/" + name;
        }
        return parent.getPath() + "/" + name;
    }

    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemComponent component : childerns) {
            totalSize += component.getSize();
        }
        return totalSize;
    }

}
