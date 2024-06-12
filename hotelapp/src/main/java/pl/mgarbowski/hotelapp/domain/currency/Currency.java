package pl.mgarbowski.hotelapp.domain.currency;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a currency entity.
 */
@Data
@Entity
@AllArgsConstructor
public class Currency {
    @Id
    private String isoCode;
    private String name;

    /**
     * Protected constructor for JPA.
     */
    protected Currency() {}
}
