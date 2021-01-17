package ro.mta.se.lab.controller;

/**
 * Class responsible for retrieving the icons for the weather forecast from the openweather site.
 * @author Vlad Florea
 */

public class WeatherIconConstructor {
    private static final String baseURL ="http://openweathermap.org/img/wn/";
    private static final String extension ="@2x.png";

    public static String getImageUrl(String iconCode){
        return baseURL+iconCode+extension;
    }


}
