package mtaWeather.Exceptions;

public class exceptionBadCSV extends weatherException {
    private String message;

    @Override
    public String getMessage(){
        return this.message;
    }
    public exceptionBadCSV(String message){
        this.message=message;
    }
}
