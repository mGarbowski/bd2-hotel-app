package pl.mgarbowski.hotelapp.domain.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing addresses.
 */
@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    /**
     * Retrieves all addresses.
     *
     * @return a list of all addresses
     */
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}