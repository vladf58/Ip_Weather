package mtaWeather.controller;
import javafx.beans.property.StringProperty;
import mtaWeather.Exceptions.BadConnException;
import mtaWeather.model.City;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//api.openweathermap.org/data/2.5/find?lat={lat}&lon={lon}&cnt={cnt}&appid={API key}
//https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}

public class WeatherApiUser {
    private static final String APIkey="b66e54e6b4a9c5f582989f8a2d459700";
    private static final String baseUrl="https://api.openweathermap.org/data/2.5/onecall?";

    private static final String excludeValues ="&exclude=minutely,hourly,alerts";
    private static final String langArgs="&lang=en";
    private static final String unitsArgs="&units=metric";
    private static final String appidArg="&appid=";

    private static String getJsonResponse(String urlAddr) throws BadConnException{
        try {
            URL url = new URL(urlAddr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
        }
        catch (Exception e){
            throw new BadConnException("Could not connect to OpenWeather Server!");
        }

    }

    public static JSONObject getWeatherByCity(City city) throws  BadConnException{
            String lat = city.getLat().getValue().toString();
            String lng = city.getLng().getValue().toString();

            String urlAddr = baseUrl+"lat="+lat+"&lon="+lng+unitsArgs+langArgs+appidArg+APIkey;

            return new JSONObject(getJsonResponse(urlAddr));

    }
    public static JSONObject getWeatherByCoord(String lat, String lng) throws  BadConnException{
            String urlAddr = baseUrl+"lat="+lat+"&lon="+lng+unitsArgs+langArgs+appidArg+APIkey;
            return new JSONObject(getJsonResponse(urlAddr));
    }


}
