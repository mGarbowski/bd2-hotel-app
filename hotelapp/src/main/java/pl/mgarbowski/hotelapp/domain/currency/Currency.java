package pl.mgarbowski.hotelapp.domain.currency;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Currency {
    @Id
    private String isoCode;
    private String name;

    protected Currency() {}
}
