package pl.mgarbowski.hotelapp.domain.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelStatisticsRepository extends JpaRepository<HotelStatistics, Integer> {
}
