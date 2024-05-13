package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.ApplicationState;
import pl.mgarbowski.hotelapp.domain.booking.BookingService;

@Command(command = "booking")
@RequiredArgsConstructor
public class BookingCommands {
    private final ApplicationState applicationState;
    private final BookingService bookingService;

    @Command(command = "list")
    String getActiveBookings() {
        var activeBookings = bookingService.getActiveBookingsForCustomer(applicationState.getUser().get().getId());
        return activeBookings.toString();
    }
}
