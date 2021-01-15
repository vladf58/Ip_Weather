package mtaWeather.Exceptions;

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
