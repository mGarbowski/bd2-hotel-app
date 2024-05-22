package pl.mgarbowski.hotelapp.domain.apartment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
public class ApartmentStatistics {
    @Id
    private Integer apartmentId;
    private Long nCustomers;
    private Long nBookings;
    private Long nComplaints;
    private BigDecimal totalEarning;
    private BigDecimal avgEarning;
}
