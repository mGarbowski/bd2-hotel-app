package pl.mgarbowski.hotelapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Apartment {
    @Id
    private Integer id;
    private Integer nRooms;
    private Integer nBathrooms;
    private Integer nBeds;
    @Column(name = "max_n_people")
    private Integer maxNPeople;
    private Integer area;
    private BigDecimal pricePerDay;
    @ManyToOne(optional = false)
    private Hotel hotel;
    @ManyToOne(optional = false)
    private Currency currency;
}