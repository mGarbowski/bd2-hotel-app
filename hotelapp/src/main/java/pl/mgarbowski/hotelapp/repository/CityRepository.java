package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.City;

public interface CityRepository extends JpaRepository<City, Integer> {
}
