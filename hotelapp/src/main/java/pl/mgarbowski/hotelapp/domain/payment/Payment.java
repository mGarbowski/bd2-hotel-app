package pl.mgarbowski.hotelapp.domain.payment;

import jakarta.persistence.*;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.booking.Booking;
import pl.mgarbowski.hotelapp.domain.currency.Currency;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date timestamp;
    private BigDecimal amount;
    @ManyToOne
    private Booking booking;
    @ManyToOne // TODO make it many to one in ER diagram
    private Currency currency;
}
