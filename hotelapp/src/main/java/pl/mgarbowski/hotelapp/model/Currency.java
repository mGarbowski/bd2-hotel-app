package pl.mgarbowski.hotelapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Currency {
    @Id
    private String isoCode;
    private String name;
}
