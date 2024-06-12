package pl.mgarbowski.hotelapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.mgarbowski.hotelapp.domain.customer.Customer;

import java.util.Optional;

/**
 * This class represents the state of the application.
 * It holds the currently logged-in user.
 */
@Component
@AllArgsConstructor
@Getter
@Setter
public class ApplicationState {

    /**
     * The currently logged-in user.
     */
    private Optional<Customer> user;
}
