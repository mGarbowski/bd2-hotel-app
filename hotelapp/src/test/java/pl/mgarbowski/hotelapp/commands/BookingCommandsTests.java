package pl.mgarbowski.hotelapp.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mgarbowski.hotelapp.HotelappApplication;
import pl.mgarbowski.hotelapp.domain.booking.BookingRepository;
import pl.mgarbowski.hotelapp.domain.booking.ComplaintRepository;
import pl.mgarbowski.hotelapp.domain.booking.RatingRepository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = HotelappApplication.class)
public class BookingCommandsTests {
    @Autowired
    BookingCommands bookingCommands;

    @Autowired
    CustomerCommands customerCommands;

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RatingRepository ratingRepository;

    @BeforeEach
    void clearDb() {
        String clearContent = "";
        String schemaContent = "";
        String dataContent = "";
        try {
            clearContent = new String(Files.readAllBytes(Paths.get("../database/scripts/clean-all.sql")));
            schemaContent = new String(Files.readAllBytes(Paths.get("../database/scripts/schema.sql")));
            dataContent = new String(Files.readAllBytes(Paths.get("../database/scripts/sample-data.sql")));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to read SQL files");
            System.exit(1);
        }

        customerCommands.logout();
        try (
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "admin", "admin");
            var stmt = conn.createStatement()
        ) {
            stmt.execute(clearContent);
            stmt.execute(schemaContent);
            stmt.execute(dataContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    @Test
    void testMakeBooking() {
        customerCommands.login(2);
        var response = bookingCommands.makeBooking(1, Date.valueOf("2025-04-01"), Date.valueOf("2025-04-07"), 2);
        assertEquals(
                "Booking complete",
                response
        );
    }

    @Test
    void testMakeBookingNotLoggedIn() {
        var response = bookingCommands.makeBooking(1, Date.valueOf("2025-04-01"), Date.valueOf("2025-04-07"), 2);
        assertEquals(
                "Log in to make booking",
                response
        );
    }

    @Test
    void testMakeComplaintNotLoggedIn() {
        var response = bookingCommands.makeComplaint(1, "Bad service");
        assertEquals(
                "Not logged in",
                response
        );
    }

    @Test
    void testMakeComplaintBookingNotFound() {
        customerCommands.login(1);
        var response = bookingCommands.makeComplaint(100, "Bad service");
        assertEquals(
                "Booking not found",
                response
        );
    }

    @Test
    void testMakeComplaintComplaintAlreadyExists() {
        customerCommands.login(1);
        var response = bookingCommands.makeComplaint(1, "Bad service");
        assertEquals(
                "Complaint already exists for this booking",
                response
        );
    }

    @Test
    void testMakeComplaintCorrect() {
        customerCommands.login(4);
        var response = bookingCommands.makeComplaint(8, "Bad service");
        assertEquals(
                "Complaint created",
                response
        );

        var complaint = complaintRepository.getComplaintByBooking(
                bookingRepository.findById(8).get()
        );
        assertEquals(
                "Bad service",
                complaint.get().getText()
        );
    }

    @Test
    void testAddOpinionNotLoggedIn() {
        var response = bookingCommands.addOpinion(1, 4,"Excellent");
        assertEquals(
                "Not logged in",
                response
        );
    }

}
