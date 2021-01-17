package ro.mta.se.lab.Exceptions;
/**
 * Abstract class that extends the Exception Class
 * @author Vlad Florea
 */
abstract public class MtaWeatherException extends Exception {
    @Override
    abstract public String getMessage();
}
