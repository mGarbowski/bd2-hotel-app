package pl.mgarbowski.hotelapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mgarbowski.hotelapp.repository.CustomerRepository;

@SpringBootTest
public class DatabaseSetupTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void customerRepositoryTest() {
        var all = customerRepository.findAll();
        all.forEach(System.out::println);
    }
}
