package pl.mgarbowski.hotelapp.commands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mgarbowski.hotelapp.HotelappApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = HotelappApplication.class)
public class CustomerCommandsTests {
    @Autowired
    CustomerCommands customerCommands;

    @Test
    void testWhoNotLoggedIn() {
        var response = customerCommands.who();
        assertEquals(
            "You are not currently logged in",
            response
        );
    }

    @Test
    void testWhoLoggedIn() {
        var response = customerCommands.login(1);
        assertEquals(
            "Welcome John Doe!",
            response
        );
    }

    @Test
    void testLoginCustomerNotFound() {
        var response = customerCommands.login(100);
        assertEquals(
            "Customer with id [100] not found",
            response
        );
    }
}
