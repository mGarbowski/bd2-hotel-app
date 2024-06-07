package pl.mgarbowski.hotelapp.domain.apartment;

/**
 * Exception thrown when a booking date is invalid.
 */
public class InvalidBookingDateException extends Exception {
    /**
     * Constructs a new InvalidBookingDateException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidBookingDateException(String message) {
        super(message);
    }
}
