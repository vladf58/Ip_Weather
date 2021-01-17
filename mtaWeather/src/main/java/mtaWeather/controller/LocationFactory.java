package mtaWeather.controller;

import javafx.beans.property.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import mtaWeather.Exceptions.BadCSVException;
import mtaWeather.model.CitiesData;
import mtaWeather.model.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Optional;

/**
 *This Class is responsible for generating CitiesData objects (see model package)
 *
 * @author Vlad Florea
 */
public class LocationFactory {
    /**
     * Local definitions
     */
    private static final String defaultCitiesFile="/cities/worldcities.csv";
    private static final String defaultColumns[]= {"city","lat","lng","country","id","iso2"};
    private static final String defaultID="0";
    private static final String defaultISO2="null";
    private static final String CSVSeparator=",";

    /**
     * Method that generates a CitiesData object based on the default .csv file
     * @return CitiesData
     * @throws BadCSVException
     */
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
    /**
     * Method that generates a CitiesData object based on a given .csv file
     * @return CitiesData
     * @throws BadCSVException
     */
    public  CitiesData generateCitiesFromCsv(String csvFile) throws BadCSVException {
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

    /**
     * Method for adding a new location to a specific csv location file
     * @param CSVFile
     * @param cityName
     * @param country
     * @param lat
     * @param lng
     * @return
     */
    public CitiesData addNewCityToCSVFile(String CSVFile,String cityName, String country,String lat, String lng){
        /**
         * Verify city name
         *
         */
        if (cityName.contains(CSVSeparator)){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location because the city name contains the CSV separator:"+CSVSeparator+". Try removing it!");
            errorAlert.showAndWait();
            return null;

        }
        /**
         * Verify country name
         */
        if (cityName.contains(CSVSeparator)){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location because the country name contains the CSV separator:"+CSVSeparator+". Try removing it!");
            errorAlert.showAndWait();
            return null;

        }
        /**
         * Try parsing the latitude, make sure is a number
         */
        try{
            Double.parseDouble(lat);
        }
        catch (NumberFormatException e){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location because the latitude is not a number");
            errorAlert.showAndWait();
            return null;
        }

        /**
         * Try parsing the longitude, make sure is a number
         */
        try{
            Double.parseDouble(lng);
        }
        catch (NumberFormatException e){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location because the longitude is not a number");
            errorAlert.showAndWait();
            return null;
        }

        /**
         * Regenerate the current cities from the default CSV
         */
        CitiesData defaultDB;
        try {
            defaultDB = generateCitiesFromCsv(CSVFile);
        }
        catch (BadCSVException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location:"+e.getMessage());
            errorAlert.showAndWait();
            return null;
        }

        /**
         * Check to see if the country exists
         */
        if(!defaultDB.getCountries().contains(country)){
            /**
             * The country doesn't exist in the csv --> Can't search for the flag
             * The user might not want to add it
             */
            Alert alert = new Alert(Alert.AlertType.WARNING, "The country you want to add is not in the current CSV file, you can still add it but the app won't be able to display the flag. Do you still want to add it?");
            alert.setTitle("Missing Country.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() != ButtonType.OK) {
                return null;
            }
        }

        /**
         * Add the new country
         */
        String strToAppend= System.lineSeparator()+cityName+","+lat+","+lng+","+country+","+defaultID+","+defaultISO2;
        try {
            Files.write(Paths.get(this.getClass().getResource(CSVFile).toString()), strToAppend.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location:"+e.getMessage());
            errorAlert.showAndWait();
            return null;
        }

        /**
         * Reload the cities database. The new location could have corrupted the file.
         */

        try {
            defaultDB = generateCitiesFromCsv(CSVFile);
        }
        catch (BadCSVException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("The new location corrupted the csv file:"+e.getMessage());
            errorAlert.showAndWait();
            return null;
        }
        return  defaultDB;


    }


    /**
     * Method for adding a new location to the default csv location file.
     * @param cityName
     * @param country
     * @param lat
     * @param lng
     * @return
     */
    public CitiesData addNewCityToDefaultCSV(String cityName, String country,String lat, String lng){
        /**
         * The ISO2 id for the country
         */
        String newISO2 = defaultISO2;
        /**
         * Verify city name
         *
         */
        if (cityName.contains(CSVSeparator)){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location because the city name contains the CSV separator:"+CSVSeparator+". Try removing it!");
            errorAlert.showAndWait();
            return null;

        }
        /**
         * Verify country name
         */
        if (cityName.contains(CSVSeparator)){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location because the country name contains the CSV separator:"+CSVSeparator+". Try removing it!");
            errorAlert.showAndWait();
            return null;

        }
        /**
         * Try parsing the latitude, make sure is a number
         */
        try{
            Double.parseDouble(lat);
        }
        catch (NumberFormatException e){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location because the latitude is not a number");
            errorAlert.showAndWait();
            return null;
        }

        /**
         * Try parsing the longitude, make sure is a number
         */
        try{
            Double.parseDouble(lng);
        }
        catch (NumberFormatException e){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location because the longitude is not a number");
            errorAlert.showAndWait();
            return null;
        }

        /**
         * Regenerate the current cities from the default CSV
         */
        CitiesData defaultDB;
        try {
            defaultDB = generateCitiesFromDefaultCsv();
        }
        catch (BadCSVException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location:"+e.getMessage());
            errorAlert.showAndWait();
            return null;
        }

        /**
         * Check to see if the country exists
         */
        if(!defaultDB.getCountries().contains(country)){
            /**
             * The country doesn't exist in the csv --> Can't search for the flag
             * The user might not want to add it
             */
            Alert alert = new Alert(Alert.AlertType.WARNING, "The country you want to add is not in the current CSV file, you can still add it but the app won't be able to display the flag. Do you still want to add it?");
            alert.setTitle("Missing Country.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() != ButtonType.OK) {
                return null;
            }
        }
        else {
            /**
             * Check to see is not a duplicate
             */
            if (defaultDB.getCity(country,cityName) !=null){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("There was an exception");
                errorAlert.setContentText("Could not add the new location because is a duplicate!");
                errorAlert.showAndWait();
                return null;
            }
            /**
             * Set the iso2
             */
            newISO2 =defaultDB.getIsoOfCountry(country);


        }

        /**
         * Add the new country
         *
         */

        String strToAppend= System.lineSeparator()+cityName+","+lat+","+lng+","+country+","+defaultID+","+newISO2;
        try {
            Files.write(Paths.get(this.getClass().getResource(defaultCitiesFile).toURI()), strToAppend.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException | URISyntaxException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("Could not add the new location:"+e.getMessage());
            errorAlert.showAndWait();
            return null;
        }

        /**
         * Reload the cities database. The new location could have corrupted the file.
         */

        try {
            defaultDB = generateCitiesFromDefaultCsv();
        }
        catch (BadCSVException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText("The new location corrupted the csv file:"+e.getMessage());
            errorAlert.showAndWait();
            return null;
        }
        return  defaultDB;
    }
    /**
     * Classes Constructor
     */
    public LocationFactory(){


    }

}
