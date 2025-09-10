package by.step.behavioral.command.simple;

public class Print1Command implements MyCommand {
    @Override
    public void exec() {
        System.out.println("Print 1 Command");
    }
}
