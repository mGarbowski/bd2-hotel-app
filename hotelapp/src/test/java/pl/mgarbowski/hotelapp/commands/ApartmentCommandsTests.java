package pl.mgarbowski.hotelapp.commands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mgarbowski.hotelapp.HotelappApplication;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = HotelappApplication.class)
public class ApartmentCommandsTests {
    @Autowired
    ApartmentCommands apartmentCommands;

    @Test
    void testSearchFreeApartmentsInCity() {
        var response = apartmentCommands.searchFreeApartmentsInCity("Toronto", Date.valueOf("2024-12-01"), Date.valueOf("2024-12-12"));
        assertEquals("    Hotel: Rabin Rooms – Apartment number [5] (0,00 stars) – Capacity: 6 person – Price per day: 300,00\n" +
                "        - Rooms: 3\n" +
                "        - Bathrooms: 2\n" +
                "        - Beds: 3\n" +
                "        - Address: 456 Bay St, M5J 2T3, Toronto, Canada \n" +
                "\n", response);
    }
}
