package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.address.Country;
import pl.mgarbowski.hotelapp.domain.address.CountryRepository;

import java.util.List;

@Command(command = "country")
@RequiredArgsConstructor
public class MyCommands {
    private final CountryRepository countryRepository;

    @Command(command = "list", description = "List all countries")
    public List<String> listAll() {
        return countryRepository.findAll().stream().map(Country::getName).toList();
    }

    @Command(command = "byCode", description = "Get country name by code")
    public String getByCode(String code) {
        return countryRepository
                .findByCode(code)
                .map(Country::getName)
                .orElse("Not found");
    }
}
