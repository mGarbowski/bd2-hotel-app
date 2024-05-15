package pl.mgarbowski.hotelapp.domain.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findBookingsByCustomer(Customer customer);
}
