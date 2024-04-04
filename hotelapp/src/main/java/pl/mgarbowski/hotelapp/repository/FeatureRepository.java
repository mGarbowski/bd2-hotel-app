package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.Feature;

public interface FeatureRepository extends JpaRepository<Feature, String> {
}
