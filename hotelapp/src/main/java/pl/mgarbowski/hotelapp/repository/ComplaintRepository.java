package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
}
