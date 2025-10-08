package by.step.serialization;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
class Person implements Serializable {
    private String name;
    private int age;
    private double height;
    private boolean married;
    //    private transient String password;
}