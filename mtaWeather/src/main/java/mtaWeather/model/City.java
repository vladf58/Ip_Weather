package mtaWeather.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Model for a City object.
 * Holds:
 *      a name,
 *      a country,
 *      an id (currently not in use),
 *      the iso2 identifier.
 *
 *
 *
 * @author Vlad Florea
 */
public class City {
    private StringProperty name;
    public StringProperty nameProperty() {
        return name;
    }
    private StringProperty country;
    private IntegerProperty id;
    private StringProperty iso2;

    private DoubleProperty lat;

    /**
     * Getters
     *
     */
    public DoubleProperty getLat(){
        return lat;
    }
    private DoubleProperty lng;
    public DoubleProperty getLng(){
        return lng;
    }

    public StringProperty getIso2(){
        return iso2;
    }
    public StringProperty getName(){
        return  name;
    }
    public StringProperty getCountry(){
        return country;
    }

    /**
     * Constructor
     * @param name
     * @param country
     * @param id
     * @param lat
     * @param lng
     * @param iso2
     */
    public City(StringProperty name, StringProperty country, IntegerProperty id,DoubleProperty lat, DoubleProperty lng, StringProperty iso2){
        this.country=country;
        this.id=id;
        this.lat=lat;
        this.lng=lng;
        this.name=name;
        this.iso2=iso2;
    }



}
