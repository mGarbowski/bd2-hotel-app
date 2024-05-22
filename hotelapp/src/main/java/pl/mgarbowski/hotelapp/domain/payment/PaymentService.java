package pl.mgarbowski.hotelapp.domain.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentSummaryRepository paymentSummaryRepository;

    public List<PaymentSummaryEntry> getSummaryForBooking(Integer bookingId) {
        return paymentSummaryRepository.findAllByBookingId(bookingId);
    }
}
