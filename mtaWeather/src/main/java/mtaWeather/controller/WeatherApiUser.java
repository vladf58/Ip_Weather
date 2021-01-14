package mtaWeather.controller;
import javafx.beans.property.StringProperty;
import mtaWeather.model.City;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//api.openweathermap.org/data/2.5/find?lat={lat}&lon={lon}&cnt={cnt}&appid={API key}

public class WeatherApiUser {
    private static final String APIkey="b66e54e6b4a9c5f582989f8a2d459700";
    private static final String baseUrl="https://api.openweathermap.org/data/2.5/find?";

    public static JSONObject getWeatherByCity(City city){
        try {
            String lat = city.getLat().getValue().toString();
            String lng = city.getLng().getValue().toString();

            String urlAddr = baseUrl+"lat="+lat+"&lon="+lng+"&units=metric"+"&lang=ro"+"&cnt=1"+"&appid="+APIkey;
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

            return new JSONObject(content.toString());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static JSONObject getWeatherByCoord(String lat, String lng){
        try{
            String urlAddr = baseUrl+"lat=+lat"+"&lon="+lng+"&units=metric"+"&lang=ro"+"&cnt=1"+"&appid="+APIkey;
            URL url = new URL(urlAddr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return new JSONObject(con.getResponseMessage());
        }
        catch (Exception e){
            e.getMessage();
            return null;
        }
    }

    public static JSONObject getWeatherByCityName(String cityName){
        try{
            String urlAddr = baseUrl+"q="+cityName+"&units=metric"+"&lang=ro"+"&cnt=1"+"&appid="+APIkey;
            URL url = new URL(urlAddr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return new JSONObject(con.getResponseMessage());
        }
        catch (Exception e){
            e.getMessage();
            return null;
        }
    }

}
