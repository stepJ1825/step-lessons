package by.step.serializationproblems;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String age;    //на втором этапе убрать это поле

}