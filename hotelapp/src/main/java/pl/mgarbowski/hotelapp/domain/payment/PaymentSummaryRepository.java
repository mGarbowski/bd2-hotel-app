package pl.mgarbowski.hotelapp.domain.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for {@link PaymentSummaryEntry} entities.
 * Provides methods for performing CRUD operations on PaymentSummaryEntry entities.
 */
public interface PaymentSummaryRepository extends JpaRepository<PaymentSummaryEntry, UUID> {
    /**
     * Retrieves all payment summary entries for a given booking ID.
     *
     * @param bookingId the ID of the booking
     * @return a list of payment summary entries for the booking
     */
    List<PaymentSummaryEntry> findAllByBookingId(Integer bookingId);
}
