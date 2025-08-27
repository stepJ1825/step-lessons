package by.step.classwork2.model;

import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
//@Setter(AccessLevel.PRIVATE)
//@EqualsAndHashCode(of = {"fullName"})
//@ToString(exclude = {"fullName","nn"})
public class Organization {
    private String name;
    private String fullName;
    private String nn;
}
