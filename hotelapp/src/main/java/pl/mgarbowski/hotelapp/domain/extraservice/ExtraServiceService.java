package pl.mgarbowski.hotelapp.domain.extraservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgarbowski.hotelapp.domain.booking.BookingRepository;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtraServiceService {
    private final ExtraServiceRepository extraServiceRepository;
    private final AvailableServiceRepository availableServiceRepository;
    private final ExtraServiceOrderRepository extraServiceOrderRepository;
    private final BookingRepository bookingRepository;

    public List<ExtraService> getAvailableForBooking(Integer bookingId, Customer customer) throws InvalidBookingException {
        var errorMessage = String.format("You do not have a booking with id %d", bookingId);
        var booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InvalidBookingException(errorMessage));

        if (!booking.getCustomer().getId().equals(customer.getId())) {
            throw new InvalidBookingException(errorMessage);
        }

        return extraServiceRepository.getAvailableForBooking(bookingId);
    }
}
