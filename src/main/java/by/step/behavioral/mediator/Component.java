package by.step.behavioral.mediator;

/**
 * Общий интерфейс компонентов.
 */
public interface Component {
    void setMediator(Mediator mediator);
    String getName();
}