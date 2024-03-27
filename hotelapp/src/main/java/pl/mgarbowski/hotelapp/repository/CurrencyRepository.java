package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
