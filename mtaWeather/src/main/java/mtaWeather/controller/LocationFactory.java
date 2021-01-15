package mtaWeather.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import mtaWeather.Exceptions.BadCSVException;
import mtaWeather.model.CitiesData;
import mtaWeather.model.City;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LocationFactory {
    private static final String defaultCitiesFile="/cities/worldcities.csv";
    //private static final String defaultCitiesFile="/cities/test.txt";

    private static final String defaultColumns[]= {"city","lat","lng","country","id","iso2"};

    public  CitiesData generateCitiesFromDefaultCsv() throws BadCSVException{

        BufferedReader CSVReader;
        ArrayList<City> citiesDB = new ArrayList<City>();
        try{

            InputStream is = this.getClass().getResourceAsStream(defaultCitiesFile);
            if (is == null) {
                throw new BadCSVException("Cannot open the csv file");
            }
            InputStreamReader isr = new InputStreamReader(is);
            CSVReader = new BufferedReader(isr);

            String line =CSVReader.readLine();
            if(line == null){
                throw new BadCSVException("The format of the csv file is wrong");
            }

            String columns[] = line.split(",");
            if(columns.length != defaultColumns.length){
                throw new BadCSVException("Bad first line of csv");
            }

            for(Integer i=0; i<columns.length;i++){
                if(!defaultColumns[i].equals(columns[i])){
                    throw new BadCSVException("The header of the csv doesn't match with the format! Expected:"+defaultColumns[i]+" but is:"+columns[i]);
                }
            }

            line = CSVReader.readLine();
            while(line!=null){
                columns = line.split(",");

                if(columns.length != defaultColumns.length){
                    throw new BadCSVException("Bad input on csv file-> City="+columns[0]);
                }

                String name = columns[0];
                String country =columns[3];
                Integer id = Integer.parseInt(columns[4]);
                Double lat = Double.parseDouble(columns[1]);
                Double lng = Double.parseDouble(columns[2]);
                String iso2 = columns[5];

                City newCity = new City(new SimpleStringProperty(name),new SimpleStringProperty(country), new SimpleIntegerProperty(id),new SimpleDoubleProperty(lat),new SimpleDoubleProperty(lng), new SimpleStringProperty(iso2));
                citiesDB.add(newCity);
                line = CSVReader.readLine();
            }
            return new CitiesData(citiesDB);
        }
        catch(Exception e){
            //To do: Log exception
            throw new BadCSVException("Error with CSV file:"+e.getMessage());
        }
    }
    public   CitiesData generateCitiesFromCsv(String csvFile) throws BadCSVException {
        BufferedReader CSVReader;
        ArrayList<City> citiesDB = new ArrayList<City>();
        try{

            InputStream is = this.getClass().getResourceAsStream(csvFile);
            if (is == null) {
                throw new BadCSVException("Cannot open the csv file");
            }
            InputStreamReader isr = new InputStreamReader(is);
            CSVReader = new BufferedReader(isr);

            String line =CSVReader.readLine();
            if(line == null){
                throw new BadCSVException("The format of the csv file is wrong");
            }

            String columns[] = line.split(",");
            if(columns.length != defaultColumns.length){
                throw new BadCSVException("Bad first line of csv");
            }

            for(Integer i=0; i<columns.length;i++){
                if(!defaultColumns[i].equals(columns[i])){
                    throw new BadCSVException("The header of the csv doesn't match with the format! Expected:"+defaultColumns[i]+" but is:"+columns[i]);
                }
            }

            line = CSVReader.readLine();
            while(line!=null){
                columns = line.split(",");

                if(columns.length != defaultColumns.length){
                    throw new BadCSVException("Bad input on csv file-> City="+columns[0]);
                }

                String name = columns[0];
                String country =columns[3];
                Integer id = Integer.parseInt(columns[4]);
                Double lat = Double.parseDouble(columns[1]);
                Double lng = Double.parseDouble(columns[2]);
                String iso2 = columns[5];

                City newCity = new City(new SimpleStringProperty(name),new SimpleStringProperty(country), new SimpleIntegerProperty(id),new SimpleDoubleProperty(lat),new SimpleDoubleProperty(lng), new SimpleStringProperty(iso2));
                citiesDB.add(newCity);
                line = CSVReader.readLine();
            }
            return new CitiesData(citiesDB);
        }
        catch(Exception e){
            //To do: Log exception

            throw new BadCSVException("Error with CSV file:"+e.getMessage());
        }


    }

    public LocationFactory(){

    }

}
