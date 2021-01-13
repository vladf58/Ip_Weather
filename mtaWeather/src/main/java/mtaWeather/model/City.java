package mtaWeather.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class City {
    private StringProperty name;
    private StringProperty country;
    private IntegerProperty id;
    private StringProperty iso2;

    private DoubleProperty lat;
    private DoubleProperty lng;

    public StringProperty getIso2(){
        return iso2;
    }
    public StringProperty getName(){
        return  name;
    }
    public StringProperty getCountry(){
        return country;
    }
    public City(StringProperty name, StringProperty country, IntegerProperty id,DoubleProperty lat, DoubleProperty lng, StringProperty iso2){
        this.country=country;
        this.id=id;
        this.lat=lat;
        this.lng=lng;
        this.name=name;
        this.iso2=iso2;
    }



}
