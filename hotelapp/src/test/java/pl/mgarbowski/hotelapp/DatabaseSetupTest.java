package pl.mgarbowski.hotelapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mgarbowski.hotelapp.repository.CustomerRepository;
import pl.mgarbowski.hotelapp.repository.HotelRepository;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseSetupTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    HotelRepository hotelRepository;

    @Test
    void customerRepositoryTest() {
        var all = customerRepository.findAll();
        all.forEach(System.out::println);
        assertFalse(all.isEmpty());
    }

    @Test
    void hotelRepositoryTest() {
        var all = hotelRepository.findAll();
        all.forEach(System.out::println);
        assertFalse(all.isEmpty());
    }
}
