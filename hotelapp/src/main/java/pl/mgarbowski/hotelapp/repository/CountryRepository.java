package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.Country;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findByCode(String code);
}
