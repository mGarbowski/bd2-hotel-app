package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.ExtraService;

public interface ExtraServiceRepository extends JpaRepository<ExtraService, Integer> {
}
