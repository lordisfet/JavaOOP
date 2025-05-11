package labs.lab9_11.Exceptions;

public class NoPhonesAvailableException extends RuntimeException {
    public NoPhonesAvailableException(String message) {
        super(message);
    }
}
