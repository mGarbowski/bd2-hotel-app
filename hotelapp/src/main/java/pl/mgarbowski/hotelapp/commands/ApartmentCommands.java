package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.address.Address;
import pl.mgarbowski.hotelapp.domain.apartment.Apartment;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentService;
import pl.mgarbowski.hotelapp.domain.apartment.InvalidBookingDateException;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.util.List;

/**
 * Shell commands for managing apartments.
 */
@RequiredArgsConstructor
@Command(command = "apartment", group = "apartment")
public class ApartmentCommands {
    private final ApartmentService apartmentService;

    /**
     * Searches for free apartments in a specified city within a date range.
     *
     * @param city  the city to search in
     * @param start the start date of the booking
     * @param end   the end date of the booking
     * @return a string representation of available apartments or a message if none are available
     * @throws InvalidParameterException if any parameter is null
     */
    @Command(command = "searchCity", description = "Search free apartments in city")
    public String searchFreeApartmentsInCity(String city, Date start, Date end) {
        if (city == null || start == null || end == null) {
            throw new InvalidParameterException("Parameters cannot be null.");
        }
        try {
            var apartments = apartmentService.findFreeApartmentsInCity(city, start, end);

            if (apartments.isEmpty()) {
                return "No apartments available at that date.";
            } else {
                return formatString(apartments);
            }

        } catch (InvalidBookingDateException e) {
            return e.getMessage();
        }
    }

    /**
     * Formats a list of apartments into a string.
     *
     * @param apartments the list of apartments
     * @return a formatted string of apartments
     */
    private String formatString(List<Apartment> apartments) {
        return String.join(" ", apartments.stream()
                .map(ApartmentCommands::formatSingleApartment)
                .toList()
        );
    }

    /**
     * Formats an address into a string.
     *
     * @param address the address to format
     * @return a formatted string of the address
     */
    private static String formatAddress(Address address) {
        return String.format(
                "%s, %s, %s, %s",
                address.getStreet(),
                address.getZipCode(),
                address.getCity().getName(),
                address.getCity().getCountry().getName()
        );
    }

    /**
     * Formats a single apartment's details into a string.
     *
     * @param apartment the apartment to format
     * @return a formatted string of the apartment's details
     */
    private static String formatSingleApartment(Apartment apartment) {
        return String.format(
                """
                            Hotel: %s – Apartment number [%d] (%.2f stars) – Capacity: %s – Price per day: %.2f
                                - Rooms: %d
                                - Bathrooms: %d
                                - Beds: %d
                                - Address: %s \n
                        """,
                apartment.getHotel().getName(),
                apartment.getId(),
                apartment.getAvg_rating().doubleValue(),
                apartment.getMaxNPeople() > 1 ?
                        String.format("%d person", apartment.getMaxNPeople()) : String.format("%d people", apartment.getMaxNPeople()),
                apartment.getPricePerDay(),
                apartment.getNRooms(),
                apartment.getNBathrooms(),
                apartment.getNBeds(),
                formatAddress(apartment.getHotel().getAddress())
        );
    }
}
