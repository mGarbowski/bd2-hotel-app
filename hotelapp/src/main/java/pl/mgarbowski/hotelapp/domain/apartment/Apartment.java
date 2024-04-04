package pl.mgarbowski.hotelapp.domain.apartment;

import jakarta.persistence.*;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.currency.Currency;
import pl.mgarbowski.hotelapp.domain.feature.Feature;
import pl.mgarbowski.hotelapp.domain.hotel.Hotel;

import java.math.BigDecimal;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "available_feature",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_name")
    )
    private List<Feature> features;
}
