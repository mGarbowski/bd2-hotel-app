package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentStatistics;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentStatisticsRepository;
import pl.mgarbowski.hotelapp.domain.hotel.HotelStatistics;
import pl.mgarbowski.hotelapp.domain.hotel.HotelStatisticsRepository;

import java.util.List;

/**
 * Shell commands for retrieving statistics for apartments and hotels.
 */
@Command(command = "stats", group = "stats")
@RequiredArgsConstructor
public class StatisticsCommands {
    private final ApartmentStatisticsRepository apartmentStatisticsRepository;
    private final HotelStatisticsRepository hotelStatisticsRepository;

    /**
     * Shows statistics for all apartments.
     *
     * @return a formatted string of statistics for all apartments
     */
    @Command(command = "apartments", description = "Show statistics for all apartments")
    public String allApartments() {
        var stats = apartmentStatisticsRepository.findAll();
        return formatApartments(stats);
    }

    /**
     * Shows statistics for a single hotel.
     *
     * @param hotelId the ID of the hotel
     * @return a formatted string of the hotel's statistics
     */
    @Command(command = "hotel", description = "Show statistics for single hotel")
    public String singleHotel(Integer hotelId) {
        var statistics = hotelStatisticsRepository.findById(hotelId).orElseThrow(
                () -> new IllegalArgumentException("Hotel statistics not found")
        );
        return formatSingleHotelDetails(statistics);
    }

    /**
     * Shows statistics for all hotels.
     *
     * @return a formatted string of statistics for all hotels
     */
    @Command(command = "hotels", description = "Show statistics for all hotels")
    public String allHotels() {
        var stats = hotelStatisticsRepository.findAll();
        return formatAllHotels(stats);
    }

    /**
     * Formats statistics for a single apartment into a string with fixed-width columns.
     *
     * @param apartment the statistics of the apartment
     * @return a formatted string of the apartment's statistics
     */
    private static String formatSingleApartment(ApartmentStatistics apartment) {
        return String.format(
                "%-2d\t%-10d\t%-10d\t%-10d\t%-15.2f\t%-12.2f",
                apartment.getApartmentId(),
                apartment.getNBookings(),
                apartment.getNCustomers(),
                apartment.getNComplaints(),
                apartment.getTotalEarning(),
                apartment.getAvgEarning()
        );
    }

    /**
     * Formats all apartments' statistics into a string with a fixed-width header and rows.
     *
     * @param apartments the list of apartment statistics
     * @return a formatted string of all apartments' statistics
     */
    private static String formatApartments(List<ApartmentStatistics> apartments) {
        var rows = apartments.stream()
                .map(StatisticsCommands::formatSingleApartment)
                .toList();
        var header = String.format(
                "%-2s\t%-10s\t%-10s\t%-10s\t%-15s\t%-12s\n",
                "ID", "Bookings", "Customers", "Complaints", "Total earnings", "Avg earnings"
        );
        return header + String.join("\n", rows);
    }
    /**
     * Formats detailed statistics for a single hotel into a string.
     *
     * @param hotelStatistics the statistics of the hotel
     * @return a formatted string of the hotel's detailed statistics
     */
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

    /**
     * Formats statistics for all hotels into a string.
     *
     * @param hotels the list of hotel statistics
     * @return a formatted string of statistics for all hotels
     */
    /**
     * Formats all hotels' statistics into a string with a fixed-width header and rows.
     *
     * @param hotels the list of hotel statistics
     * @return a formatted string of all hotels' statistics
     */
    private static String formatAllHotels(List<HotelStatistics> hotels) {
        var rows = hotels.stream().map(StatisticsCommands::formatSingleHotelRow).toList();
        var header = String.format(
                "%-2s\t%-9s\t%-25s\t%-45s\t%-20s\t%-10s\t%-10s\t%-10s\t%-8s\n",
                "ID", "Avg Rating", "Name", "Address", "Email", "Customers", "Bookings", "Complaints", "Total Earning"
        );
        return header + String.join("\n", rows);
    }

    /**
     * Formats a single hotel's statistics into a tab-separated string.
     *
     * @param hotel the statistics of the hotel
     * @return a formatted string of the hotel's statistics
     */
    private static String formatSingleHotelRow(HotelStatistics hotel) {
        return String.format(
                "%-2d\t%-9.2f\t%-25s\t%-45s\t%-20s\t%-10d\t%-10d\t%-10d\t%-6.2f",
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

    /**
     * Formats a hotel's address into a string.
     *
     * @param hotelStatistics the statistics of the hotel
     * @return a formatted string of the hotel's address
     */
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
