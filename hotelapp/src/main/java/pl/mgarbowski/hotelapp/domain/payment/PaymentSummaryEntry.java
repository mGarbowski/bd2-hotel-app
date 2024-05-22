package pl.mgarbowski.hotelapp.domain.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Table(name = "payments_summary")
public class PaymentSummaryEntry {
    @Id
    private UUID id;
    private Integer bookingId;
    private String name;
    private BigDecimal amount;
}
