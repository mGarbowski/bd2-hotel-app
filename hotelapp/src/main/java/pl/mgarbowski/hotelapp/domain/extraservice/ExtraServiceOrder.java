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
    private Integer id;
    private Timestamp timestamp;
    @ManyToOne
    private Booking booking;
    @OneToOne
    private AvailableService availableService;
}
