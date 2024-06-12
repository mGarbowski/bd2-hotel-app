package pl.mgarbowski.hotelapp.domain.booking;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

/**
 * Represents a complaint entity.
 */
@Entity
@Data
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date timestamp;
    private String text;
    @OneToOne
    private Booking booking;
}
