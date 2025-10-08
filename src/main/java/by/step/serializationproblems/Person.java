package by.step.serializationproblems;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

@Data
@AllArgsConstructor
class Person  {
//    private static final long serialVersionUID = -8593198743907875344L;
    private String name;
    private int age;
    private transient String password;
}