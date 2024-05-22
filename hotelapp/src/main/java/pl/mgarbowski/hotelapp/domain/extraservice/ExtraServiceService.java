package pl.mgarbowski.hotelapp.domain.extraservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgarbowski.hotelapp.domain.booking.Booking;
import pl.mgarbowski.hotelapp.domain.booking.BookingRepository;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExtraServiceService {
    private final ExtraServiceRepository extraServiceRepository;
    private final AvailableServiceRepository availableServiceRepository;
    private final ExtraServiceOrderRepository extraServiceOrderRepository;
    private final BookingRepository bookingRepository;

    public List<ExtraService> getAvailableForBooking(Integer bookingId, Customer customer) throws InvalidBookingException {
        var booking = getBookingBelongingToCustomer(customer, bookingId);
        return extraServiceRepository.getAvailableForBooking(booking.getId());
    }

    public Optional<ExtraService> getAvailableByName(String name, Integer bookingId, Customer user) throws InvalidBookingException {
        return getAvailableForBooking(bookingId, user)
                .stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst();
    }

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

    private Booking getBookingBelongingToCustomer(Customer customer, Integer bookingId) throws InvalidBookingException {
        var errorMessage = String.format("You do not have a booking with id %d", bookingId);
        var maybeBooking = bookingRepository.findById(bookingId);
        if (maybeBooking.isEmpty()) {
            throw new InvalidBookingException(errorMessage);
        }

        var queryUserId = customer.getId();
        var actualUserId = maybeBooking.get().getId();
        if (!queryUserId.equals(actualUserId)) {
            throw new InvalidBookingException(errorMessage);
        }

        return maybeBooking.get();
    }
}
