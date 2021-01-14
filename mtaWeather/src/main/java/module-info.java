module mtaWeather {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    requires org.json;
    opens mtaWeather.controller;
    opens mtaWeather;


}