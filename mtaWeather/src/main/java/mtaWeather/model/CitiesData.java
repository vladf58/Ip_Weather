package mtaWeather.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

/**
 * Model for a City database.
 * Holds an array of cities.
 * Object generated through LocationFactory
 * @author Vlad Florea
 */

public class CitiesData {
    private ArrayList<City> citiesDB;

    /**
     * Method that searches for a city based on a country & a name
     * @param country
     * @param cityName
     * @return City if exists else null
     */
    public City getCity(String country, String cityName){
        for(City city : citiesDB){
            if(city.getName().getValue().equals(cityName) && city.getCountry().getValue().equals(country)){
                return city;
            }
        }
        return null;
    }

    /**
     * Contructor
     * @param citiesDB
     */
    public CitiesData(ArrayList<City> citiesDB ){
        this.citiesDB = citiesDB;

    }
    public String getIsoOfCountry(String country){
        for(City city: citiesDB){
            if(city.getCountry().getValue().toString().equals(country)){
                return city.getIso2().getValue().toString();
            }
        }
        return null;
    }


    /**
     * Get all the unique countries from all the locations
     * @return ObservableList of countries names.
     */
    public ObservableList<String> getCountries(){
        ObservableList<String> Countries = FXCollections.observableArrayList();
        for (City crtCity : citiesDB ) {
            if(!Countries.contains(crtCity.getCountry().getValue())){
                Countries.add(crtCity.getCountry().getValue());
            }
        }

        Countries.sort(String.CASE_INSENSITIVE_ORDER);
        return Countries;
    }

    /**
     * Get all the cities countries from a country
     * @return ObservableList of cities names.
     */
    public ObservableList<String> getCities(String Country){
        ObservableList<String> cities = FXCollections.observableArrayList();
        for(City crtCity: citiesDB){
            if(crtCity.getCountry().getValue().equals(Country)){
                cities.add(crtCity.getName().getValue());
            }
        }

        cities.sort(String.CASE_INSENSITIVE_ORDER);
        return cities;
    }
}
