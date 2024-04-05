package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.customer.Customer;
import pl.mgarbowski.hotelapp.domain.customer.CustomerService;

@Command(command = "user")
@RequiredArgsConstructor
public class CustomerCommands {
    private final CustomerService customerService;

    @Command(command = "who", description = "Display currently logged in user")
    public String who() {
        return customerService.getLoggedIn()
                .map(c -> String.format("[%d] %s %s", c.getId(), c.getName(), c.getSurname()))
                .orElse("You are not currently logged in");
    }

    @Command(command = "login", description = "Log in as an existing customer")
    public String login(Integer customerId) {
        return customerService.attemptLogin(customerId)
                .map(c -> String.format("Welcome %s %s!", c.getName(), c.getSurname()))
                .orElse(String.format("Customer with id [%d] not found", customerId));
    }

    @Command(command = "list", description = "List all customers")
    public String list() {
        var customers = customerService.getAll().stream()
                .map(Customer::toString)
                .toList();
        return String.join("\n", customers);
    }
}
