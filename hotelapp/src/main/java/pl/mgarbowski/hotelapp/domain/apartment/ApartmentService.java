package pl.mgarbowski.hotelapp.domain.apartment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgarbowski.hotelapp.domain.booking.Booking;
import pl.mgarbowski.hotelapp.domain.booking.BookingRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing apartments.
 */
@RequiredArgsConstructor
@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final BookingRepository bookingRepository;

    /**
     * Finds free apartments in a given city within a date range.
     *
     * @param city  the name of the city
     * @param start the start date of the booking
     * @param end   the end date of the booking
     * @return a list of free apartments in the specified city and date range
     * @throws InvalidBookingDateException if the start date is after the end date
     */
    public List<Apartment> findFreeApartmentsInCity(String city, Date start, Date end) throws InvalidBookingDateException {
        if (start.after(end)) {
            throw new InvalidBookingDateException("Start is after end");
        }

        List<Apartment> cityApartments = apartmentRepository.findByCity(city);

        var freeApartmentsInCity = new ArrayList<Apartment>();

        for (Apartment apartment : cityApartments) {
            List<Booking> listOfConflicts = bookingRepository.findConflictingBookings(apartment.getId(), start, end);
            if (listOfConflicts.isEmpty()) {
                freeApartmentsInCity.add(apartment);
            }
        }

        return freeApartmentsInCity;
    }
}
