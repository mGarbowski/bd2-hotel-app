package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.ApplicationState;
import pl.mgarbowski.hotelapp.domain.extraservice.ExtraService;
import pl.mgarbowski.hotelapp.domain.extraservice.ExtraServiceService;
import pl.mgarbowski.hotelapp.domain.extraservice.InvalidBookingException;
import pl.mgarbowski.hotelapp.domain.extraservice.ServiceNotAvailableException;

import java.util.List;

@Command(command = "extra")
@RequiredArgsConstructor
public class ExtrasCommands {
    private final ApplicationState applicationState;
    private final ExtraServiceService extrasService;

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

    private static String formatMessage(List<ExtraService> availableExtras) {
        return String.join("\n", availableExtras.stream()
                .map(ExtrasCommands::formatSingleExtra)
                .toList()
        );
    }

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
