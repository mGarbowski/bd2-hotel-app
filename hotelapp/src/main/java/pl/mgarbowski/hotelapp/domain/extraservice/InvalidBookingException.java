package pl.mgarbowski.hotelapp.domain.extraservice;

public class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}
