package pl.mgarbowski.hotelapp.domain.feature;

import lombok.Data;

import java.io.Serializable;

@Data
public class AvailableFeatureKey implements Serializable {
    private String featureName;
    private Integer apartmentId;
}
