package pl.mgarbowski.hotelapp.domain.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class ComplaintService {
    private final ComplaintRepository complaintRepository;

    public void createComplaint(Booking booking, Date date, String complaintText) throws IllegalArgumentException {
        if(complaintRepository.getComplaintByBooking(booking).isPresent()) {
            throw new IllegalArgumentException("Complaint already exists for this booking");
        }
        var complaint = new Complaint();
        complaint.setBooking(booking);
        complaint.setText(complaintText);
        complaint.setTimestamp(date);
        complaintRepository.save(complaint);
    }
}
