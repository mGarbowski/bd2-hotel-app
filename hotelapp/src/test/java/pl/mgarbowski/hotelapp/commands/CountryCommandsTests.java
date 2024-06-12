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


}
