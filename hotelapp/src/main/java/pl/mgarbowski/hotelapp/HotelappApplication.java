package pl.mgarbowski.hotelapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@CommandScan
@SpringBootApplication
public class HotelappApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelappApplication.class, args);
    }

}
