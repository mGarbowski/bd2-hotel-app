package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.customer.Customer;
import pl.mgarbowski.hotelapp.domain.customer.CustomerService;

/**
 * Shell commands for managing customers.
 */
@Command(command = "user", group = "user")
@RequiredArgsConstructor
public class CustomerCommands {
    private final CustomerService customerService;

    /**
     * Displays the currently logged-in user.
     *
     * @return a string representation of the logged-in user or a message if not logged in
     */
    @Command(command = "who", description = "Display currently logged in user")
    public String who() {
        return customerService.getLoggedIn()
                .map(c -> String.format("[%d] %s %s", c.getId(), c.getName(), c.getSurname()))
                .orElse("You are not currently logged in");
    }

    /**
     * Logs in as an existing customer.
     *
     * @param customerId the ID of the customer
     * @return a welcome message or an error message if the customer is not found
     */
    @Command(command = "login", description = "Log in as an existing customer")
    public String login(Integer customerId) {
        return customerService.attemptLogin(customerId)
                .map(c -> String.format("Welcome %s %s!", c.getName(), c.getSurname()))
                .orElse(String.format("Customer with id [%d] not found", customerId));
    }


    /**
     * Logs out currently logged in user.
     *
     * @return confirmation message
     */
    @Command(command = "logout", description = "Log out")
    public String logout() {
        customerService.logout();
        return "Logged out";
    }

    /**
     * Lists all customers.
     *
     * @return a formatted string of all customers
     */
    @Command(command = "list", description = "List all customers")
    public String list() {
        var customers = customerService.getAll().stream()
                .map(Customer::toString)
                .toList();
        return String.join("\n", customers);
    }
}
