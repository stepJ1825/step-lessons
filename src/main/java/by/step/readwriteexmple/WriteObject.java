package by.step.readwriteexmple;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class WriteObject {
    public static void main(String[] args) throws IOException {
        UserFromWrite user = new UserFromWrite("firstPerson", 11);
        String filename = "src/main/java/by/step/readwriteexmple/out.txt";
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream os = new ObjectOutputStream(fileOutputStream)) {
            os.writeObject(user);
        }
    }

    private record UserFromWrite(String name, int age)
            implements Serializable {
    }
}


