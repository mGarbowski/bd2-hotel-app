package pl.mgarbowski.hotelapp.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgarbowski.hotelapp.ApplicationState;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ApplicationState appState;

    public Optional<Customer> getLoggedIn() {
        return appState.getUser();
    }

    public Optional<Customer> attemptLogin(Integer customerId) {
        var maybeCustomer = customerRepository.findById(customerId);
        appState.setUser(maybeCustomer);
        return maybeCustomer;
    }

    public void logout() {
        appState.setUser(Optional.empty());
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
