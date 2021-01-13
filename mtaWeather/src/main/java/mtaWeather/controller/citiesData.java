package mtaWeather.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import mtaWeather.Exceptions.exceptionBadCSV;
import mtaWeather.Exceptions.weatherException;
import mtaWeather.model.City;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class citiesData {
    private static final String defaultCitiesFile="/cities/worldcities.csv";
    //private static final String defaultCitiesFile="/cities/test.txt";

    private static final String defaultColumns[]= {"city","lat","lng","country","id","iso2"};

    private List<City> citiesDB;

    private void initFromCSV(String csvFile) throws weatherException {

        BufferedReader CSVReader;
        citiesDB = new ArrayList<City>();

        try{

            InputStream is = this.getClass().getResourceAsStream(defaultCitiesFile);
            if (is == null) {
                throw new exceptionBadCSV("Cannot open the csv file");
            }
            InputStreamReader isr = new InputStreamReader(is);
            CSVReader = new BufferedReader(isr);

            String line =CSVReader.readLine();
            if(line == null){
                throw new exceptionBadCSV("The format of the csv file is wrong");
            }

            String columns[] = line.split(",");
            if(columns.length != defaultColumns.length){
                throw new exceptionBadCSV("Bad first line of csv");
            }

            for(Integer i=0; i<columns.length;i++){
                if(!defaultColumns[i].equals(columns[i])){
                    throw new exceptionBadCSV("The header of the csv doesn't match with the format! Expected:"+defaultColumns[i]+" but is:"+columns[i]);
                }
            }

            line = CSVReader.readLine();
            while(line!=null){
                columns = line.split(",");

                if(columns.length != defaultColumns.length){
                    throw new exceptionBadCSV("Bad input on csv file-> City="+columns[0]);
                }

                String name = columns[0];
                String country =columns[3];
                Integer id = Integer.parseInt(columns[4]);
                Double lat = Double.parseDouble(columns[1]);
                Double lng = Double.parseDouble(columns[2]);
                String iso2 = columns[5];

                City newCity = new City(new SimpleStringProperty(name),new SimpleStringProperty(country), new SimpleIntegerProperty(id),new SimpleDoubleProperty(lat),new SimpleDoubleProperty(lng), new SimpleStringProperty(iso2));
                this.citiesDB.add(newCity);
                line = CSVReader.readLine();

            }
        }
        catch(Exception e){
            //To do: Log exception

            throw new exceptionBadCSV("Error with CSV file:"+e.getMessage());
        }


    }

    public citiesData(String inFile) throws weatherException{
        initFromCSV(inFile);
    }
    public citiesData() throws weatherException{
        initFromCSV(defaultCitiesFile);
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
