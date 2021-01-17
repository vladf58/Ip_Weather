package mtaWeather.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import mtaWeather.Exceptions.BadConnException;
import mtaWeather.Exceptions.BadJSONException;
import mtaWeather.Exceptions.InvalidCityException;
import mtaWeather.Exceptions.MtaWeatherException;
import mtaWeather.model.CitiesData;
import mtaWeather.model.City;
import mtaWeather.model.WeatherForecast;
import mtaWeather.model.WeekForecast;

import java.util.ArrayList;

/**
 * The controller for the mtaWeather Application and for the weatherAppView GUI.
 *
 * @author Vlad Florea
 */

public class weatherController {

    @FXML
    private ComboBox countryDropDown;

    @FXML
    private ComboBox cityDropDown;

    @FXML
    private TextField latSelect;

    @FXML
    private TextField lngSelect;

    @FXML
    private TextField citySelect;

    @FXML
    private TextField countrySelect;

    //To do-> refactor
    @FXML
    private Label labelBanner;

    @FXML
    private ImageView countryFlagImg;

    @FXML
    private Label timeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private ImageView weatherIconImg;

    @FXML
    private Label weatherDescriptionLabel;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label windLabel;

    @FXML
    private Label humidityLabel;

    /**
     * Lower daily forecast labels
     */
    @FXML
    private Label day0Label;
    @FXML
    private Label day1Label;
    @FXML
    private Label day2Label;
    @FXML
    private Label day3Label;
    @FXML
    private Label day4Label;
    @FXML
    private Label day5Label;

    /**
     * Lower daily forecast grids
     */
    @FXML
    private GridPane day0Grid;
    @FXML
    private GridPane day1Grid;
    @FXML
    private GridPane day2Grid;
    @FXML
    private GridPane day3Grid;
    @FXML
    private GridPane day4Grid;
    @FXML
    private GridPane day5Grid;

    /**
     * Lower daily forecast images
     */
    @FXML
    private ImageView day0Img;
    @FXML
    private ImageView day1Img;
    @FXML
    private ImageView day2Img;
    @FXML
    private ImageView day3Img;
    @FXML
    private ImageView day4Img;
    @FXML
    private ImageView day5Img;

    /**
     * Weather params images
     */
    @FXML
    private ImageView tempImg;
    @FXML
    private ImageView humidityImg;
    @FXML
    private ImageView windImg;

    /**
     * The button for adding a new location.
     */
    @FXML
    private Button newLocationButton;
    /**
     * Arrays for holding lower daily forecast objects
     */
    private ArrayList<ImageView> dayImgs;
    private ArrayList<Label> dayLabels;
    private ArrayList<GridPane> dayGrids;

    /**
     * Cities data loaded from the file
     */
    private CitiesData loadedCities;

    /**
     * Current displayed city
     */
    private City crtCity;

    /**
     * Current forecast for the week
     */
    private WeekForecast crtForecast;

    /**
     * Method responsible for loading the ComboBox for the Countries
     */
    private void loadCoutryDropDown(){
        countryDropDown.setItems(loadedCities.getCountries());
        countryDropDown.setValue(loadedCities.getCountries().get(0));
    }

    /**
     * Method responsible for loading the ComboBox for the Cities
     */
    private void loadCityDropDown(String Country){
        cityDropDown.setItems(loadedCities.getCities(countryDropDown.getValue().toString()));
        cityDropDown.setValue(cityDropDown.getItems().get(0));

    }

    /**
     * Method responsible for setting the current location based on ip
     */
    private void setLocationOnIp(){
        if(IpApiUser.getCityByCrtIp(loadedCities) !=null) {
            countryDropDown.setValue(IpApiUser.getCityByCrtIp(loadedCities).getCountry().getValue());
            cityDropDown.setValue(IpApiUser.getCityByCrtIp(loadedCities).getName().getValue());
        }
    }

