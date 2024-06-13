package pl.mgarbowski.hotelapp;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

/**
 * The main application class for the Hotel Management Application.
 * It serves as the entry point for the Spring Boot application.
 */
@CommandScan
@SpringBootApplication
public class HotelappApplication {

    /**
     * The main method to start the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(HotelappApplication.class, args);
    }
    @PostConstruct
    public void displayStartupMessage() {
        System.out.println("Welcome to the hotel application! To view the available commands type 'help'.");
    }
}
