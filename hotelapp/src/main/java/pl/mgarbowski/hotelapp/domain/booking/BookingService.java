package pl.mgarbowski.hotelapp.domain.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentRepository;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service class for managing bookings.
 */
@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ApartmentRepository apartmentRepository;

    /**
     * Makes a booking for a customer.
     *
     * @param customer    the customer making the booking
     * @param apartmentId the ID of the apartment
     * @param startDate   the start date of the booking
     * @param endDate     the end date of the booking
     * @param nPeople     the number of people for the booking
     * @throws IncorrectDateException          if the end date is before the start date or the booking period is in the past
     * @throws ApartmentNotAvailableException  if the apartment is not available in the specified period
     * @throws ApartmentDoesNotExistException  if the apartment does not exist
     */
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

    /**
     * Retrieves a booking by its ID.
     *
     * @param id the ID of the booking
     * @return an optional containing the booking if found, or empty if not found
     * @throws NoSuchElementException if the booking is not found
     */
    public Optional<Booking> getBookingById(Integer id) throws NoSuchElementException {
        return bookingRepository.findById(id);
    }

    /**
     * Retrieves bookings for a specific customer.
     *
     * @param customer the customer
     * @return a list of bookings for the customer
     */
    public List<Booking> getBookingsByCustomer(Customer customer) {
        return bookingRepository.findBookingsByCustomer(customer);
    }

    /**
     * Retrieves active bookings for a specific customer.
     *
     * @param customerId the ID of the customer
     * @return a list of active bookings for the customer
     */
    public List<Booking> getActiveBookingsForCustomer(Integer customerId) {
        return bookingRepository.getActiveBookingsByCustomer(customerId);
    }

    /**
     * Checks if an apartment is available in a given date range.
     *
     * @param apartmentId the ID of the apartment
     * @param startDate   the start date of the booking
     * @param endDate     the end date of the booking
     * @return true if the apartment is available, false otherwise
     */
    private boolean isApartmentAvailable(Integer apartmentId, Date startDate, Date endDate) {
        return bookingRepository.findConflictingBookings(apartmentId, startDate, endDate).isEmpty();
    }
}
