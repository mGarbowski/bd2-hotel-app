package pl.mgarbowski.hotelapp.domain.extraservice;

/**
 * Exception thrown when a booking is invalid.
 */
public class InvalidBookingException extends Exception {
    /**
     * Constructs a new InvalidBookingException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidBookingException(String message) {
        super(message);
    }
}
