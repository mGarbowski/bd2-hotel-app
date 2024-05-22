package pl.mgarbowski.hotelapp.domain.booking;

import jakarta.persistence.*;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.apartment.Apartment;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

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

    protected Booking() {}

    Booking(Date startDate, Date endDate, Integer nPeople, Apartment apartment, Customer customer) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.nPeople = nPeople;
        this.apartment = apartment;
        this.customer = customer;
    }
}
