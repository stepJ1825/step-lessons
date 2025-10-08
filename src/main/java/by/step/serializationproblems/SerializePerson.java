//package by.step.serializationproblems;
//
//import java.io.*;
//
//public class SerializePerson {
//    public static void main(String[] args) {
//        Person person = new Person("Alice", 30);
//
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
//                "src/main/java/by/step/serializationproblems/person.ser"))) {
//            oos.writeObject(person);
//            System.out.println("Объект сериализован и сохранён в person.ser");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
