package pl.mgarbowski.hotelapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.util.Optional;

@Component
@AllArgsConstructor
@Getter
@Setter
public class ApplicationState {
    private Optional<Customer> user;
}
