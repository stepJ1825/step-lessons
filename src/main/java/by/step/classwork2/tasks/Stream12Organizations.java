package by.step.classwork2.tasks;

import by.step.classwork2.model.Organization;

import java.util.List;
import java.util.Map;

public class Stream12Organizations {

    //    У нас есть список организаций.
    //    Внутри есть 3 поля: name, fullName и nn.
    //    Нам необходимо составить такую Map, в которой ключем будет nn, а значением будет fullName.

    public static void main(String[] args) {
        final var organizations = List.of(
                new Organization("Org1", "Organization One", "001"),
                new Organization("Org2", "Organization Two", "002"),
                new Organization("Org3", "Organization Three", "003"),
                new Organization("Org4", "Organization Four", "004"),
                new Organization("Org5", "Organization Five", "005"),
                new Organization("Org6", "Organization Six", "006"),
                new Organization("Org7", "Organization Seven", "007"),
                new Organization("Org8", "Organization Eight", "008"),
                new Organization("Org9", "Organization Nine", "009"),
                new Organization("Org10", "Organization Ten", "001")
        );

        final var map = listToMap(organizations);

        System.out.println(map);
    }

    public static Map<String, String> listToMap(List<Organization> organizations) {
        //        return organizations.stream()
        return null;
    }

    public static Map<String, List<String>> listToMap2(List<Organization> organizations) {
        //        return organizations.stream()
        return null;
    }
}