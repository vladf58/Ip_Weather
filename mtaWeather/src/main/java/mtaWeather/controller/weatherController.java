package mtaWeather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import mtaWeather.Exceptions.weatherException;
import mtaWeather.model.CitiesData;
import mtaWeather.model.City;
import mtaWeather.model.WeatherForecast;
import mtaWeather.model.WeekForecast;

import java.util.ArrayList;

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

    @FXML
    private ImageView tempImg;

    @FXML
    private ImageView humidityImg;

    @FXML
    private ImageView windImg;

    private ArrayList<ImageView> dayImgs;
    private ArrayList<Label> dayLabels;
    private ArrayList<GridPane> dayGrids;

    private CitiesData loadedCities;

    private City crtCity;

    private WeekForecast crtForecast;

    private void loadCoutryDropDown(){
        countryDropDown.setItems(loadedCities.getCountries());
        countryDropDown.setValue(loadedCities.getCountries().get(0));
    }

    private void setLocationOnIp(){
        countryDropDown.setValue(IpApiUser.getCityByCrtIp(loadedCities).getCountry().getValue());
        cityDropDown.setValue(IpApiUser.getCityByCrtIp(loadedCities).getName().getValue());

    }


    private void loadCityDropDown(String Country){
        cityDropDown.setItems(loadedCities.getCities(countryDropDown.getValue().toString()));
        cityDropDown.setValue(cityDropDown.getItems().get(0));

    }

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

    @FXML
    private void setForecastImg(){
        this.tempImg.setImage(new Image(this.getClass().getResource("/icons/thermometer.png").toString()));

        this.humidityImg.setImage(new Image(this.getClass().getResource("/icons/rain.png").toString()));

        this.windImg.setImage(new Image(this.getClass().getResource("/icons/wind.png").toString()));


    }

    @FXML
    private void initialize(){
        try {
            /**
             * Init arrays
             */
            initDaysContent();
            /**
             * Load cities
             */
            this.loadedCities = new CitiesData();

            /**
             * Set static images
             */
            this.countryFlagImg.preserveRatioProperty().setValue(false);
            this.weatherIconImg.preserveRatioProperty().setValue(false);
            this.windImg.preserveRatioProperty().setValue(false);
            this.humidityImg.preserveRatioProperty().setValue(false);
            this.tempImg.preserveRatioProperty().setValue(false);
            setForecastImg();

            /**
             * Add events
             */
            countryDropDown.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        //Event code
                        loadCityDropDown(newValue.toString());
                    });

            //Event Listener for city select
            cityDropDown.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        if(newValue != null) {

                            /**
                             * Display banner
                             */
                            this.labelBanner.setText(newValue.toString() + ", " + countryDropDown.getValue().toString());
                            this.crtCity= this.loadedCities.getCity(countryDropDown.getValue().toString(),newValue.toString());


                            if(crtCity != null) {
                                loadCountryImg();;
                                /**
                                 * Get weather object
                                 *
                                 */
                                WeekForecast crtWeekForecast= new WeekForecast(WeatherApiUser.getWeatherByCity(crtCity));
                                WeatherForecast crtForecast = crtWeekForecast.getCrtForecast();
                                this.crtForecast =crtWeekForecast;

                                /**
                                 * Load info with the current data
                                 */
                                loadMainPannel(crtForecast);

                                /**
                                 * Load the daily forecast
                                 */
                                loadDailyPannel(crtWeekForecast);


                            }
                        }
                    });


            //Event for grid Click
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
        catch (weatherException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void loadMainPannel(WeatherForecast crtForecast){

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
        String iconUrl=WeatherIconConstructor.getImageUrl(crtForecast.getWeatherIcon().getValue());
        this.weatherIconImg.setImage(new Image(iconUrl));

        /**
         * Set description
         */
        String crtForecstDescription=crtForecast.getWeatherMainDescription().getValue();
        this.weatherDescriptionLabel.setText(crtForecstDescription.substring(0,1).toUpperCase()+crtForecstDescription.substring(1));

        /**
         * Set temp values
         */
        this.temperatureLabel.setText(crtForecast.getWeatherTemp().getValue());
        this.windLabel.setText(crtForecast.getWind().getValue());
        this.humidityLabel.setText(crtForecast.getHumidity().getValue());

    }

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

    @FXML
    private void loadCountryImg(){
        /**
         * Display picture
         */
        String imgUrl=FlagApiUser.getImageOfFlagUrl(crtCity.getIso2().getValue());


        this.countryFlagImg.setImage(new Image(imgUrl));
    }

    @FXML
    private void reinitGuiWithSelectedDay(Integer index){
        WeatherForecast crtWeather= crtForecast.getForecast().get(index);
        loadMainPannel(crtWeather);
    }

    public weatherController(){
    }

}
