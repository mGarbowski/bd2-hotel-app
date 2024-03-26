package pl.mgarbowski.hotelapp;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class MyCommands {
    @ShellMethod(key = "hello", value = "Display hello message")
    public String helloWorld(@ShellOption(defaultValue = "world") String arg) {
        return "Hello " + arg;
    }
}
