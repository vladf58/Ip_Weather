package ro.mta.se.lab.controller;

import ro.mta.se.lab.Exceptions.BadLogException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Class responsible for logging to a file every server interogation
 */
public class Logger {

    private static final URL logFile = Logger.class.getResource("/controller/files/logFile.txt");

    public static void logToFile(String message) throws BadLogException {

        /**
         * Get the current time
         */
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss :");
        Date date = new Date(System.currentTimeMillis());

        String newMessage = formatter.format(date)+ message + System.lineSeparator();
        try {
                Files.writeString(Paths.get(logFile.toURI()), newMessage, CREATE, APPEND);

        }
        catch (IOException | URISyntaxException e){
            throw new BadLogException("Something is wrong with the Log file:"+e.getMessage());
        }
    }
}