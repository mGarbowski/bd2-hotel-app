package pl.mgarbowski.hotelapp;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@CommandScan
@SpringBootApplication
public class HotelappApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelappApplication.class, args);
    }
    @PostConstruct
    public void displayStartupMessage() {
        System.out.println("Welcome to the hotel application! To view the available commands type 'help'.");
    }
}
