package pl.mgarbowski.hotelapp.domain.booking;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

/**
 * Represents a rating entity.
 */
@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date timestamp;
    private Integer starRating;
    private String text;
    @OneToOne
    private Booking booking;
}
