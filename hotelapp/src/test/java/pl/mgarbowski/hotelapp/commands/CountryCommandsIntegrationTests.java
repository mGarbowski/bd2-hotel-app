package pl.mgarbowski.hotelapp.commands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mgarbowski.hotelapp.HotelappApplication;
import pl.mgarbowski.hotelapp.domain.address.Country;
import pl.mgarbowski.hotelapp.domain.address.CountryRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = HotelappApplication.class)
public class CountryCommandsIntegrationTests {
    @Autowired
    CountryCommands countryCommands;
    @Autowired
    CountryRepository countryRepository;

    @Test
    void shouldDisplayCountry() {
        var country = new Country("Poland", "PL");
        countryRepository.save(country);

        var response = countryCommands.getByCode("PL");

        assertTrue(response.contains("Poland"));
    }
}
