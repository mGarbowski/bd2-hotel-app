package pl.mgarbowski.hotelapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.repository.*;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class DatabaseSetupTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ApartmentRepository apartmentRepository;
    @Autowired
    ExtraServiceRepository extraServiceRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired ComplaintRepository complaintRepository;
    @Autowired RatingRepository ratingRepository;
    @Autowired FeatureRepository featureRepository;

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

    @Test
    void apartmentRepositoryTest() {
        testFindAll(apartmentRepository);
    }

    @Test
    void extraServiceRepositoryTest() {
        testFindAll(extraServiceRepository);
    }

    @Test
    void bookingRepositoryTest() {
        testFindAll(bookingRepository);
    }

    @Test
    void complaintRepositoryTest() {
        testFindAll(complaintRepository);
    }

    @Test
    void ratingRepositoryTest() {
        testFindAll(ratingRepository);
    }

    @Test
    void featureRepositoryTest() {
        testFindAll(featureRepository);
    }
}
