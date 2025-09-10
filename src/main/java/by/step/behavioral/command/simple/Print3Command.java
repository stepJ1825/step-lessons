package by.step.behavioral.command.simple;

public class Print3Command implements MyCommand {
    @Override
    public void exec() {
        System.out.println("Print 3 Command");
    }
}
