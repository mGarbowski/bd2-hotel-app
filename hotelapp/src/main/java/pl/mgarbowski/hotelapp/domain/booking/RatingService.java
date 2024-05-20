package pl.mgarbowski.hotelapp.domain.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;

    public void createRating(Booking booking,Integer starRating, String ratingText) throws IllegalArgumentException {
        if(ratingRepository.getRatingByBooking(booking).isPresent()) {
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
