package by.step.readwriteexmple;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;

public class ReadObject {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        String filename = "src/main/java/by/step/readwriteexmple/out.txt";
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object o = ois.readObject();
            if ("UserFromWrite".equals(o.getClass().getSimpleName())) {
                Field name = o.getClass().getDeclaredField("name");
                name.setAccessible(true);
                String o1 = (String) name.get(o);
                Field age = o.getClass().getDeclaredField("age");
                age.setAccessible(true);
                int o2 = (int) age.get(o);
                UserFromRead userFromRead = new UserFromRead(o1, o2);
                System.out.println(userFromRead);
            }
        }
    }

    private record UserFromRead(String name, int age) {
    }
}
