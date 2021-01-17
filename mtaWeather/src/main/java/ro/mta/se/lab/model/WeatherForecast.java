package ro.mta.se.lab.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ro.mta.se.lab.Exceptions.BadJSONException;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
/**
 * Model for a weather forecast for a day

 *
 *
 * @author Vlad Florea
 */

public class WeatherForecast {

    /**
     * Static members for accessing the JSOn response from the API
     */
    private static final String listIdentifierArrayJSON="list";
    private static final String coordArrayJSON="coord";
    private static final String lngJSON="lon";
    private static final String latJSON="lat";
    private static final String weatherArrayJSON="weather";
    private static final String weatherDescriptionJSON="description";
    private static final String weatherIconJSON="icon";
    private static final String mainArrayJSON="main";
    private static final String tempJSON="temp";
    private static final String dayTempJSON="day";
    private static final String feelsLikeJSON="feels_like";
    private static final String tempMinJSON="temp_min";
    private static final String tempMaxJSON="temp_max";
    private static final String pressureJSON="pressure";
    private static final String humidityJSON="humidity";
    private static final String visibilityJSON="visibility";
    private static final String windArrayJSON="wind";
    private static final String windJSON="wind_speed";
    private static final String cloudsArray="clouds";
    private static final String cloudsAllJSON="all";
    private static final String dtJSON="dt";
    private static final String timezoneJSON="timezone";

    private static final String celsiusMetric="°C";
    private static final String windMetric="m/s";
    private static final String percentMetric="%";


    /**
     * Latitude and longitude
     */
    private StringProperty lat;

    public StringProperty getLat(){
        return lat;
    }
    private StringProperty lng;
    public  StringProperty getLng(){
        return lng;
    }

    /**
     * Weather Main description
     */
    private StringProperty weatherMainDescription;
    public StringProperty getWeatherMainDescription(){
        return weatherMainDescription;
    }

    /**
     * Weather Icon
     */
    private StringProperty WeatherIcon;
    public StringProperty getWeatherIcon(){
        return WeatherIcon;
    }

    /**
     * Weather detalis regarding temperature
     */
    private StringProperty weatherTemp;
    public  StringProperty getWeatherTemp(){
        return new SimpleStringProperty(weatherTemp.getValue()+" "+celsiusMetric);
    }
    private StringProperty weatherFeelsLike;
    public StringProperty getWeatherFeelsLike(){
        return weatherFeelsLike;
    }


    /**
     * Maximum and minimum temperature at the moment
     */
    private StringProperty tempMin;
    public StringProperty getTempMin(){
        return tempMin;
    }
    private StringProperty tempMax;
    public StringProperty getTempMax(){
        return tempMax;
    }


    /**
     * Humidity
     */
    private StringProperty humidity;
    public StringProperty getHumidity(){

        return new SimpleStringProperty(humidity.getValue()+" "+percentMetric);
    }


    /**
     * Cloudiness
     */
    private StringProperty cloudsPercent;
    public StringProperty getCloudsPercent(){

        return new SimpleStringProperty(cloudsPercent.getValue()+" "+percentMetric);
    }
    /**
     * Time of data calculation UTC
     */
    private StringProperty dt;

    /**
     * Wind value m/s
     */
    private StringProperty wind;
    public StringProperty getWind(){
        return new SimpleStringProperty(wind.getValue() +" "+windMetric);
    }

    /**
     * Constructor from a JSON Object and with a specified shiftTimezone from UTC
     * @param weatherJSON -> JSON Object received from api
     * @param shiftTimezone -> shift time(unix time) from UTC.
     * @throws BadJSONException
     */
    public WeatherForecast(JSONObject weatherJSON, Long shiftTimezone) throws BadJSONException {
        try {
            /**
             * Access dt member
             */
            Long dtUTC = Long.parseLong(weatherJSON.get(dtJSON).toString());
            Long crtTime = dtUTC + shiftTimezone;
            this.dt = new SimpleStringProperty(crtTime.toString());
            /**
             * Access main description
             * weatherDescriptionJSON
             */
            this.weatherMainDescription = new SimpleStringProperty(weatherJSON.getJSONArray(weatherArrayJSON).getJSONObject(0).get(weatherDescriptionJSON).toString());

            /**
             * Access weather icon
             */

            this.WeatherIcon = new SimpleStringProperty(weatherJSON.getJSONArray(weatherArrayJSON).getJSONObject(0).get(weatherIconJSON).toString());

            /**
             * Access main temperature values
             */

            try {
                this.weatherTemp = new SimpleStringProperty(weatherJSON.getJSONObject(tempJSON).get(dayTempJSON).toString());
            } catch (JSONException e) {
                this.weatherTemp = new SimpleStringProperty(weatherJSON.get(tempJSON).toString());
            }
            /**
             * Get weather feels like
             */
            try {
                this.weatherFeelsLike = new SimpleStringProperty(weatherJSON.getJSONObject(feelsLikeJSON).get(dayTempJSON).toString());
            } catch (JSONException e) {
                this.weatherFeelsLike = new SimpleStringProperty(weatherJSON.get(feelsLikeJSON).toString());
            }
            /**
             * Get temp min& max
             */
            try {
                this.tempMin = new SimpleStringProperty(weatherJSON.getJSONObject(tempJSON).get(tempMinJSON).toString());
                this.tempMax = new SimpleStringProperty(weatherJSON.getJSONObject(tempJSON).get(tempMaxJSON).toString());
            } catch (JSONException e) {
                this.tempMin = null;
                this.tempMax = null;
            }
            /**
             *Get humidity
             */
            this.humidity = new SimpleStringProperty(weatherJSON.get(humidityJSON).toString());

            /**
             * Get clouds percent
             */
            this.cloudsPercent = new SimpleStringProperty(weatherJSON.get(cloudsArray).toString());

            /**
             * get Wind
             */
            this.wind = new SimpleStringProperty(weatherJSON.get(windJSON).toString());

        }
        catch (JSONException e){
            throw new BadJSONException("Bad response received from the weather server!");
        }
    }

    /**
     * Method that computes the current date as dd/MM/yyyy
     * @return Current Date
     */
    public StringProperty getCrtDate(){
        try {
            Long crtUtc = Long.parseLong(this.dt.getValue().toString());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String formattedDtm = Instant.ofEpochSecond(crtUtc).atZone(ZoneId.of("UTC")).format(formatter);
            return new SimpleStringProperty(formattedDtm);
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * Method that computes the current Time as HH:mm
     * @return Current Time
     */
    public StringProperty getCrtTime()
    {
        try {
            Long crtUtc = Long.parseLong(this.dt.getValue().toString());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            String formattedDtm = Instant.ofEpochSecond(crtUtc).atZone(ZoneId.of("UTC")).format(formatter);
            return new SimpleStringProperty(formattedDtm);
        }
        catch (Exception e){
            return null;
        }
        }





}
