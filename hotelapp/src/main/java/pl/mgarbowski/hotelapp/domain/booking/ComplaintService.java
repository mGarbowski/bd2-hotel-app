package pl.mgarbowski.hotelapp.domain.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Service class for managing complaints.
 */
@Service
@RequiredArgsConstructor
public class ComplaintService {
    private final ComplaintRepository complaintRepository;

    /**
     * Creates a complaint for a given booking.
     *
     * @param booking       the booking associated with the complaint
     * @param complaintText the text of the complaint
     * @throws IllegalArgumentException if a complaint already exists for the booking
     */
    public void createComplaint(Booking booking, String complaintText) throws IllegalArgumentException {
        if (complaintRepository.getComplaintByBooking(booking).isPresent()) {
            throw new IllegalArgumentException("Complaint already exists for this booking");
        }
        var complaint = new Complaint();
        complaint.setBooking(booking);
        complaint.setText(complaintText);
        complaint.setTimestamp(new Date(System.currentTimeMillis()));
        complaintRepository.save(complaint);
    }
}
