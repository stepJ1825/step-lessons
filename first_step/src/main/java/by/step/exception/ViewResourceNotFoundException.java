package by.step.exception;

public class ViewResourceNotFoundException extends RuntimeException{
    public ViewResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
