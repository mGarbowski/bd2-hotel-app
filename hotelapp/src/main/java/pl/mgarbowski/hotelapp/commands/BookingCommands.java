package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.ApplicationState;
import pl.mgarbowski.hotelapp.domain.booking.BookingDTO;
import pl.mgarbowski.hotelapp.domain.booking.BookingService;

import java.util.Date;
import java.util.List;

@Command(command = "booking")
@RequiredArgsConstructor
public class BookingCommands {
    private final ApplicationState applicationState;
    private final BookingService bookingService;

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
