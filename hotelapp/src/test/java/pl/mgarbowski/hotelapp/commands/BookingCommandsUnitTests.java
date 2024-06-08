package pl.mgarbowski.hotelapp.commands;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.mgarbowski.hotelapp.ApplicationState;
import pl.mgarbowski.hotelapp.HotelappApplication;
import pl.mgarbowski.hotelapp.domain.booking.BookingService;
import pl.mgarbowski.hotelapp.domain.booking.ComplaintService;
import pl.mgarbowski.hotelapp.domain.booking.RatingService;
import pl.mgarbowski.hotelapp.domain.customer.CustomerService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = HotelappApplication.class)
public class BookingCommandsUnitTests {
    @Autowired BookingCommands bookingCommands;
    @MockBean ApplicationState applicationState;
    @MockBean BookingService bookingService;
    @MockBean ComplaintService complaintService;
    @MockBean CustomerService customerService;
    @MockBean RatingService ratingService;

    @Test
    void shouldDisplayMessageWhenUserNotLoggedInOnGetActiveBookings() {
        Mockito.doReturn(Optional.empty()).when(applicationState).getUser();

        var response = bookingCommands.getActiveBookings();

        assertEquals("Log in to see your active bookings", response);
    }
}
