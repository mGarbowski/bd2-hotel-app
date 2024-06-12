package pl.mgarbowski.hotelapp.domain.extraservice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link ExtraServiceOrder} entities.
 * Provides methods for performing CRUD operations on ExtraServiceOrder entities.
 */
public interface ExtraServiceOrderRepository extends JpaRepository<ExtraServiceOrder, Integer> {
}
