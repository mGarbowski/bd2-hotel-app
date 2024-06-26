package pl.mgarbowski.hotelapp.domain.extraservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.currency.Currency;

import java.math.BigDecimal;


/**
 * Represents an ExtraService entity.
 */
@Data
@Entity
public class ExtraService {
    @Id
    private Integer id;
    private String name;
    private BigDecimal price;
    @ManyToOne
    private Currency currency;
}
