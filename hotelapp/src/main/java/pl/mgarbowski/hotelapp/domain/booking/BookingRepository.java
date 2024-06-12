package pl.mgarbowski.hotelapp.domain.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.sql.Date;
import java.util.List;

/**
 * Repository interface for {@link Booking} entities.
 * Provides methods for performing CRUD operations on Booking entities.
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    /**
     * Finds bookings that conflict with a given date range for a specific apartment.
     *
     * @param apartmentId the ID of the apartment
     * @param start       the start date of the booking
     * @param end         the end date of the booking
     * @return a list of conflicting bookings
     */
    @Query(value = "SELECT * FROM get_conflicting_bookings(:apartmentId, :start, :end)", nativeQuery = true)
    List<Booking> findConflictingBookings(@Param("apartmentId") Integer apartmentId, @Param("start") Date start, @Param("end") Date end);

    /**
     * Finds bookings for a specific customer.
     *
     * @param customer the customer
     * @return a list of bookings for the customer
     */
    List<Booking> findBookingsByCustomer(Customer customer);

    /**
     * Finds active bookings for a specific customer.
     *
     * @param customerId the ID of the customer
     * @return a list of active bookings for the customer
     */
    @Query(value = "SELECT b FROM Booking b " +
            "JOIN Apartment a ON b.apartment.id = a.id " +
            "JOIN Hotel h ON a.hotel.id = h.id " +
            "JOIN Address addr ON h.address.id = addr.id " +
            "JOIN City c ON addr.city.id = c.id " +
            "WHERE b.customer.id = :customerId AND b.endDate > current_date")
    List<Booking> getActiveBookingsByCustomer(@Param("customerId") Integer customerId);
}
