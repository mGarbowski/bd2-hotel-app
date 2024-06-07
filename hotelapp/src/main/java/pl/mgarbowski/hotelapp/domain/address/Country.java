package pl.mgarbowski.hotelapp.domain.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Represents a country entity.
 */
@Entity
@Data
public class Country {
    @Id
    private Integer id;
    private String name;
    private String code;
}
