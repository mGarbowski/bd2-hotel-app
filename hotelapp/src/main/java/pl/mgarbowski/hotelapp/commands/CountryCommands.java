package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.address.Country;
import pl.mgarbowski.hotelapp.domain.address.CountryRepository;

import java.util.List;

/**
 * Shell commands for managing countries.
 */
@Command(command = "country", group = "country")
@RequiredArgsConstructor
public class CountryCommands {
    private final CountryRepository countryRepository;

    /**
     * Lists all countries.
     *
     * @return a list of country names
     */
    @Command(command = "list", description = "List all countries")
    public List<String> listAll() {
        return countryRepository.findAll().stream().map(Country::getName).toList();
    }

    /**
     * Gets the name of a country by its code.
     *
     * @param code the code of the country
     * @return the name of the country or "Not found" if no country with the given code exists
     */
    @Command(command = "byCode", description = "Get country name by code")
    public String getByCode(String code) {
        return countryRepository
                .findByCode(code)
                .map(Country::getName)
                .orElse("Not found");
    }
}
