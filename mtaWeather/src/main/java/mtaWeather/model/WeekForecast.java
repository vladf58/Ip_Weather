package mtaWeather.model;

import javafx.beans.property.SimpleStringProperty;
import mtaWeather.Exceptions.BadJSONException;
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

    public WeekForecast(JSONObject weatherJSON) throws  BadJSONException{
        /**
         * Current timezone
         */

        this.forecast = new ArrayList<WeatherForecast>();
        this.timezoneOffset = Long.parseLong(weatherJSON.get(timezoneOffsetJSON).toString());

        /**
         * Current weather
         */
        try {
            crtForecast = new WeatherForecast(weatherJSON.getJSONObject(currentJSON), timezoneOffset);
        }
        catch (BadJSONException e){
            throw new BadJSONException("Could not get the current weather from the server for this location!");
        }

        /**
         * Access days
         */
        JSONArray dailyWeatherJsonArray = weatherJSON.getJSONArray(dailyJSON);

        for (Integer index = 0; index < dailyWeatherJsonArray.length(); index++) {
            try {
                this.forecast.add(new WeatherForecast(dailyWeatherJsonArray.getJSONObject(index), timezoneOffset));
            }
            catch (BadJSONException e){
                this.forecast.add(null);
            }
            }

    }

}
