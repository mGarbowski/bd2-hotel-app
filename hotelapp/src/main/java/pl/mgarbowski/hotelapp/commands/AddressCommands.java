package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.model.Address;
import pl.mgarbowski.hotelapp.model.AddressRepository;

@RequiredArgsConstructor
@Command(command = "address")
public class AddressCommands {
    private final AddressRepository addressRepository;

    @Command(command = "list")
    public String listAll() {
        var addresses = addressRepository
                .findAll().stream()
                .map(Address::toString)
                .toList();
        return String.join("\n", addresses);
    }
}
