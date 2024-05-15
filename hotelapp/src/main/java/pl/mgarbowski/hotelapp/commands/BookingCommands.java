package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.booking.BookingService;
import pl.mgarbowski.hotelapp.domain.booking.ComplaintService;
import pl.mgarbowski.hotelapp.domain.customer.CustomerService;


@RequiredArgsConstructor
@Command(command = "booking")
public class BookingCommands {
    private final BookingService bookingService;
    private final ComplaintService complaintService;
    private final CustomerService customerService;

    @Command(command = "makeComplaint", description = "Make a complaint about a booking")
    public String makeComplaint(Integer bookingId, String complaintText) {
        var customer = customerService.getLoggedIn();
        if(customer.isEmpty()) {
            return "Not logged in";
        }
        var booking = bookingService.getBookingsByCustomer(customer.get())
                .stream()
                .filter(b -> b.getId().equals(bookingId))
                .findFirst();
        if(booking.isEmpty()) {
            return "Booking not found";
        }
        try {
            complaintService.createComplaint(booking.get(), complaintText);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "Complaint created";
    }
}
