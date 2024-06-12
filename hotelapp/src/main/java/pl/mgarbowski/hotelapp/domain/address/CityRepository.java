package pl.mgarbowski.hotelapp.domain.address;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link City} entities.
 * Provides methods for performing CRUD operations on City entities.
 */
public interface CityRepository extends JpaRepository<City, Integer> {
}
