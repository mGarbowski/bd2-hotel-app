package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.apartment.Apartment;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentService;
import pl.mgarbowski.hotelapp.domain.apartment.InvalidBookingDateException;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Command(command = "apartment")
public class ApartmentCommands {
    private final ApartmentService apartmentService;


    @Command(command = "searchCity", description = "Search free apartments in city")
    public String searchFreeApartmentsInCity(String city, Date start, Date end) {
        try {
            var apartments = apartmentService.findFreeApartmentsInCity(city, start, end);
            return formatString(apartments);
        } catch (InvalidBookingDateException e) {
            return e.getMessage();
        }
    }

    private String formatString(List<Apartment> apartments) {
        return String.join(" ", apartments.stream()
                .map(Apartment::toString)
                .toList()
        );
    }
}
