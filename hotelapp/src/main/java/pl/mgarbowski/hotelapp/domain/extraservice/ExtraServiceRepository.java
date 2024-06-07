package pl.mgarbowski.hotelapp.domain.extraservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for {@link ExtraService} entities.
 * Provides methods for performing CRUD operations on ExtraService entities.
 */
public interface ExtraServiceRepository extends JpaRepository<ExtraService, Integer> {
    /**
     * Retrieves available services for a given booking ID.
     *
     * @param bookingId the ID of the booking
     * @return a list of available services for the booking
     */
    @Query(value = "SELECT s FROM ExtraService s " +
            "JOIN AvailableService avs ON s.id = avs.extraService.id " +
            "JOIN Hotel h on avs.hotel.id = h.id " +
            "JOIN Apartment a ON a.hotel.id = h.id " +
            "JOIN Booking b ON b.apartment.id = a.id " +
            "WHERE b.id = :bookingId")
    List<ExtraService> getAvailableForBooking(Integer bookingId);
}
