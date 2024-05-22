package pl.mgarbowski.hotelapp.domain.extraservice;

import jakarta.persistence.*;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.booking.Booking;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "service_order")
public class ExtraServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp timestamp;
    @ManyToOne
    private Booking booking;
    @OneToOne
    private AvailableService availableService;

    protected ExtraServiceOrder() {}

    private ExtraServiceOrder(Timestamp timestamp, Booking booking, AvailableService availableService) {
        this.timestamp = timestamp;
        this.booking = booking;
        this.availableService = availableService;
    }

    static ExtraServiceOrder create(Booking booking, AvailableService availableService) {
        var now = new Timestamp(System.currentTimeMillis());
        return new ExtraServiceOrder(now, booking, availableService);
    }
}
