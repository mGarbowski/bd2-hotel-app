package pl.mgarbowski.hotelapp.domain.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    public List<BookingDTO> getActiveBookingsForCustomer(Integer customerId) {
        return bookingRepository.getActiveBookingsByCustomer(customerId);
    }
}
