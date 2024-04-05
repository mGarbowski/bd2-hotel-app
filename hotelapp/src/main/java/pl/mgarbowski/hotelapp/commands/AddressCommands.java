package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.address.Address;
import pl.mgarbowski.hotelapp.domain.address.AddressService;

@RequiredArgsConstructor
@Command(command = "address")
public class AddressCommands {
    private final AddressService addressService;

    @Command(command = "list")
    public String listAll() {
        var addresses = addressService.getAll().stream()
                .map(Address::toString)
                .toList();
        return String.join("\n", addresses);
    }
}
