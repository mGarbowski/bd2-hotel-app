package pl.mgarbowski.hotelapp.domain.feature;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableFeatureRepository extends JpaRepository<AvailableFeature, AvailableFeatureKey> {
}
