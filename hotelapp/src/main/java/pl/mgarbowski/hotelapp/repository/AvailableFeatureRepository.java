package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.AvailableFeature;
import pl.mgarbowski.hotelapp.model.AvailableFeatureKey;

public interface AvailableFeatureRepository extends JpaRepository<AvailableFeature, AvailableFeatureKey> {
}
