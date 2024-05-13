package pl.mgarbowski.hotelapp.domain.booking;

import java.sql.Date;

public record BookingDTO(
        Integer bookingId,
        Date start,
        Date end,
        Integer nPeople,
        String street,
        String city
) {
}
