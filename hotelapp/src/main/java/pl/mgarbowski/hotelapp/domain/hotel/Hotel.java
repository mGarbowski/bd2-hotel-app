package pl.mgarbowski.hotelapp.domain.hotel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.address.Address;

import java.math.BigDecimal;

/**
 * Represents a Hotel entity.
 */
@Data
@Entity
public class Hotel {
    @Id
    private Integer id;
    private String name;
    private String phoneNumber;
    private String email;
    private Integer stars;
    @OneToOne
    private Address address;
    private Integer totalBookings;
    private BigDecimal avgRating;
}
