package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.ApplicationState;
import pl.mgarbowski.hotelapp.domain.booking.*;
import pl.mgarbowski.hotelapp.domain.customer.CustomerService;

import java.sql.Date;
import java.util.List;

/**
 * Shell commands for managing bookings.
 */
@RequiredArgsConstructor
@Command(command = "booking", group = "booking")
public class BookingCommands {
    private final ApplicationState applicationState;
    private final BookingService bookingService;
    private final ComplaintService complaintService;
    private final CustomerService customerService;
    private final RatingService ratingService;

    /**
     * Makes a booking for an apartment.
     *
     * @param apartmentId the ID of the apartment
     * @param startDate   the start date of the booking
     * @param endDate     the end date of the booking
     * @param nPeople     the number of people for the booking
     * @return a message indicating the booking status
     */
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

    /**
     * Makes a complaint about a booking.
     *
     * @param bookingId     the ID of the booking
     * @param complaintText the text of the complaint
     * @return a message indicating the complaint status
     */
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

    /**
     * Adds an opinion to a booking.
     *
     * @param bookingId   the ID of the booking
     * @param starRating  the star rating
     * @param opinionText the text of the opinion
     * @return a message indicating the opinion status
     */
    @Command(command = "addOpinion", description = "Add an opinion to a booking")
    public String addOpinion(Integer bookingId, Integer starRating, String opinionText) {
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

    /**
     * Lists the current user's active bookings.
     *
     * @return a formatted string of the user's active bookings
     */
    @Command(command = "list", description = "List current user's active bookings")
    String getActiveBookings() {
        var user = applicationState.getUser();
        if (user.isEmpty()) {
            return "Log in to see your active bookings";
        }

        var activeBookings = bookingService.getActiveBookingsForCustomer(user.get().getId());
        return formatMessage(activeBookings);
    }

    /**
     * Formats a list of bookings into a string.
     *
     * @param bookings the list of bookings
     * @return a formatted string of the bookings
     */
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

    /**
     * Formats multiple bookings into a string.
     *
     * @param bookings the list of bookings
     * @return a formatted string of the bookings
     */
    private static String formatMultipleBookings(List<Booking> bookings) {
        return String.join("\n", bookings.stream().map(BookingCommands::formatSingleBooking).toList());
    }

    /**
     * Formats a single booking into a string.
     *
     * @param booking the booking to format
     * @return a formatted string of the booking
     */
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
