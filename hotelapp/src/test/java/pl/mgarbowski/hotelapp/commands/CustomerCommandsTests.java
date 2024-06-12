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
            "Customer(id=1, name=John, surname=Doe, age=30, gender=M, email=john.doe@example.com, phoneNumber=123-456-7890, address=[1] 123 Main St, 10001, New York, United States)\n" +
                    "Customer(id=2, name=Jane, surname=Smith, age=25, gender=F, email=jane.smith@example.com, phoneNumber=987-654-3210, address=[2] 456 Park Ave, SW1A 1AA, London, United Kingdom)\n" +
                    "Customer(id=3, name=Michael, surname=Johnson, age=40, gender=M, email=michael.johnson@example.com, phoneNumber=555-123-4567, address=[3] 789 Rue de la Paix, 75001, Paris, France)\n" +
                    "Customer(id=4, name=Emily, surname=Brown, age=35, gender=F, email=emily.brown@example.com, phoneNumber=555-987-6543, address=[4] 101 FriedrichstraĂźe, 10117, Berlin, Germany)\n" +
                    "Customer(id=5, name=William, surname=Davis, age=28, gender=M, email=william.davis@example.com, phoneNumber=444-567-8901, address=[5] 456 Bay St, M5J 2T3, Toronto, Canada)",
            response
        );
    }
}
