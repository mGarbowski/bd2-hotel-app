package pl.mgarbowski.hotelapp.domain.feature;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


/**
 * Represents a Feature entity.
 */
@Entity
@Data
public class Feature {
    @Id
    private String name;
}
