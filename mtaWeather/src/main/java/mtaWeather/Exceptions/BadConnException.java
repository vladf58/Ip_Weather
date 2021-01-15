package mtaWeather.Exceptions;

/**
 * Exception thrown from a Bad connection to a server
 * @author Vlad Florea
 */
public class BadConnException  extends MtaWeatherException{
    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    public BadConnException(String message){
        this.message=message;
    }
}
