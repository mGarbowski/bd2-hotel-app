package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.address.Address;
import pl.mgarbowski.hotelapp.domain.hotel.HotelRepository;
import pl.mgarbowski.hotelapp.domain.hotel.HotelStatistics;
import pl.mgarbowski.hotelapp.domain.hotel.HotelStatisticsRepository;

@RequiredArgsConstructor
@Command(command = "hotel")
public class HotelCommands {
    final HotelStatisticsRepository hotelStatisticsRepository;
    final HotelRepository hotelRepository;

    @Command(command = "statistics", description = "Show hotel statistics")
    public String showHotelStatistics(Integer hotelId) {
        var statistics = hotelStatisticsRepository.findById(hotelId).orElseThrow(
                () -> new IllegalArgumentException("Hotel statistics not found")
        );
        return formatString(statistics);
    }

    private String formatString(HotelStatistics hotelStatistics) {
        var hotel = hotelRepository.findById(hotelStatistics.getHotelId()).orElseThrow(
                () -> new IllegalArgumentException("Hotel not found")
        );
        return String.format(
                """
                        
                        %s
                        %s
                        %s
                        
                        Number of customers:    %d
                        Number of bookings:     %d
                        Number of complaints:   %d
                        Total earning:          %s
                        """,
                hotel.getName(),
                formatAddress(hotel.getAddress()),
                hotel.getEmail(),
                hotelStatistics.getNCustomers(),
                hotelStatistics.getNBookings(),
                hotelStatistics.getNComplaints(),
                hotelStatistics.getTotalEarning()
        );
    }

    private static String formatAddress(Address address) {
        return String.format(
                "%s, %s, %s, %s",
                address.getStreet(),
                address.getZipCode(),
                address.getCity().getName(),
                address.getCity().getCountry().getName()
        );
    }
}
