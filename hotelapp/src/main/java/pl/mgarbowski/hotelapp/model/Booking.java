package pl.mgarbowski.hotelapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date startDate;
    private Date endDate;
    private Integer nPeople;

    @ManyToOne
    private Apartment apartment;

    @ManyToOne
    private Customer customer;
}
