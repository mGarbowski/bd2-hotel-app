package pl.mgarbowski.hotelapp.domain.booking;

public class ApartmentNotAvailableException extends Exception {
    public ApartmentNotAvailableException(String message) {
        super(message);
    }
}
