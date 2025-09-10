package by.step.solid.goodisg;

public class Human implements Driveable,Walkable,Swimable {


    @Override
    public void walk() {
        System.out.println("Human is walking");
    }

    @Override
    public void drive() {
        System.out.println("Human is driving");

    }

    @Override
    public void swim() {
        System.out.println("Human is swimming");
    }
}
