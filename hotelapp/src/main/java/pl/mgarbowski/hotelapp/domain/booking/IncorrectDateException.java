package pl.mgarbowski.hotelapp.domain.booking;

/**
 * Exception thrown when a booking date is incorrect.
 */
public class IncorrectDateException extends Exception {
    /**
     * Constructs a new IncorrectDateException with the specified detail message.
     *
     * @param message the detail message
     */
    public IncorrectDateException(String message) {
        super(message);
    }
}
