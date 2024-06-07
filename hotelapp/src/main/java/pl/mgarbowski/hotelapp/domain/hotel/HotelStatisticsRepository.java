package pl.mgarbowski.hotelapp.domain.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link HotelStatistics} entities.
 * Provides methods for performing CRUD operations on HotelStatistics entities.
 */
public interface HotelStatisticsRepository extends JpaRepository<HotelStatistics, Integer> {
}
