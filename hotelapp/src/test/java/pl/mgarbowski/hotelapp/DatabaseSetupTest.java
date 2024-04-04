package pl.mgarbowski.hotelapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentRepository;
import pl.mgarbowski.hotelapp.domain.booking.BookingRepository;
import pl.mgarbowski.hotelapp.domain.booking.ComplaintRepository;
import pl.mgarbowski.hotelapp.domain.booking.RatingRepository;
import pl.mgarbowski.hotelapp.domain.currency.CurrencyRepository;
import pl.mgarbowski.hotelapp.domain.customer.CustomerRepository;
import pl.mgarbowski.hotelapp.domain.extraservice.AvailableServiceRepository;
import pl.mgarbowski.hotelapp.domain.extraservice.ExtraServiceOrderRepository;
import pl.mgarbowski.hotelapp.domain.extraservice.ExtraServiceRepository;
import pl.mgarbowski.hotelapp.domain.feature.FeatureRepository;
import pl.mgarbowski.hotelapp.domain.hotel.HotelRepository;
import pl.mgarbowski.hotelapp.domain.payment.PaymentRepository;

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
    @Autowired
    ComplaintRepository complaintRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    FeatureRepository featureRepository;
    @Autowired
    AvailableServiceRepository availableServiceRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ExtraServiceOrderRepository extraServiceOrderRepository;

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


    @Test
    void availableExtraServiceRepositoryTest() {
        testFindAll(availableServiceRepository);
    }

    @Test
    void paymentRepositoryTest() {
        testFindAll(paymentRepository);
    }

    @Test
    void extraServiceOrderRepositoryTest() {
        testFindAll(extraServiceOrderRepository);
    }
}
