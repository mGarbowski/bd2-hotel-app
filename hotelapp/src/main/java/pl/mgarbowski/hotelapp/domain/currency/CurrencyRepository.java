package pl.mgarbowski.hotelapp.domain.currency;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Currency} entities.
 * Provides methods for performing CRUD operations on Currency entities.
 */
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
