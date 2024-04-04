package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
