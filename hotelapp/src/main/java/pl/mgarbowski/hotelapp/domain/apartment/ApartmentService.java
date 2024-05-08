package pl.mgarbowski.hotelapp.domain.apartment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;

    public List<Apartment> findFreeApartmentsInCity(String city, Date start, Date end) throws InvalidBookingDateException {
        if (start.after(end)) {
            throw new InvalidBookingDateException("Start is after end");
        }

        return apartmentRepository.findByCity(city);
    }
}
