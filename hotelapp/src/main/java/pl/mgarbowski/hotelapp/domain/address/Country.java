package pl.mgarbowski.hotelapp.domain.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class Country {
    @Id
    private Integer id;
    private String name;
    private String code;
}
