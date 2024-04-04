package pl.mgarbowski.hotelapp.domain.extraservice;

import jakarta.persistence.*;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.hotel.Hotel;

@Entity
@Data
@IdClass(AvailableServiceKey.class)
@Table(name = "available_service")
public class AvailableService {
    @Id
    @Column(name = "services_id")
    private Integer servicesId;
    @Id
    @Column(name = "hotel_id")
    private Integer hotelId;
    @ManyToOne
    @JoinColumn(name = "services_id")
    private ExtraService extraService;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
