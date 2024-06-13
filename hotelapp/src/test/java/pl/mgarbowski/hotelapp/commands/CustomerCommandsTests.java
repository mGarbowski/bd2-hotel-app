package pl.mgarbowski.hotelapp.commands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mgarbowski.hotelapp.HotelappApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        customerCommands.login(1);
        var response = customerCommands.who();
        assertEquals(
                "[1] John Doe",
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

    @Test
    void testLoginCorrect() {
        var response = customerCommands.login(1);
        assertEquals(
                "Welcome John Doe!",
                response
        );
    }

    @Test
    void testLogoutCorrect() {
        customerCommands.login(1);
        customerCommands.logout();
        var response = customerCommands.who();
        assertEquals(
                "You are not currently logged in",
                response
        );
    }

    @Test
    void testListAll() {
        var response = customerCommands.list();
        assertEquals(
                "[1] John Doe\n" +
                        "[2] Jane Smith\n" +
                        "[3] Michael Johnson\n" +
                        "[4] Emily Brown\n" +
                        "[5] William Davis", response
        );
    }
}
