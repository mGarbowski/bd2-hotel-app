package pl.mgarbowski.hotelapp.domain.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT * FROM get_conflicting_bookings(:apartmentId, :start, :end)", nativeQuery = true)
    List<Booking> findConflictingBookings(Integer apartmentId, Date start, Date end);
}
