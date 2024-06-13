package pl.mgarbowski.hotelapp.domain.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import pl.mgarbowski.hotelapp.domain.address.Address;

/**
 * Represents a customer entity.
 */
@Data
@Entity
public class Customer {
    @Id
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private Character gender;
    private String email;
    private String phoneNumber;
    @OneToOne
    private Address address;

    public String toString() {
        return String.format("[%d] %s %s", id, name, surname);
    }
}
