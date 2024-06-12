package pl.mgarbowski.hotelapp.domain.booking;

/**
 * Exception thrown when an apartment does not exist.
 */
public class ApartmentDoesNotExistException extends Exception {
    /**
     * Constructs a new ApartmentDoesNotExistException with the specified detail message.
     *
     * @param message the detail message
     */
    public ApartmentDoesNotExistException(String message) {
        super(message);
    }
}
