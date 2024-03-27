package pl.mgarbowski.hotelapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

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
}
