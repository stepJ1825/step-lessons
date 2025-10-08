package by.step.serializationproblems;

import java.io.*;

public class DeserializePerson {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                "src/main/java/by/step/serializationproblems/person.ser"))) {
//            Person person = (Person) ois.readObject();
            Object person = ois.readObject();
            System.out.println("Десериализованный объект: " + person);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
