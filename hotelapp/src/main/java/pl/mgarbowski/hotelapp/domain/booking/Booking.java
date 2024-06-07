package pl.mgarbowski.hotelapp.domain.booking;

import jakarta.persistence.*;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.apartment.Apartment;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.sql.Date;

/**
 * Represents a booking entity.
 */
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

    /**
     * Protected constructor for JPA.
     */
    protected Booking() {}

    /**
     * Constructs a new Booking with the specified details.
     *
     * @param startDate the start date of the booking
     * @param endDate   the end date of the booking
     * @param nPeople   the number of people for the booking
     * @param apartment the apartment being booked
     * @param customer  the customer making the booking
     */
    Booking(Date startDate, Date endDate, Integer nPeople, Apartment apartment, Customer customer) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.nPeople = nPeople;
        this.apartment = apartment;
        this.customer = customer;
    }
}
