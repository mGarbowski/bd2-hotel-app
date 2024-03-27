package pl.mgarbowski.hotelapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Hotel {
    @Id
    private Integer id;
    private String phoneNumber;
    private String email;
    private Integer stars;
    @OneToOne
    private Address address;
}
