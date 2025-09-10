package by.step.solid.badisg;

public class Fish implements SuperInterface{
    @Override
    public void fly() {
        throw new IllegalStateException();
    }

    @Override
    public void walk() {
        throw new IllegalStateException();
    }

    @Override
    public void drive() {
        throw new IllegalStateException();
    }

    @Override
    public void swim() {
        System.out.println("Fish is swimming");
    }
}
