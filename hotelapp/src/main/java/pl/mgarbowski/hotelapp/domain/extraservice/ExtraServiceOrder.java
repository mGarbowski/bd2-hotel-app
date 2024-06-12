package pl.mgarbowski.hotelapp.domain.extraservice;

import jakarta.persistence.*;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.booking.Booking;

import java.sql.Timestamp;

/**
 * Represents an order for an extra service.
 */
@Entity
@Data
public class ExtraServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp timestamp;
    @ManyToOne
    private Booking booking;
    @OneToOne
    private AvailableService availableService;

    /**
     * Protected constructor for JPA.
     */
    protected ExtraServiceOrder() {}

    /**
     * Private constructor for creating a new ExtraServiceOrder.
     *
     * @param timestamp       the timestamp of the order
     * @param booking         the booking associated with the order
     * @param availableService the available service being ordered
     */
    private ExtraServiceOrder(Timestamp timestamp, Booking booking, AvailableService availableService) {
        this.timestamp = timestamp;
        this.booking = booking;
        this.availableService = availableService;
    }

    /**
     * Creates a new ExtraServiceOrder for a given booking and available service.
     *
     * @param booking         the booking associated with the order
     * @param availableService the available service being ordered
     * @return a new ExtraServiceOrder
     */
    static ExtraServiceOrder create(Booking booking, AvailableService availableService) {
        var now = new Timestamp(System.currentTimeMillis());
        return new ExtraServiceOrder(now, booking, availableService);
    }
}
