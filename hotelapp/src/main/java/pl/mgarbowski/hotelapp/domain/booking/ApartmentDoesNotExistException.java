package pl.mgarbowski.hotelapp.domain.booking;

public class ApartmentDoesNotExistException extends Exception {
    public ApartmentDoesNotExistException(String message) {
        super(message);
    }
}
