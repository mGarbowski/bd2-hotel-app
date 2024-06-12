package pl.mgarbowski.hotelapp.domain.feature;

import jakarta.persistence.*;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.apartment.Apartment;


/**
 * Represents an AvailableFeature entity.
 */
@Entity
@IdClass(AvailableFeatureKey.class)
@Data
public class AvailableFeature {
    @Id
    @Column(name = "apartment_id")
    private Integer apartmentId;
    @Id
    @Column(name = "feature_name")
    private String featureName;
    @OneToOne
    private Feature feature;
    @ManyToOne
    private Apartment apartment;
}
