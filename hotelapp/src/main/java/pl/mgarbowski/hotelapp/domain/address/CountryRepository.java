package pl.mgarbowski.hotelapp.domain.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for {@link Country} entities.
 * Provides methods for performing CRUD operations on Country entities.
 */
public interface CountryRepository extends JpaRepository<Country, Integer> {
    /**
     * Finds a country by its code.
     *
     * @param code the code of the country
     * @return an optional containing the country if found, or empty if not found
     */
    Optional<Country> findByCode(String code);
}
