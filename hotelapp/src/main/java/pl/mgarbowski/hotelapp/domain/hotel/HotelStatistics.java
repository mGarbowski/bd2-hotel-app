package pl.mgarbowski.hotelapp.domain.hotel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Table(name = "hotel_statistics")
@Getter
public class HotelStatistics {
    @Id
    private Integer hotel_id;
    private Long n_customers;
    private Integer n_bookings;
    private Long n_complaints;
    private BigDecimal total_earning;
}
