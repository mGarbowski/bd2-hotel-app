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

@RequiredArgsConstructor
@Command(command = "apartment")
public class ApartmentCommands {
    private final ApartmentService apartmentService;


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

    private String formatString(List<Apartment> apartments) {
        return String.join(" ", apartments.stream()
                .map(ApartmentCommands::formatSingleApartment)
                .toList()
        );
    }

    private static String formatAddress(Address address) {
        return String.format(
                "%s, %s, %s, %s",
                address.getStreet(),
                address.getZipCode(),
                address.getCity().getName(),
                address.getCity().getCountry().getName()
        );
    }

    private static String formatSingleApartment(Apartment apartment) {
        return String.format(
                """
                            Hotel: [%d] – Apartment number [%d] (%.2f stars) – Capacity: %s – Price per day: %.2f
                                - Rooms: %d
                                - Bathrooms: %d
                                - Beds: %d
                                - Address: %s \n
                        """,
                apartment.getHotel().getId(),
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
