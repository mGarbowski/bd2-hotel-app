package pl.mgarbowski.hotelapp.domain.apartment;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link ApartmentStatistics} entities.
 * Provides methods for performing CRUD operations on ApartmentStatistics entities.
 */
public interface ApartmentStatisticsRepository extends JpaRepository<ApartmentStatistics, Integer> {
}
