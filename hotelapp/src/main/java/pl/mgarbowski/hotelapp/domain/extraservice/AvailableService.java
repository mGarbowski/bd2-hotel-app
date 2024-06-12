package pl.mgarbowski.hotelapp.domain.extraservice;

import jakarta.persistence.*;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.hotel.Hotel;

/**
 * Represents an AvailableService entity.
 */
@Entity
@Data
@IdClass(AvailableServiceKey.class)
public class AvailableService {
    @Id
    @Column(name = "extra_service_id")
    private Integer extraServiceId;
    @Id
    @Column(name = "hotel_id")
    private Integer hotelId;
    @ManyToOne
    @JoinColumn(name = "extra_service_id")
    private ExtraService extraService;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
