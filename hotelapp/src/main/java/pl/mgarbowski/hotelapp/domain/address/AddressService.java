package pl.mgarbowski.hotelapp.domain.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
