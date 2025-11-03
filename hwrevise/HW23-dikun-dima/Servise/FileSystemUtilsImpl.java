package HW23.Servise;

import HW23.Model.Directory;
import HW23.Model.File;
import HW23.Model.FileSystemComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileSystemUtilsImpl implements FileSystemUtils {

    @Override
    public Directory getDirectoryByPath(Directory root, String path) {
        if (!ValidateUtils.notNullDirectory(root) || !ValidateUtils.validatePath(path)) {
            return null;
        }

        if (path.equals("/") || path.equals(root.getName())) {
            return root;
        }

        String normalizedPath = path.startsWith("/") ? path.substring(1) : path;
        normalizedPath = normalizedPath.endsWith("/") ?
                normalizedPath.substring(0, normalizedPath.length() - 1) : normalizedPath;

        String[] pathParts = normalizedPath.split("/");

        Directory currentDir = root;
        for (String part : pathParts) {
            if (part.isEmpty()) continue;

            FileSystemComponent component = currentDir.getComponent(part);
            if ( !(component instanceof Directory) ) {
                return null;
            }
            currentDir = (Directory) component;
        }
        return currentDir;
    }

    @Override
    public void moveComponentsTo(Directory source, Directory target) {
        if ( !ValidateUtils.notNullDirectory(source) || !ValidateUtils.notNullDirectory(target) ) {
            return;
        }

        List<FileSystemComponent> componentsToMove = new ArrayList<>(source.getChilderns());

        for (FileSystemComponent component : componentsToMove) {
            boolean removed = source.removeComponent(component.getName());
            if (removed) {
                target.addComponent(component);
                component.setParent(target);
            }
        }
    }

    @Override
    public List<String> findFilesByMask(Directory directory, String mask) {
        if (!ValidateUtils.notNullDirectory(directory) || !ValidateUtils.validateName(mask)){
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        findFilesRecursive(directory, mask, result, "");
        return result;
    }

    private void findFilesRecursive(Directory directory,
                                    String mask,
                                    List<String> result,
                                    String currentPath) {

        for (FileSystemComponent component : directory.getChilderns()) {
            if ( component instanceof File file ) {
                if (matherMask(file.getName(), mask)) {
                    String filePath = currentPath.isEmpty() ?
                            file.getName() : currentPath + "/" + file.getName();
                    result.add(filePath);
                }
            } else if ( component instanceof Directory subDir ) {
                String newPath = currentPath.isEmpty() ?
                        subDir.getName() : currentPath + "/" + subDir.getName();
                findFilesRecursive(subDir, mask, result, newPath);
            }
        }
    }


    public boolean matherMask(String fileName, String mask) {
        if ( !ValidateUtils.validateName(fileName) || !ValidateUtils.validateName(mask) ) {
            return false;
        }

        String regex = mask.replace(".", "\\.").replace("*", ".*");
        return fileName.matches(regex);
    }
}
