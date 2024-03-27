package pl.mgarbowski.hotelapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.command.annotation.EnableCommand;
import pl.mgarbowski.hotelapp.commands.MyCommands;

@CommandScan
@SpringBootApplication
public class HotelappApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelappApplication.class, args);
    }

}
