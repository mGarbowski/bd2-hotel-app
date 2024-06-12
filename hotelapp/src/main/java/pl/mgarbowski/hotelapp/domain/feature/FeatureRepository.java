package pl.mgarbowski.hotelapp.domain.feature;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Feature} entities.
 * Provides methods for performing CRUD operations on Feature entities.
 */
public interface FeatureRepository extends JpaRepository<Feature, String> {
}
