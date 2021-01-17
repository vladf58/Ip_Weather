package ro.mta.se.lab.Exceptions;
/**
 * Exception thrown from a Bad JSON.
 * @author Vlad Florea
 */
public class InvalidCityException extends MtaWeatherException{
    private String message;
    @Override
    public String getMessage(){
        return this.message;
    }
    public InvalidCityException(String message){
        this.message=message;
    }
}
