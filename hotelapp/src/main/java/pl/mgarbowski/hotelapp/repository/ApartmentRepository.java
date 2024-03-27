package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
}
