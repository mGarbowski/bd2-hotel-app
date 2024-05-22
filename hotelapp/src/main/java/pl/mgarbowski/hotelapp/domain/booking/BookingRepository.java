package pl.mgarbowski.hotelapp.domain.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.sql.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT * FROM get_conflicting_bookings(:apartmentId, :start, :end)", nativeQuery = true)
    List<Booking> findConflictingBookings(Integer apartmentId, Date start, Date end);

    List<Booking> findBookingsByCustomer(Customer customer);

    @Query(value = "SELECT b FROM Booking b " +
            "JOIN Apartment a ON b.apartment.id = a.id " +
            "JOIN Hotel h ON a.hotel.id = h.id " +
            "JOIN Address addr ON h.address.id = addr.id " +
            "JOIN City c ON addr.city.id = c.id " +
            "WHERE b.customer.id = :customerId AND b.endDate > current_date")
    List<Booking> getActiveBookingsByCustomer(@Param("customerId") Integer customerId);
}
