package mtaWeather.controller;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mtaWeather.Exceptions.weatherException;

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

    @FXML
    private Label labelBanner;

    @FXML
    private ImageView countryFlagImg;

    private citiesData loadedCities;

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
            this.loadedCities = new citiesData();
            countryDropDown.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        loadCityDropDown(newValue.toString());
                    });

            //Event Listener for city select
            cityDropDown.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        if(newValue != null) {
                            this.labelBanner.setText("Currently displaying weather in: " + newValue.toString() + ", " + countryDropDown.getValue().toString());
                            String iso2= this.loadedCities.getIsoOfCountry(countryDropDown.getValue().toString());
                            if(iso2 != null) {
                                String imgUrl=FlagApiUser.getImageOfFlagUrl(iso2);
                                this.countryFlagImg.setImage(new Image(imgUrl));
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
