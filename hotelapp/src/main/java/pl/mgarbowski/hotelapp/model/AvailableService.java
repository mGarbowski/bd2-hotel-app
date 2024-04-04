package pl.mgarbowski.hotelapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(AvailableServiceKey.class)
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
    private Hotel hotel;
}
