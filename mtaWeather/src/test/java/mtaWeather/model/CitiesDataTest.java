package mtaWeather.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CitiesDataTest {
    City stubCity;
    CitiesData citiesDB;
    @BeforeEach
    void init(){
        citiesDB= new CitiesData(new ArrayList<City>());

        stubCity = new City(new SimpleStringProperty("Bucharest"), new SimpleStringProperty("Romania"), new SimpleIntegerProperty(0) {
        }, new SimpleDoubleProperty(2.13), new SimpleDoubleProperty(2.14), new SimpleStringProperty("RO"));

        citiesDB.addCity(stubCity);
    }
    @Test
    void getIsoOfCountry() {
        assertEquals(citiesDB.getIsoOfCountry("Romania"),"RO");
    }

    @Test
    void getCountries() {
        ObservableList<String> expectedList = FXCollections.observableArrayList();
        expectedList.add(stubCity.getCountry().getValue());

        assertEquals(citiesDB.getCountries(),expectedList);



    }

    @Test
    void getCities() {
        ObservableList<String> expectedList = FXCollections.observableArrayList();
        expectedList.add(stubCity.getName().getValue());


        assertEquals(citiesDB.getCities("Romania"),expectedList);
    }
}