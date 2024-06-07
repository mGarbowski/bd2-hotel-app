package pl.mgarbowski.hotelapp.domain.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Represents a city entity.
 */
@Data
@Entity
public class City {
    @Id
    private Integer id;
    private String name;
    @ManyToOne(optional = false)
    private Country country;
}
