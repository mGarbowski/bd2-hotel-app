package pl.mgarbowski.hotelapp.domain.booking;

public class IncorrectDateException extends Exception {
    public IncorrectDateException(String message) {
        super(message);
    }
}
