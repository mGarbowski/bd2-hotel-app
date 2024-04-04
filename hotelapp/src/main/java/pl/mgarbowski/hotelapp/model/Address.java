package pl.mgarbowski.hotelapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    private Integer id;
    private String street;
    private String zipCode;
    @ManyToOne(optional = false)
    private City city;

    @Override
    public String toString() {
        return String.format(
                "[%d] %s, %s, %s, %s",
                id, street, zipCode, city.getName(), city.getCountry().getName()
        );
    }
}