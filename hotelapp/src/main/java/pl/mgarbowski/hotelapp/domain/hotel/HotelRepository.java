package pl.mgarbowski.hotelapp.domain.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Hotel} entities.
 * Provides methods for performing CRUD operations on Hotel entities.
 */
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
