package pl.mgarbowski.hotelapp.commands;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

@Command
public class MyCommands {
    @Command(command = "hello", description = "Display hello message")
    public String helloWorld(@Option(defaultValue = "world") String arg) {
        return "Hello " + arg;
    }
}
