package ro.mta.se.lab.Exceptions;

public class BadLogException extends MtaWeatherException{
    private String message;
    @Override
    public String getMessage(){
        return this.message;
    }
    public BadLogException(String message){
        this.message=message;
    }
}
