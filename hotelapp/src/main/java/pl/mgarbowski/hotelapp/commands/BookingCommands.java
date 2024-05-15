package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.booking.BookingService;
import pl.mgarbowski.hotelapp.domain.booking.ComplaintService;

import java.sql.Date;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@Command(command = "booking")
public class BookingCommands {
    private final BookingService bookingService;
    private final ComplaintService complaintService;

    @Command(command = "makeComplaint")
    public String makeComplaint(Integer bookingId, Date date, String complaintText) {
        var booking = bookingService.getBookingById(bookingId);
        if(booking.isEmpty()) {
            return "Booking not found";
        }

        try {
            complaintService.createComplaint(booking.get(), date, complaintText);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "Complaint created";
    }
}
