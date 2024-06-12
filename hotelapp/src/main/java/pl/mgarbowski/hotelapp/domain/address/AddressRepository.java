package pl.mgarbowski.hotelapp.domain.address;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Address} entities.
 * Provides methods for performing CRUD operations on Address entities.
 */
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
