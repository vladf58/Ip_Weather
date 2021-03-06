package ro.mta.se.lab.controller;

import ro.mta.se.lab.Exceptions.BadConnException;
import ro.mta.se.lab.Exceptions.InvalidCityException;
import ro.mta.se.lab.model.City;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class responsible for using the OpenWeather api
 * Credits goes to https://openweathermap.org for providing the api
 * @author Vlad Florea
 */

public class WeatherApiUser {
    /**
     * API key
     */
    private static final String APIkey="b66e54e6b4a9c5f582989f8a2d459700";
    /**
     * Base url
     */
    private static final String baseUrl="https://api.openweathermap.org/data/2.5/onecall?";

    /**
     * URL args
     */
    private static final String excludeValues ="&exclude=minutely,hourly,alerts";
    private static final String langArgs="&lang=en";
    private static final String unitsArgs="&units=metric";
    private static final String appidArg="&appid=";

    /**
     * Method responsible for making a GET request to given url and returning the JSON respons
     * @param urlAddr
     * @return Json as a string
     * @throws BadConnException
     */
    private static String getServerResponse(String urlAddr) throws BadConnException{
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
            throw new BadConnException("Could not get a response from the OpenWeather Server for the specified query!");
        }

    }

    /**
     * Method responsible for getting the forecast for a given city
     * @param city
     * @return JSONObject -> Server's response as a JSON
     * @throws BadConnException
     */
    public static JSONObject getWeatherByCity(City city) throws  BadConnException, InvalidCityException {
        String lat, lng;
        try {
             lat = city.getLat().getValue().toString();
             lng = city.getLng().getValue().toString();
        }
        catch (Exception e){
            throw new InvalidCityException("The selected city is invalid! Could not access lat&lng. Might be null");
        }
            String urlAddr = baseUrl+"lat="+lat+"&lon="+lng+excludeValues+unitsArgs+langArgs+appidArg+APIkey;

            return new JSONObject(getServerResponse(urlAddr));

    }
    /**
     * Method responsible for getting the forecast for a given combination of lat and long
     * @param lat,lng
     * @return JSONObject -> Server's response as a JSON
     * @throws BadConnException
     */
    public static JSONObject getWeatherByCoord(String lat, String lng) throws  BadConnException{
            String urlAddr = baseUrl+"lat="+lat+"&lon="+lng+excludeValues+unitsArgs+langArgs+appidArg+APIkey;
            return new JSONObject(getServerResponse(urlAddr));
    }


}
