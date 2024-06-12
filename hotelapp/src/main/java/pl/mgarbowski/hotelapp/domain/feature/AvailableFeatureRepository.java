package pl.mgarbowski.hotelapp.domain.feature;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link AvailableFeature} entities.
 * Provides methods for performing CRUD operations on AvailableFeature entities.
 */
public interface AvailableFeatureRepository extends JpaRepository<AvailableFeature, AvailableFeatureKey> {
}
