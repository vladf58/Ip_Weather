package mtaWeather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mtaWeather.Exceptions.weatherException;
import mtaWeather.model.City;
import mtaWeather.model.WeatherForecast;

public class weatherController {

    @FXML
    private ChoiceBox countryDropDown;

    @FXML
    private ChoiceBox cityDropDown;

    @FXML
    private TextField latSelect;

    @FXML
    private TextField lngSelect;

    @FXML
    private TextField citySelect;

    @FXML
    private ChoiceBox countrySelect;

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

    private CitiesData loadedCities;

    private City crtCity;

    private void loadCoutryDropDown(){
        countryDropDown.setItems(loadedCities.getCountries());
        countryDropDown.setValue(loadedCities.getCountries().get(0));

    }


    private void loadCityDropDown(String Country){
        cityDropDown.setItems(loadedCities.getCities(countryDropDown.getValue().toString()));
        cityDropDown.setValue(cityDropDown.getItems().get(0));

    }
    @FXML
    private void initialize(){
        try {
            this.loadedCities = new CitiesData();

            this.countryFlagImg.preserveRatioProperty().setValue(false);
            this.weatherIconImg.preserveRatioProperty().setValue(false);

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
                                /**
                                * Display picture
                                */
                                String imgUrl=FlagApiUser.getImageOfFlagUrl(crtCity.getIso2().getValue());


                                this.countryFlagImg.setImage(new Image(imgUrl));

                                /**
                                 * Get weather object
                                 *
                                 */
                                WeatherForecast crtForecast= new WeatherForecast(WeatherApiUser.getWeatherByCity(crtCity));

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





                        }
                        });

                loadCoutryDropDown();




        }
        catch (weatherException e){
            System.out.println(e.getMessage());
        }
    }


    public weatherController(){
    }

}
