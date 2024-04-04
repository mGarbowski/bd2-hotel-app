package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
