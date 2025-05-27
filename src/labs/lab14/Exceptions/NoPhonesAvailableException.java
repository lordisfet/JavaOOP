package labs.lab14.Exceptions;

public class NoPhonesAvailableException extends RuntimeException {
    public NoPhonesAvailableException(String message) {
        super(message);
    }
}
