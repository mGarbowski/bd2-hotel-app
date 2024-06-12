package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.ApplicationState;
import pl.mgarbowski.hotelapp.domain.extraservice.ExtraService;
import pl.mgarbowski.hotelapp.domain.extraservice.ExtraServiceService;
import pl.mgarbowski.hotelapp.domain.extraservice.InvalidBookingException;
import pl.mgarbowski.hotelapp.domain.extraservice.ServiceNotAvailableException;

import java.util.List;

/**
 * Shell commands for managing extra services.
 */
@Command(command = "extra", group = "extra")
@RequiredArgsConstructor
public class ExtrasCommands {
    private final ApplicationState applicationState;
    private final ExtraServiceService extrasService;

    /**
     * Lists extra services available to order for a given booking.
     *
     * @param bookingId the ID of the booking
     * @return a formatted string of available extra services or a message if none are available
     */
    @Command(command = "list", description = "List extra services available to order for given booking")
    public String listAvailable(Integer bookingId) {
        var user = applicationState.getUser();
        if (user.isEmpty()) {
            return "Log in to access your bookings";
        }

        try {
            var availableExtras = extrasService.getAvailableForBooking(bookingId, user.get());
            if (availableExtras.isEmpty()) {
                return "No extra services available for given booking";
            }
            return formatMessage(availableExtras);
        } catch (InvalidBookingException e) {
            return e.getMessage();
        }
    }

    /**
     * Orders an extra service for a given booking.
     *
     * @param name      the name of the extra service
     * @param bookingId the ID of the booking
     * @return a message indicating the order status
     */
    @Command(command = "order", description = "Order extra service")
    public String order(String name, Integer bookingId) {
        var maybeUser = applicationState.getUser();
        if (maybeUser.isEmpty()) {
            return "Log in to order";
        }
        var user = maybeUser.get();

        try {
            extrasService.order(name, bookingId, user);
            return String.format("%s ordered successfully", name);
        } catch (InvalidBookingException | ServiceNotAvailableException e) {
            return e.getMessage();
        }
    }

    /**
     * Formats a list of extra services into a string.
     *
     * @param availableExtras the list of extra services
     * @return a formatted string of the extra services
     */
    private static String formatMessage(List<ExtraService> availableExtras) {
        return String.join("\n", availableExtras.stream()
                .map(ExtrasCommands::formatSingleExtra)
                .toList()
        );
    }

    /**
     * Formats a single extra service into a string.
     *
     * @param extra the extra service to format
     * @return a formatted string of the extra service
     */
    private static String formatSingleExtra(ExtraService extra) {
        return String.format(
                "[%d] %s, %.2f%s",
                extra.getId(),
                extra.getName(),
                extra.getPrice(),
                extra.getCurrency().getIsoCode()
        );
    }
}
