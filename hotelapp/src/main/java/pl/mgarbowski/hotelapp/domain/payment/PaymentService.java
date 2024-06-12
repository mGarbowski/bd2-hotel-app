package pl.mgarbowski.hotelapp.domain.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgarbowski.hotelapp.domain.booking.BookingRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service class for managing payments.
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentSummaryRepository paymentSummaryRepository;
    private final BookingRepository bookingRepository;

    /**
     * Retrieves the payment summary for a given booking.
     *
     * @param bookingId the ID of the booking
     * @return a list of payment summary entries
     */
    public List<PaymentSummaryEntry> getSummaryForBooking(Integer bookingId) {
        return paymentSummaryRepository.findAllByBookingId(bookingId);
    }

    /**
     * Calculates the total balance from a list of payment summary entries.
     *
     * @param summary the list of payment summary entries
     * @return the total balance
     */
    public BigDecimal getTotalBalanceForSummary(List<PaymentSummaryEntry> summary) {
        return summary.stream()
                .map(PaymentSummaryEntry::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Retrieves the total balance for a given booking.
     *
     * @param bookingId the ID of the booking
     * @return the total balance for the booking
     */
    public BigDecimal getTotalBalanceForBooking(Integer bookingId) {
        var summary = getSummaryForBooking(bookingId);
        return getTotalBalanceForSummary(summary);
    }

    /**
     * Makes a payment for a given booking.
     *
     * @param bookingId the ID of the booking
     * @param amount    the amount to be paid
     * @throws IllegalArgumentException if the amount is not greater than zero or exceeds the remaining balance
     */
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
