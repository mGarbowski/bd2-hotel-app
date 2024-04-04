package pl.mgarbowski.hotelapp.domain.booking;

import jakarta.persistence.*;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.booking.Booking;

import java.sql.Date;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date timestamp;  //TODO decide on appropriate name and type
    private Integer starRating;
    private String text;
    @OneToOne
    private Booking booking;
}
