package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.booking.BookingService;

import java.sql.Date;


@RequiredArgsConstructor
@Command(command = "booking")
public class BookingCommands {
    private final BookingService bookingService;

    @Command(command = "makeComplaint")
    public String makeComplaint(Integer bookingId, Date date, String complaintText) {
        var booking = bookingService.getBookingById(bookingId);
        var bookingString = booking.toString();
        // log booking string
        System.out.println(bookingString);

        return "dsd";
    }
}
