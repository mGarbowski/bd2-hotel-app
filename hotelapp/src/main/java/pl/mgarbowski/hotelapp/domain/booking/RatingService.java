package pl.mgarbowski.hotelapp.domain.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Service class for managing ratings.
 */
@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;

    /**
     * Creates a rating for a given booking.
     *
     * @param booking     the booking associated with the rating
     * @param starRating  the star rating (1-5)
     * @param ratingText  the text of the rating
     * @throws IllegalArgumentException if a rating already exists for the booking or the star rating is out of range
     */
    public void createRating(Booking booking, Integer starRating, String ratingText) throws IllegalArgumentException {
        if (ratingRepository.getRatingByBooking(booking).isPresent()) {
            throw new IllegalArgumentException("Rating already exists for this booking");
        }
        if (starRating < 1 || starRating > 5) {
            throw new IllegalArgumentException("Star rating must be between 1 and 5");
        }
        var rating = new Rating();
        rating.setBooking(booking);
        rating.setText(ratingText);
        rating.setStarRating(starRating);
        rating.setTimestamp(new Date(System.currentTimeMillis()));
        ratingRepository.save(rating);
    }
}
