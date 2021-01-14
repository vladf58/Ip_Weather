package mtaWeather.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.json.JSONObject;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class WeatherForecast {
    /**
     * Default values for JSON
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
    private static final String feelsLikeJSON="feels_like";
    private static final String tempMinJSON="temp_min";
    private static final String tempMaxJSON="temp_max";
    private static final String pressureJSON="pressure";
    private static final String humidityJSON="humidity";
    private static final String visibilityJSON="visibility";
    private static final String windArrayJSON="wind";
    private static final String windJSON="speed";
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
    public StringProperty getDt(){
        return dt;
    }

    /**
     * Shift in seconds from UTC
     */
    private StringProperty timezone;
    public StringProperty getTimezone(){
        return timezone;
    }

    private StringProperty wind;
    public StringProperty getWind(){
        return new SimpleStringProperty(wind.getValue() +" "+windMetric);
    }
    public WeatherForecast(JSONObject responseJSON){

        /**
         * Acces the coordinates
         */
        JSONObject weatherJSON= responseJSON.getJSONArray(listIdentifierArrayJSON).getJSONObject(0);
        this.lat= new SimpleStringProperty(weatherJSON.getJSONObject(coordArrayJSON).get(latJSON).toString());
        this.lng = new SimpleStringProperty(weatherJSON.getJSONObject(coordArrayJSON).get(lngJSON).toString());


        /**
         * Access main description
         * weatherDescriptionJSON
         */
        this.weatherMainDescription=new SimpleStringProperty(weatherJSON.getJSONArray(weatherArrayJSON).getJSONObject(0).get(weatherDescriptionJSON).toString());
        this.WeatherIcon = new SimpleStringProperty(weatherJSON.getJSONArray(weatherArrayJSON).getJSONObject(0).get(weatherIconJSON).toString());

        /**
         * Access main temperature values
         */

        this.weatherTemp = new SimpleStringProperty(weatherJSON.getJSONObject(mainArrayJSON).get(tempJSON).toString());
        this.weatherFeelsLike = new SimpleStringProperty(weatherJSON.getJSONObject(mainArrayJSON).get(feelsLikeJSON).toString());
        this.tempMin = new SimpleStringProperty(weatherJSON.getJSONObject(mainArrayJSON).get(tempMinJSON).toString());
        this.tempMax = new SimpleStringProperty(weatherJSON.getJSONObject(mainArrayJSON).get(tempMaxJSON).toString());
        this.humidity = new SimpleStringProperty(weatherJSON.getJSONObject(mainArrayJSON).get(humidityJSON).toString());

        /**
         * Get clouds percent
         */
        this.cloudsPercent = new SimpleStringProperty(weatherJSON.getJSONObject(cloudsArray).get(cloudsAllJSON).toString());

        /**
         * get Wind
         */
        this.wind=new SimpleStringProperty(weatherJSON.getJSONObject(windArrayJSON).get(windJSON).toString());

        /**
         * Get UTC
         */
        this.dt= new SimpleStringProperty(weatherJSON.get(dtJSON).toString());

        /**
         * Get shift timezone
         *
         */
        try {
            this.timezone = new SimpleStringProperty(weatherJSON.get(timezoneJSON).toString());
        }
        catch (Exception e){
            this.timezone =new SimpleStringProperty("0");
        }

    }

    public StringProperty getCrtDate(){
        Long crtUtc = Long.parseLong(this.dt.getValue().toString());
        Long diffUtc = Long.parseLong(this.timezone.getValue().toString());

        Long crtTimeUnix = crtUtc+diffUtc;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD-MM-YYYY");

        String formattedDtm = Instant.ofEpochSecond(crtTimeUnix).atZone(ZoneId.of("UTC")).format(formatter);
        return new SimpleStringProperty(formattedDtm);
    }

    public StringProperty getCrtTime(){
        Long crtUtc = Long.parseLong(this.dt.getValue().toString());
        Long diffUtc = Long.parseLong(this.timezone.getValue().toString());

        Long crtTimeUnix = crtUtc+diffUtc;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

         String formattedDtm = Instant.ofEpochSecond(crtTimeUnix).atZone(ZoneId.of("UTC")).format(formatter);
        return new SimpleStringProperty(formattedDtm);
    }





}
