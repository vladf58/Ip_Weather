package mtaWeather.controller;

public class FlagApiUser {
    private static final String baseUrl="http://www.geognos.com/api/en/countries/flag/";
    private static final String extension=".png";
    public static String getImageOfFlagUrl(String iso2){
        return baseUrl+iso2+extension;
    }



}
