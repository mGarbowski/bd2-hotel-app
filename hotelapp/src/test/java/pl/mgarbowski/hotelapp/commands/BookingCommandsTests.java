package pl.mgarbowski.hotelapp.commands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mgarbowski.hotelapp.HotelappApplication;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = HotelappApplication.class)
public class BookingCommandsTests {
    @Autowired
    BookingCommands bookingCommands;


    @Test
    void testListNotLoggedIn() {
        var response = bookingCommands.getActiveBookings();
        assertEquals(
                "Log in to see your active bookings",
                response
        );
    }

}
