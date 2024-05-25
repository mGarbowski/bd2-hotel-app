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
    @ManyToOne
    private Currency currency;


    public static Payment createPayment(BigDecimal amount, Booking booking) {
        return new Payment(
                new Date(System.currentTimeMillis()),
                amount,
                booking,
                new Currency("GBP", "British Pound")
        );
    }


    private Payment(Date timestamp, BigDecimal amount, Booking booking, Currency currency) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.booking = booking;
        this.currency = currency;
    }

    protected Payment() {
    }
}
