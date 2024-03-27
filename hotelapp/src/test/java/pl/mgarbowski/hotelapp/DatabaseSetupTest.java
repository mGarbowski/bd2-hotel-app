package pl.mgarbowski.hotelapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.repository.CurrencyRepository;
import pl.mgarbowski.hotelapp.repository.CustomerRepository;
import pl.mgarbowski.hotelapp.repository.HotelRepository;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseSetupTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    <T, I> void testFindAll(JpaRepository<T, I> repository) {
        var all = repository.findAll();
        all.forEach(System.out::println);
        assertFalse(all.isEmpty());
    }

    @Test
    void customerRepositoryTest() {
        testFindAll(currencyRepository);
    }

    @Test
    void hotelRepositoryTest() {
        testFindAll(hotelRepository);
    }

    @Test
    void currencyRepositoryTest() {
        testFindAll(currencyRepository);
    }
}
