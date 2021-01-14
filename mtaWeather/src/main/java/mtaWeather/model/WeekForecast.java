package mtaWeather.model;

import javafx.beans.property.SimpleStringProperty;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class WeekForecast {
    /**
     * Default values for JSON
     */
    private static final String currentJSON="current";
    private static final String timezoneOffsetJSON="timezone_offset";
    private static final String dailyJSON="daily";

    public ArrayList<WeatherForecast> getForecast() {
        return forecast;
    }

    /**
     * List of forecasts
     */
    private ArrayList<WeatherForecast> forecast;

    public WeatherForecast getCrtForecast() {
        return crtForecast;
    }

    private WeatherForecast crtForecast;
    private Long timezoneOffset;

    public WeekForecast(JSONObject weatherJSON){
        /**
         * Current timezone
         */

        this.forecast = new ArrayList<WeatherForecast>();
        this.timezoneOffset=Long.parseLong(weatherJSON.get(timezoneOffsetJSON).toString());

        /**
         * Current weather
         */
        crtForecast= new WeatherForecast(weatherJSON.getJSONObject(currentJSON),timezoneOffset);


        /**
         * Access days
         */
        JSONArray dailyWeatherJsonArray = weatherJSON.getJSONArray(dailyJSON);

        for(Integer index=0;index<dailyWeatherJsonArray.length();index++)
        {
            this.forecast.add(new WeatherForecast(dailyWeatherJsonArray.getJSONObject(index),timezoneOffset));
        }

    }

}
