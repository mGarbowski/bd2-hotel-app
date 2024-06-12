package pl.mgarbowski.hotelapp.domain.booking;

/**
 * Exception thrown when an apartment is not available.
 */
public class ApartmentNotAvailableException extends Exception {
    /**
     * Constructs a new ApartmentNotAvailableException with the specified detail message.
     *
     * @param message the detail message
     */
    public ApartmentNotAvailableException(String message) {
        super(message);
    }
}
