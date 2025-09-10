package by.step.solid.badisg;

public class Human implements SuperInterface {
    @Override
    public void fly() {
        throw new IllegalStateException();
    }

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
