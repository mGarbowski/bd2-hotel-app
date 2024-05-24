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
    private String name;
    private String email;
    private BigDecimal avgRating;
    private String street;
    private String zipCode;
    private String city;
    private String country;
    private Long nCustomers;
    private Integer nBookings;
    private Long nComplaints;
    private BigDecimal totalEarning;
}
