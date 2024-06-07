package pl.mgarbowski.hotelapp.domain.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for {@link Rating} entities.
 * Provides methods for performing CRUD operations on Rating entities.
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    /**
     * Retrieves a rating by its associated booking.
     *
     * @param booking the booking associated with the rating
     * @return an optional containing the rating if found, or empty if not found
     */
    Optional<Rating> getRatingByBooking(Booking booking);
}
