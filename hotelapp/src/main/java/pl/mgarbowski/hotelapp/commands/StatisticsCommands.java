package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentStatistics;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentStatisticsRepository;
import pl.mgarbowski.hotelapp.domain.hotel.HotelStatistics;
import pl.mgarbowski.hotelapp.domain.hotel.HotelStatisticsRepository;

import java.util.List;

@Command(command = "stats", group = "stats")
@RequiredArgsConstructor
public class StatisticsCommands {
    private final ApartmentStatisticsRepository apartmentStatisticsRepository;
    private final HotelStatisticsRepository hotelStatisticsRepository;

    @Command(command = "apartments", description = "Show statistics for all apartments")
    public String allApartments() {
        var stats = apartmentStatisticsRepository.findAll();
        return formatApartments(stats);
    }

    @Command(command = "hotel", description = "Show statistics for single hotel")
    public String singleHotel(Integer hotelId) {
        var statistics = hotelStatisticsRepository.findById(hotelId).orElseThrow(
                () -> new IllegalArgumentException("Hotel statistics not found")
        );
        return formatSingleHotelDetails(statistics);
    }

    @Command(command = "hotels", description = "Show statistics for all hotels")
    public String allHotels() {
        var stats = hotelStatisticsRepository.findAll();
        return formatAllHotels(stats);
    }

    private static String formatApartments(List<ApartmentStatistics> apartments) {
        var rows = apartments.stream()
                .map(StatisticsCommands::formatSingleApartment)
                .toList();
        var header = "ID\tBookings\tCustomers\tComplaints\tTotal earnings\tAvg earnings\n";
        return header + String.join("\n", rows);
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

    private static String formatSingleHotelDetails(HotelStatistics hotelStatistics) {
        return String.format(
                """
                                                
                        %s
                        %s
                        %s
                                                
                        Number of customers:    %d
                        Number of bookings:     %d
                        Number of complaints:   %d
                        Total earning:          %s
                        Average rating:         %.2f
                        """,
                hotelStatistics.getName(),
                formatAddress(hotelStatistics),
                hotelStatistics.getEmail(),
                hotelStatistics.getNCustomers(),
                hotelStatistics.getNBookings(),
                hotelStatistics.getNComplaints(),
                hotelStatistics.getTotalEarning(),
                hotelStatistics.getAvgRating()
        );
    }

    private static String formatAllHotels(List<HotelStatistics> hotels) {
        var rows = hotels.stream().map(StatisticsCommands::formatSingleHotelRow).toList();
        var header = "ID\tAvg Rating\tName\tAddress\tEmail\tCustomers\tBookings\tComplaints\tTotal Earning\n";
        return header + String.join("\n", rows);
    }

    private static String formatSingleHotelRow(HotelStatistics hotel) {
        return String.format(
                "[%d]\t%.2f\t%s\t%s\t%s\t%d\t%d\t%d\t%.2f",
                hotel.getHotelId(),
                hotel.getAvgRating(),
                hotel.getName(),
                formatAddress(hotel),
                hotel.getEmail(),
                hotel.getNCustomers(),
                hotel.getNBookings(),
                hotel.getNComplaints(),
                hotel.getTotalEarning()
        );
    }

    private static String formatAddress(HotelStatistics hotelStatistics) {
        return String.format(
                "%s, %s, %s, %s",
                hotelStatistics.getStreet(),
                hotelStatistics.getZipCode(),
                hotelStatistics.getCity(),
                hotelStatistics.getCountry()
        );
    }
}
