package by.step.creational.prototype.robotexample;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class Robot {
    private int age;
    private String name;
    private double mass;

    public Robot(int age, String name, double mass) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.age = age;
        this.name = name;
        this.mass = mass;
    }

    public Robot clone() {
        Robot robot1 = new Robot();
        robot1.setAge(0);
        robot1.setMass(0);
        robot1.setName("default name");
        return robot1;
    }
}
