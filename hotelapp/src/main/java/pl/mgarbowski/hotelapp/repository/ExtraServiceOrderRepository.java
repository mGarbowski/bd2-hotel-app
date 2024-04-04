package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.ExtraServiceOrder;

public interface ExtraServiceOrderRepository extends JpaRepository<ExtraServiceOrder, Integer> {
}
