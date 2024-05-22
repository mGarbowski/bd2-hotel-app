package pl.mgarbowski.hotelapp.domain.extraservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AvailableServiceRepository extends JpaRepository<AvailableService, AvailableServiceKey> {

    @Query(value = "SELECT a FROM AvailableService a " +
            "JOIN ExtraService s ON s.id = a.extraService.id " +
            "JOIN Hotel h ON a.hotel.id = h.id " +
            "JOIN Apartment apt ON apt.hotel.id = h.id " +
            "JOIN Booking b ON b.apartment.id = apt.id " +
            "WHERE s.id = :serviceId AND b.id = :bookingId")
    Optional<AvailableService> getForServiceAndBooking(Integer serviceId, Integer bookingId);
}
