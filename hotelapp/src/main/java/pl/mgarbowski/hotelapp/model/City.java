package pl.mgarbowski.hotelapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class City {
    @Id
    private Integer id;
    private String name;
    @ManyToOne(optional = false)
    private Country country;
}
