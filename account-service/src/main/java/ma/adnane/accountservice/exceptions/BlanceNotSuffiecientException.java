package ma.adnane.accountservice.exceptions;

public class BlanceNotSuffiecientException extends RuntimeException {
    public BlanceNotSuffiecientException(String message) {
        super(message);
    }
}
