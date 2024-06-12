package pl.mgarbowski.hotelapp.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Customer} entities.
 * Provides methods for performing CRUD operations on Customer entities.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
