package pl.mgarbowski.hotelapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

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
