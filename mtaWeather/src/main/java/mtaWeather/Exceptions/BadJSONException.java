package mtaWeather.Exceptions;

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
