package pl.mgarbowski.hotelapp.domain.feature;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, String> {
}
