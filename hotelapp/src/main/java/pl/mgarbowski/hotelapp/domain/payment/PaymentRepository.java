package pl.mgarbowski.hotelapp.domain.payment;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Payment} entities.
 * Provides methods for performing CRUD operations on Payment entities.
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
