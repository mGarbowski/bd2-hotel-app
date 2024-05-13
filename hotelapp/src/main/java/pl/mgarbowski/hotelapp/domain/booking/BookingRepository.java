package pl.mgarbowski.hotelapp.domain.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT new pl.mgarbowski.hotelapp.domain.booking.BookingDTO(b.id, b.startDate, b.endDate, b.nPeople, addr.street, c.name) " +
            "FROM Booking b " +
            "JOIN Apartment a ON b.apartment.id = a.id " +
            "JOIN Hotel h ON a.hotel.id = h.id " +
            "JOIN Address addr ON h.address.id = addr.id " +
            "JOIN City c ON addr.city.id = c.id " +
            "WHERE b.customer.id = :customerId AND b.endDate > current_date")
    List<BookingDTO> getActiveBookingsByCustomer(@Param("customerId") Integer customerId);
}
