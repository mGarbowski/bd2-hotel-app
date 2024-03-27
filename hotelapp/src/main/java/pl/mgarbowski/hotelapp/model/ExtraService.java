package pl.mgarbowski.hotelapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "services")
public class ExtraService {
    @Id
    private Integer id;
    private String name;
    private BigDecimal price;
    @ManyToOne
    private Currency currency;
}
