package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.ApplicationState;
import pl.mgarbowski.hotelapp.domain.booking.*;
import pl.mgarbowski.hotelapp.domain.customer.CustomerService;

import java.sql.Date;
import java.util.List;


@RequiredArgsConstructor
@Command(command = "booking", group = "booking")
public class BookingCommands {
    private final ApplicationState applicationState;
    private final BookingService bookingService;
    private final ComplaintService complaintService;
    private final CustomerService customerService;
    private final RatingService ratingService;

    @Command(command = "make", description = "Book an apartment")
    public String makeBooking(Integer apartmentId, Date startDate, Date endDate, Integer nPeople) {
        var user = applicationState.getUser();
        if (user.isEmpty()) {
            return "Log in to make booking";
        }

        try {
            bookingService.makeBooking(user.get(), apartmentId, startDate, endDate, nPeople);
            return "Booking complete";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Command(command = "makeComplaint", description = "Make a complaint about a booking")
    public String makeComplaint(Integer bookingId, String complaintText) {
        var customer = customerService.getLoggedIn();
        if (customer.isEmpty()) {
            return "Not logged in";
        }
        var booking = bookingService.getBookingsByCustomer(customer.get())
                .stream()
                .filter(b -> b.getId().equals(bookingId))
                .findFirst();
        if (booking.isEmpty()) {
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
    public String addOpinion(Integer bookingId, Integer starRating, String opinionText) { // TODO refactor code duplication
        var customer = customerService.getLoggedIn();
        if (customer.isEmpty()) {
            return "Not logged in";
        }
        var booking = bookingService.getBookingsByCustomer(customer.get())
                .stream()
                .filter(b -> b.getId().equals(bookingId))
                .findFirst();
        if (booking.isEmpty()) {
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

    private String formatMessage(List<Booking> bookings) {
        if (bookings.isEmpty()) {
            return "You have no active bookings";
        }
        var present = bookings.stream()
                .filter(b -> b.getStartDate().before(new java.util.Date()))
                .toList();
        var future = bookings.stream()
                .filter(b -> b.getStartDate().after(new java.util.Date()))
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

    private static String formatMultipleBookings(List<Booking> bookings) {
        return String.join("\n", bookings.stream().map(BookingCommands::formatSingleBooking).toList());
    }

    private static String formatSingleBooking(Booking booking) {
        var address = booking.getApartment().getHotel().getAddress();
        return String.format(
                "[%d] %s, %s, %s for %d people, from %s to %s",
                booking.getId(),
                booking.getApartment().getHotel().getName(),
                address.getCity().getName(),
                address.getStreet(),
                booking.getNPeople(),
                booking.getStartDate().toString(),
                booking.getEndDate().toString()
        );
    }
}
