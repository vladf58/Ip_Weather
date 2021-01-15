package mtaWeather.controller;

/**
 *Static class responsible to generate the URL of flag photos.
 * Credits goes to: http://www.geognos.com/ for providing the API
 * @author Vlad Florea
 *
 */
public class FlagApiUser {
    /**
     * Base url for the API
     */
    private static final String baseUrl="http://www.geognos.com/api/en/countries/flag/";
    /**
     * Extension of the image
     */
    private static final String extension=".png";

    /**
     * Method that returns the URL based on the iso2 identifier of the country
     * @param iso2
     * @return
     */
    public static String getImageOfFlagUrl(String iso2){
        return baseUrl+iso2+extension;
    }



}
