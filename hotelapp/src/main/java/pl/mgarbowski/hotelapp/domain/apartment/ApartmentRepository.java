package pl.mgarbowski.hotelapp.domain.apartment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    @Query(value = "SELECT a FROM Apartment a " +
            "JOIN Hotel h ON a.hotel.id = h.id " +
            "JOIN Address addr ON h.address.id = addr.id " +
            "JOIN City c ON addr.city.id = c.id " +
            "WHERE c.name = :city")
    List<Apartment> findByCity(@Param("city") String city);
}
