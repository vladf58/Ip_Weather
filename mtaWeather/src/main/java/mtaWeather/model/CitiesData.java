package mtaWeather.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;


public class CitiesData {
    private ArrayList<City> citiesDB;

    public City getCity(String country, String cityName){
        for(City city : citiesDB){
            if(city.getName().getValue().equals(cityName) && city.getCountry().getValue().equals(country)){
                return city;
            }
        }
        return null;
    }

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
