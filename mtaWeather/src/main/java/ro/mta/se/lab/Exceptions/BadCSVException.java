package ro.mta.se.lab.Exceptions;

/**
 * Exception thrown from a Bad CSV file
 * @author Vlad Florea
 */
public class BadCSVException extends MtaWeatherException {
    private String message;
    @Override
    public String getMessage(){
        return message;
    }
    public BadCSVException(String message){
        this.message=message;
    }
}
