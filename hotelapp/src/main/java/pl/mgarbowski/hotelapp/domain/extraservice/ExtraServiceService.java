package pl.mgarbowski.hotelapp.domain.extraservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgarbowski.hotelapp.domain.booking.Booking;
import pl.mgarbowski.hotelapp.domain.booking.BookingRepository;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service class for managing extra services.
 */
@Service
@RequiredArgsConstructor
public class ExtraServiceService {
    private final ExtraServiceRepository extraServiceRepository;
    private final AvailableServiceRepository availableServiceRepository;
    private final ExtraServiceOrderRepository extraServiceOrderRepository;
    private final BookingRepository bookingRepository;

    /**
     * Retrieves available extra services for a given booking and customer.
     *
     * @param bookingId the ID of the booking
     * @param customer  the customer
     * @return a list of available extra services
     * @throws InvalidBookingException if the booking does not belong to the customer
     */
    public List<ExtraService> getAvailableForBooking(Integer bookingId, Customer customer) throws InvalidBookingException {
        var booking = getBookingBelongingToCustomer(customer, bookingId);
        return extraServiceRepository.getAvailableForBooking(booking.getId());
    }

    /**
     * Retrieves an available extra service by name for a given booking and customer.
     *
     * @param name      the name of the extra service
     * @param bookingId the ID of the booking
     * @param user      the customer
     * @return an optional containing the extra service if found, or empty if not found
     * @throws InvalidBookingException if the booking does not belong to the customer
     */
    public Optional<ExtraService> getAvailableByName(String name, Integer bookingId, Customer user) throws InvalidBookingException {
        return getAvailableForBooking(bookingId, user)
                .stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * Orders an extra service for a given booking and customer.
     *
     * @param extraName the name of the extra service
     * @param bookingId the ID of the booking
     * @param user      the customer
     * @throws InvalidBookingException      if the booking does not belong to the customer
     * @throws ServiceNotAvailableException if the service is not available for the booking
     * @throws NoSuchElementException       if the service is not found
     */
    public void order(String extraName, Integer bookingId, Customer user)
            throws InvalidBookingException, ServiceNotAvailableException, NoSuchElementException {
        var extra = getAvailableByName(extraName, bookingId, user)
                .orElseThrow(() -> new ServiceNotAvailableException(extraName));
        var booking = getBookingBelongingToCustomer(user, bookingId);
        var availableService = availableServiceRepository
                .getForServiceAndBooking(extra.getId(), bookingId)
                .orElseThrow();
        var order = ExtraServiceOrder.create(booking, availableService);
        extraServiceOrderRepository.save(order);
    }

    /**
     * Retrieves a booking belonging to a specific customer.
     *
     * @param customer  the customer
     * @param bookingId the ID of the booking
     * @return the booking if found
     * @throws InvalidBookingException if the booking does not belong to the customer
     */
    private Booking getBookingBelongingToCustomer(Customer customer, Integer bookingId) throws InvalidBookingException {
        var errorMessage = String.format("You do not have a booking with id %d", bookingId);
        var maybeBooking = bookingRepository.findById(bookingId);
        if (maybeBooking.isEmpty()) {
            throw new InvalidBookingException(errorMessage);
        }

        var queryUserId = customer.getId();
        var actualUserId = maybeBooking.get().getCustomer().getId();
        if (!queryUserId.equals(actualUserId)) {
            throw new InvalidBookingException(errorMessage);
        }

        return maybeBooking.get();
    }
}
