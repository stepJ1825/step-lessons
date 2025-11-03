package HW23.Servise;

import HW23.Model.Directory;

import java.util.List;

public interface FileSystemUtils {

    Directory getDirectoryByPath(Directory root, String path);
    void moveComponentsTo(Directory source, Directory target);
    List<String> findFilesByMask(Directory directory, String mask);
    boolean matherMask(String fileName, String mask);
    
}
