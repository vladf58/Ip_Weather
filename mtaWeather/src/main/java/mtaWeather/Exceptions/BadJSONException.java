package mtaWeather.Exceptions;
/**
 * Exception thrown from a Bad JSON.
 * @author Vlad Florea
 */
public class BadJSONException extends MtaWeatherException {
    private String message;
    @Override
    public String getMessage(){
        return this.message;
    }
    public BadJSONException(String message){
        this.message=message;
    }
}
