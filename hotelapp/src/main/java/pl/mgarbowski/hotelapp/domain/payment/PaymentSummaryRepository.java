package pl.mgarbowski.hotelapp.domain.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentSummaryRepository extends JpaRepository<PaymentSummaryEntry, UUID> {
    List<PaymentSummaryEntry> findAllByBookingId(Integer bookingId);
}
