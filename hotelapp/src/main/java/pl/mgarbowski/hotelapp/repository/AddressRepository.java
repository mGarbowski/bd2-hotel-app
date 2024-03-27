package pl.mgarbowski.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgarbowski.hotelapp.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
