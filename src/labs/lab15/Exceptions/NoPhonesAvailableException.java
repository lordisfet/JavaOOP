package labs.lab15.Exceptions;

public class NoPhonesAvailableException extends RuntimeException {
    public NoPhonesAvailableException(String message) {
        super(message);
    }
}
