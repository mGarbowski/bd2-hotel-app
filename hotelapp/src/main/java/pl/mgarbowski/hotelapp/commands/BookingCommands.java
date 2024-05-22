package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.ApplicationState;
import pl.mgarbowski.hotelapp.domain.booking.BookingDTO;
import pl.mgarbowski.hotelapp.domain.booking.BookingService;
import pl.mgarbowski.hotelapp.domain.booking.ComplaintService;
import pl.mgarbowski.hotelapp.domain.booking.RatingService;
import pl.mgarbowski.hotelapp.domain.customer.CustomerService;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Command(command = "booking")
public class BookingCommands {
    private final ApplicationState applicationState;
    private final BookingService bookingService;
    private final ComplaintService complaintService;
    private final CustomerService customerService;
    private final RatingService ratingService;

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

    @Command(command = "addOpinion", description = "Add an opinion to a booking")
    public String addOpinion(Integer bookingId, Integer starRating, String opinionText) {
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
            ratingService.createRating(booking.get(), starRating, opinionText);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "Opinion added";
    }

    @Command(command = "list", description = "List current user's active bookings")
    String getActiveBookings() {
        var user = applicationState.getUser();
        if (user.isEmpty()) {
            return "Log in to see your active bookings";
        }

        var activeBookings = bookingService.getActiveBookingsForCustomer(user.get().getId());
        return formatMessage(activeBookings);
    }

    private String formatMessage(List<BookingDTO> bookings) {
        if (bookings.isEmpty()) {
            return "You have no active bookings";
        }

        var present = bookings.stream()
                .filter(b -> b.start().before(new Date()))
                .toList();
        var future = bookings.stream()
                .filter(b -> b.start().after(new Date()))
                .toList();

        String message = "";
        if (!present.isEmpty()) {
            message += "Present:\n";
            message += formatMultipleBookings(present);
            message += "\n";
        }
        if (!future.isEmpty()) {
            message += "Upcoming:\n";
            message += formatMultipleBookings(future);
        }
        return message;
    }

    private static String formatMultipleBookings(List<BookingDTO> bookings) {
        return String.join("\n", bookings.stream().map(BookingCommands::formatSingleBooking).toList());
    }

    private static String formatSingleBooking(BookingDTO booking) {
        return String.format(
                "[%d] %s, %s for %d people, from %s to %s",
                booking.bookingId(),
                booking.city(),
                booking.street(),
                booking.nPeople(),
                booking.start().toString(),
                booking.end().toString()
        );
    }
}
