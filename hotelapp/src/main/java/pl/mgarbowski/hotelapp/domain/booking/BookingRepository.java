package pl.mgarbowski.hotelapp.domain.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT new pl.mgarbowski.hotelapp.domain.booking.BookingDTO(b.id, b.customer.id, b.apartment.id) " +
            "FROM Booking b " +
            "WHERE b.customer.id = :customerId AND b.endDate > current_date")
    List<BookingDTO> getActiveBookingsByCustomer(@Param("customerId") Integer customerId);
}
