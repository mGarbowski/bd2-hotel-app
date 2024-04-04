package pl.mgarbowski.hotelapp.model;

import jakarta.persistence.*;
import lombok.Data;

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
