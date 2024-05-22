package pl.mgarbowski.hotelapp.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.mgarbowski.hotelapp.domain.payment.PaymentService;
import pl.mgarbowski.hotelapp.domain.payment.PaymentSummaryEntry;

import java.math.BigDecimal;
import java.util.List;

@Command(command = "payment")
@RequiredArgsConstructor
public class PaymentCommands {
    private final PaymentService paymentService;

    @Command(command = "summary", description = "Display a summary for a given booking")
    public String displaySummary(Integer bookingId) {
        // TODO check current user
        var summary = paymentService.getSummaryForBooking(bookingId);
        var total = paymentService.getTotalBalanceForSummary(summary);
        return formatMessage(summary, total);
    }

    private static String formatMessage(List<PaymentSummaryEntry> summary, BigDecimal total) {

        var entries = String.join("\n", summary.stream()
                .map(PaymentCommands::formatSingleEntry)
                .toList()
        );

        return String.format("%s\n%s\nTOTAL:%40.2f", entries, "-".repeat(46), total);
    }

    private static String formatSingleEntry(PaymentSummaryEntry entry) {
        return String.format("%-36s%10.2f", entry.getName(), entry.getAmount());
    }

    @Command(command = "make", description = "Make a payment for a given booking")
    public String makePayment(Integer bookingId, BigDecimal amount) {

        // TODO check current user
        try {
            paymentService.makePayment(bookingId, amount);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        return "Payment made successfully. Balance for the given booking after this payment is " +
                paymentService.getTotalBalanceForBooking(bookingId);
    }
}
