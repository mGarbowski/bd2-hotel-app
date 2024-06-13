package pl.mgarbowski.hotelapp.commands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mgarbowski.hotelapp.HotelappApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = HotelappApplication.class)
public class CountryCommandsTests {
    @Autowired
    CountryCommands countryCommands;

    @Test
    void testListAll() {
        var response = countryCommands.listAll();
        assertEquals("[United States, United Kingdom, France, Germany, Canada, Poland]", response.toString());
    }

    @Test
    void testGetByCodeExists() {
        var responseUS = countryCommands.getByCode("US");
        assertEquals("United States", responseUS);
        var responseUK = countryCommands.getByCode("UK");
        assertEquals("United Kingdom", responseUK);
    }

    @Test
    void testGetByCodeNotExists() {
        var response = countryCommands.getByCode("XX");
        assertEquals("Not found", response);
    }
}
