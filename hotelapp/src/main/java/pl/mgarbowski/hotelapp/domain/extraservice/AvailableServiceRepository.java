package pl.mgarbowski.hotelapp.domain.extraservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableServiceRepository extends JpaRepository<AvailableService, AvailableServiceKey> {
}
