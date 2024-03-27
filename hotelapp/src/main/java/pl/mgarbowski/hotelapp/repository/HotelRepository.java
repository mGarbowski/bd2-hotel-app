package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
