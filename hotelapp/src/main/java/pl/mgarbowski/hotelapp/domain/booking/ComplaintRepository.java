package pl.mgarbowski.hotelapp.domain.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for {@link Complaint} entities.
 * Provides methods for performing CRUD operations on Complaint entities.
 */
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    /**
     * Retrieves a complaint by its associated booking.
     *
     * @param booking the booking associated with the complaint
     * @return an optional containing the complaint if found, or empty if not found
     */
    Optional<Complaint> getComplaintByBooking(Booking booking);
}
