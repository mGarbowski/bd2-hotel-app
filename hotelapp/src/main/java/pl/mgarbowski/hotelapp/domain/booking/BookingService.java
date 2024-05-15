package pl.mgarbowski.hotelapp.domain.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    public Optional<Booking> getBookingById(Integer id) throws NoSuchElementException {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByCustomer(Customer customer) {
        return bookingRepository.findBookingsByCustomer(customer);
    }

}
