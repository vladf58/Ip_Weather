package mtaWeather.controller;

import mtaWeather.model.CitiesData;
import mtaWeather.model.City;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IpApiUser {
    //https://ipapi.co/{ip}/{format}/
    private static final String baseUrl="https://ipapi.co/json/";
    private static final String cityJSON="city";
    private static final String countryJSON="country_name";

    public static City getCityByCrtIp(CitiesData citiesDB){
        try {
            URL url = new URL(baseUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONObject respJSON=new JSONObject(content.toString());
            String cityName= respJSON.get(cityJSON).toString();
            String countryName= respJSON.get(countryJSON).toString();

            return  citiesDB.getCity(countryName,cityName);

        }
        catch (Exception e){
            return null;
        }

    }
}
