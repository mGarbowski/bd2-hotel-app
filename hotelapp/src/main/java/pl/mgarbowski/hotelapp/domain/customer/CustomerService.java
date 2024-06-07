package pl.mgarbowski.hotelapp.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgarbowski.hotelapp.ApplicationState;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing customers.
 */
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ApplicationState appState;

    /**
     * Retrieves the currently logged-in customer.
     *
     * @return an optional containing the logged-in customer if present, or empty if not logged in
     */
    public Optional<Customer> getLoggedIn() {
        return appState.getUser();
    }

    /**
     * Attempts to log in a customer by their ID.
     *
     * @param customerId the ID of the customer
     * @return an optional containing the customer if found, or empty if not found
     */
    public Optional<Customer> attemptLogin(Integer customerId) {
        var maybeCustomer = customerRepository.findById(customerId);
        appState.setUser(maybeCustomer);
        return maybeCustomer;
    }

    /**
     * Retrieves all customers.
     *
     * @return a list of all customers
     */
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
