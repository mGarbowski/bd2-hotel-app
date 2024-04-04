package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
