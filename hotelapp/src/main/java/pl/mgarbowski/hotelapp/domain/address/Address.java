package pl.mgarbowski.hotelapp.domain.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Represents an address entity.
 */
@Data
@Entity
public class Address {
    @Id
    private Integer id;
    private String street;
    private String zipCode;
    @ManyToOne(optional = false)
    private City city;

    /**
     * Returns a string representation of the address.
     *
     * @return a formatted string of the address
     */
    @Override
    public String toString() {
        return String.format(
                "[%d] %s, %s, %s, %s",
                id, street, zipCode, city.getName(), city.getCountry().getName()
        );
    }
}
