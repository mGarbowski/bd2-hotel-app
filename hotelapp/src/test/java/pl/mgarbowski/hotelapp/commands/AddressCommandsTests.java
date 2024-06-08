package pl.mgarbowski.hotelapp.commands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mgarbowski.hotelapp.HotelappApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = HotelappApplication.class)
public class AddressCommandsTests {
    @Autowired
    AddressCommands addressCommands;

    @Test
    void testListAll() {
        var response = addressCommands.listAll();
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println(response);
        System.out.println("===================================");
        System.out.println("===================================");
        assertEquals("[1] 123 Main St, 10001, New York, United States\n" +
                "[2] 456 Park Ave, SW1A 1AA, London, United Kingdom\n" +
                "[3] 789 Rue de la Paix, 75001, Paris, France\n" +
                "[4] 101 Friedrichstra??e, 10117, Berlin, Germany\n" +
                "[5] 456 Bay St, M5J 2T3, Toronto, Canada", response);
    }
}
