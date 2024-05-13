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
        try {
            var booking = bookingService.getBookingById(bookingId);
            complaintService.createComplaint(booking, date, complaintText);
        } catch (NoSuchElementException e) {
            return "Booking not found";
        }
        return "Complaint created";
    }
}
