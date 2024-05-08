package pl.mgarbowski.hotelapp.domain.apartment;

public class InvalidBookingDateException extends Exception {
    public InvalidBookingDateException(String message) {
        super(message);
    }
}
