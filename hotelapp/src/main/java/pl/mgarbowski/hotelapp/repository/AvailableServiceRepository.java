package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.AvailableService;
import pl.mgarbowski.hotelapp.model.AvailableServiceKey;

public interface AvailableServiceRepository extends JpaRepository<AvailableService, AvailableServiceKey> {
}