    /**
     * Method for adding a new location
     */
    private void addNewLocation(){
        String newCity = this.citySelect.getText();
        String newCountry= this.countrySelect.getText();
        String newLat = this.latSelect.getText();
        String newLng = this.lngSelect.getText();

        CitiesData newDB =(new LocationFactory().addNewCityToDefaultCSV(newCity,newCountry,newLat,newLng));
        if(newDB != null){

            this.loadedCities =newDB;
            loadCityDropDown(newCountry);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Location added!");
            alert.setContentText(newCity+" has been added to the cities database!");

            alert.showAndWait();
        }

    }

    /**
     * Method responsible for initializing the dayLabels, dayGrids and dayImgs arrays.
     */
    private void initDaysContent(){
        this.dayLabels = new ArrayList<Label>();
        this.dayGrids = new ArrayList<GridPane>();
        this.dayImgs=new ArrayList<ImageView>();
        /**
         * Add label days
         */
        this.dayLabels.add(day0Label);
        this.dayLabels.add(day1Label);
        this.dayLabels.add(day2Label);
        this.dayLabels.add(day3Label);
        this.dayLabels.add(day4Label);
        this.dayLabels.add(day5Label);

        /**
         * Add grid days
         */
        this.dayGrids.add(day0Grid);
        this.dayGrids.add(day1Grid);
        this.dayGrids.add(day2Grid);
        this.dayGrids.add(day3Grid);
        this.dayGrids.add(day4Grid);
        this.dayGrids.add(day5Grid);

        /**
         * Add Img days
         */
        this.dayImgs.add(day0Img);
        this.dayImgs.add(day1Img);
        this.dayImgs.add(day2Img);
        this.dayImgs.add(day3Img);
        this.dayImgs.add(day4Img);
        this.dayImgs.add(day5Img);

    }

    /**
     * Method that sets the images for the Forecast, thermometer, rain, wind.
     */
    @FXML
    private void setForecastImg(){
        this.tempImg.setImage(new Image(this.getClass().getResource("/icons/thermometer.png").toString()));
        this.humidityImg.setImage(new Image(this.getClass().getResource("/icons/rain.png").toString()));
        this.windImg.setImage(new Image(this.getClass().getResource("/icons/wind.png").toString()));


    }

    /**
     * Method that loads the main pannel with the values for a new day
     * @param crtForecast -> Current forecast for the day
     */
    @FXML
    private void loadMainPannel(WeatherForecast crtForecast) {
        if (crtForecast == null) {
            this.weatherIconImg.setImage(new Image(this.getClass().getResource("/icons/sad.png").toString()));
            this.weatherDescriptionLabel.setText("No description for this day :(!");
        }
        else {
            /**
             * Set the date
             */
            this.dateLabel.setText(crtForecast.getCrtDate().getValue());

            /**
             * Set the time
             *
             */
            this.timeLabel.setText(crtForecast.getCrtTime().getValue());

            /**
             * Set the image
             */
            String iconUrl = WeatherIconConstructor.getImageUrl(crtForecast.getWeatherIcon().getValue());
            this.weatherIconImg.setImage(new Image(iconUrl));

            /**
             * Set description
             */
            String crtForecstDescription = crtForecast.getWeatherMainDescription().getValue();
            this.weatherDescriptionLabel.setText(crtForecstDescription.substring(0, 1).toUpperCase() + crtForecstDescription.substring(1));

            /**
             * Set temp values
             */
            this.temperatureLabel.setText(crtForecast.getWeatherTemp().getValue());
            this.windLabel.setText(crtForecast.getWind().getValue());
            this.humidityLabel.setText(crtForecast.getHumidity().getValue());

        }
    }

    /**
     * Method that loads the lower grid with the daily values for the weather
     * @param crtForecast --> current forecast for the week
     */
    @FXML
    private void loadDailyPannel(WeekForecast crtForecast){
        /**
         Load images
         */

        for(Integer i=0;i<dayImgs.size();i++){
            ImageView crtImage= dayImgs.get(i);
            String iconUrl=WeatherIconConstructor.getImageUrl(crtForecast.getForecast().get(i).getWeatherIcon().getValue());
            crtImage.setImage(new Image(iconUrl));
        }

        /**
         * Load temp values
         */
        for(Integer i=0;i<dayLabels.size();i++){
            Label crtLabel=dayLabels.get(i);
            crtLabel.setText(crtForecast.getForecast().get(i).getCrtDate().getValue());
        }

    }

