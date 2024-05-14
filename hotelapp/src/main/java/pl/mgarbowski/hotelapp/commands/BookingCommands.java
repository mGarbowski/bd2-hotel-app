package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.ApplicationState;
import pl.mgarbowski.hotelapp.domain.booking.BookingService;

import java.sql.Date;

@RequiredArgsConstructor
@Command(command = "booking")
public class BookingCommands {
    private final ApplicationState applicationState;
    private final BookingService bookingService;

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
}
