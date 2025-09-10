package by.step.behavioral.command.simple;

public class Print2Command implements MyCommand {
    @Override
    public void exec() {
        System.out.println("Print 2 Command");
    }
}
