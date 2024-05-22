package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentStatistics;
import pl.mgarbowski.hotelapp.domain.apartment.ApartmentStatisticsRepository;

import java.util.List;

@Command(command = "stats")
@RequiredArgsConstructor
public class StatisticsCommands {
    private final ApartmentStatisticsRepository apartmentStatisticsRepository;

    @Command(command = "apartment")
    public String forApartment(Integer apartmentId) {
        var stats = apartmentStatisticsRepository.findAll();
        return formatApartments(stats);
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
}
