package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.address.Address;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentStatistics;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentStatisticsRepository;
import pl.mgarbowski.hotelapp.domain.hotel.HotelRepository;
import pl.mgarbowski.hotelapp.domain.hotel.HotelStatistics;
import pl.mgarbowski.hotelapp.domain.hotel.HotelStatisticsRepository;

import java.util.List;

@Command(command = "stats")
@RequiredArgsConstructor
public class StatisticsCommands {
    private final ApartmentStatisticsRepository apartmentStatisticsRepository;
    private final HotelStatisticsRepository hotelStatisticsRepository;
    private final HotelRepository hotelRepository;

    @Command(command = "apartments")
    public String allApartments() {
        var stats = apartmentStatisticsRepository.findAll();
        return formatApartments(stats);
    }

    @Command(command = "hotel", description = "Show hotel statistics")
    public String singleHotel(Integer hotelId) {
        var statistics = hotelStatisticsRepository.findById(hotelId).orElseThrow(
                () -> new IllegalArgumentException("Hotel statistics not found")
        );
        return formatSingleHotelDetails(statistics);
    }

    private static String formatApartments(List<ApartmentStatistics> apartments) {
        var rows = apartments.stream()
                .map(StatisticsCommands::formatSingleApartment)
                .toList();
        return "ID\tBookings\tCustomers\tComplaints\tTotal earnings\tAvg earnings\n" + String.join("\n", rows);
    }

    private static String formatSingleApartment(ApartmentStatistics apartment) {
        return String.format(
                "[%d]\t%d\t%d\t%d\t%.2f\t%.2f",
                apartment.getApartmentId(),
                apartment.getNBookings(),
                apartment.getNCustomers(),
                apartment.getNComplaints(),
                apartment.getTotalEarning(),
                apartment.getAvgEarning()
        );
    }

    private String formatSingleHotelDetails(HotelStatistics hotelStatistics) {
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
                StatisticsCommands.formatAddress(hotel.getAddress()),
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
