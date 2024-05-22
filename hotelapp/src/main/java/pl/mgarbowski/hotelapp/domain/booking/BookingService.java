package pl.mgarbowski.hotelapp.domain.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentRepository;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ApartmentRepository apartmentRepository;

    public void makeBooking(Customer customer, Integer apartmentId, Date startDate, Date endDate, Integer nPeople)
            throws IncorrectDateException, ApartmentNotAvailableException, ApartmentDoesNotExistException {
        if (endDate.before(startDate)) {
            throw new IncorrectDateException("End date must be after the start date");
        }

        var now = new java.util.Date();
        if (startDate.before(now) || endDate.before(now)) {
            throw new IncorrectDateException("Booking period must be in the future");
        }

        var apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new ApartmentDoesNotExistException(String.format("Apartment with id %d does not exist", apartmentId)));
        if (!isApartmentAvailable(apartmentId, startDate, endDate)) {
            throw new ApartmentNotAvailableException("Apartment is not available in this period");
        }

        var booking = new Booking(startDate, endDate, nPeople, apartment, customer);
        bookingRepository.save(booking);
    }

    public Optional<Booking> getBookingById(Integer id) throws NoSuchElementException {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByCustomer(Customer customer) {
        return bookingRepository.findBookingsByCustomer(customer);
    }

    public List<BookingDTO> getActiveBookingsForCustomer(Integer customerId) {
        return bookingRepository.getActiveBookingsByCustomer(customerId);
    }

    private boolean isApartmentAvailable(Integer apartmentId, Date startDate, Date endDate) {
        return bookingRepository.findConflictingBookings(apartmentId, startDate, endDate).isEmpty();
    }
}
