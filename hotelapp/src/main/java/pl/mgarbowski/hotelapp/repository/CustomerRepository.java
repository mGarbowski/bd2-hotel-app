package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
