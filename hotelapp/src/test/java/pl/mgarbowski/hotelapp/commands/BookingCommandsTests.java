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

    @Autowired
    CustomerCommands customerCommands;

    @Test
    void testListNotLoggedIn() {
        var response = bookingCommands.getActiveBookings();
        assertEquals(
                "Log in to see your active bookings",
                response
        );
    }

    @Test
    void testListLoggedInOneUpcoming() {
        customerCommands.login(1);
        var response = bookingCommands.getActiveBookings();
        assertEquals(
                "Upcoming:\n" +
                        "[3] Chimponello Estate, London, 456 Park Ave for 2 people, from 2025-04-01 to 2025-04-07",
                response
        );
    }

    @Test
    void testListLoggedInNoBookings() {
        customerCommands.login(2);
        var response = bookingCommands.getActiveBookings();
        assertEquals(
                "You have no active bookings",
                response
        );
    }
}
