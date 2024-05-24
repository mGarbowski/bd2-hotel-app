package pl.mgarbowski.hotelapp.domain.hotel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
public class HotelStatistics {
    @Id
    private Integer hotelId;
    private Long nCustomers;
    private Integer nBookings;
    private Long nComplaints;
    private BigDecimal totalEarning;
}
