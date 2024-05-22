package pl.mgarbowski.hotelapp.domain.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgarbowski.hotelapp.domain.booking.BookingRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentSummaryRepository paymentSummaryRepository;
    private final BookingRepository bookingRepository;

    public List<PaymentSummaryEntry> getSummaryForBooking(Integer bookingId) {
        return paymentSummaryRepository.findAllByBookingId(bookingId);
    }

    public BigDecimal getTotalBalanceForSummary(List<PaymentSummaryEntry> summary) {
        return summary.stream()
                .map(PaymentSummaryEntry::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalBalanceForBooking(Integer bookingId) {
        var summary = getSummaryForBooking(bookingId);
        return getTotalBalanceForSummary(summary);
    }

    public void makePayment(Integer bookingId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        var totalBalance = getTotalBalanceForBooking(bookingId);
        if (amount.compareTo(totalBalance.abs()) > 0) {
            throw new IllegalArgumentException("Amount cannot be greater than the remaining balance");
        }

        var booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        var payment = Payment.createPayment(amount, booking);

        paymentRepository.save(payment);
    }
}