    /**
     * Method that loads the country flag through the FlagApiUser
     */
    @FXML
    private void loadCountryImg(){
        /**
         * Display picture
         */
        String imgUrl=FlagApiUser.getImageOfFlagUrl(crtCity.getIso2().getValue());
        this.countryFlagImg.setImage(new Image(imgUrl));
    }

    /**
     * Method that reinitializes the main pannel with the new values for a chosen day from the lower grid.
     * @param index --> day index
     */
    @FXML
    private void reinitGuiWithSelectedDay(Integer index){
        WeatherForecast crtWeather;
        try {
            crtWeather = crtForecast.getForecast().get(index);
        }
        catch (Exception e){
            crtWeather =null;
        }
        loadMainPannel(crtWeather);
    }

    /**
     * Initialize method
     */
    @FXML
    private void initialize(){
        try {
            /**
             * Initialize the arrays for the lower Grid
             */
            initDaysContent();
            /**
             * Load the Cities from the default Csv file through the factory.
             */
            this.loadedCities = new LocationFactory().generateCitiesFromDefaultCsv();

            /**
             * Set images to not resize
             */
            this.countryFlagImg.preserveRatioProperty().setValue(false);
            this.weatherIconImg.preserveRatioProperty().setValue(false);
            this.windImg.preserveRatioProperty().setValue(false);
            this.humidityImg.preserveRatioProperty().setValue(false);
            this.tempImg.preserveRatioProperty().setValue(false);

            /**
             * Set the forecast Images, i.e wind, thermometer, rain.
             */
            setForecastImg();

            /**
             * Country dropdown event.
             * Lambda function that reinitializes the the city ComboBox with respect to the new Country
             */
            countryDropDown.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        loadCityDropDown(newValue.toString());
                    });

            /**
             * City dropdown event.
             * Lambda function that gets a new forecast for the chosen city
             */
            cityDropDown.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        if(newValue != null) {
                            /**
                             * Display banner
                             */
                            this.labelBanner.setText(newValue.toString() + ", " + countryDropDown.getValue().toString());
                            /**
                             * Set the current city
                             */
                            this.crtCity= this.loadedCities.getCity(countryDropDown.getValue().toString(),newValue.toString());

                            if(crtCity != null) {
                                /**
                                 * Set the country flag
                                 */
                                loadCountryImg();
                                ;
                                /**
                                 * Get weather object
                                 *
                                 */
                                try {
                                    WeekForecast crtWeekForecast = new WeekForecast(WeatherApiUser.getWeatherByCity(crtCity));

                                    WeatherForecast crtForecast = crtWeekForecast.getCrtForecast();
                                    this.crtForecast = crtWeekForecast;

                                    /**
                                     * Load info with the current data
                                     */
                                    loadMainPannel(crtForecast);

                                    /**
                                     * Load the daily forecast
                                     */
                                    loadDailyPannel(crtWeekForecast);


                                    }
                                catch (BadConnException | BadJSONException | InvalidCityException e){
                                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                    errorAlert.setHeaderText("There was an exception");
                                    errorAlert.setContentText(e.getMessage());
                                    errorAlert.showAndWait();
                                }
                            }
                        }
                    }
                );
            /**
             * New location button clicked event
             */
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    addNewLocation();
                }
            };
            newLocationButton.setOnAction(event);


            /**
             * Load the 7 days forecast in gui
             */
            for(Integer i=0; i<dayGrids.size();i++){
                Integer finalI = i;
                dayGrids.get(i).setOnMouseClicked(observable -> {
                    reinitGuiWithSelectedDay(finalI);
                });
            }
            /**
             *
             * Display countries
             */
            loadCoutryDropDown();
            setLocationOnIp();
        }
        catch (MtaWeatherException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There was an exception");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }


    /**
     * Contructor
     */
    public weatherController(){

    }

}
