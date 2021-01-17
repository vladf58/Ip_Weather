package mtaWeather.controller;

import mtaWeather.Exceptions.InvalidCityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WeatherApiUserTest  {



    @Test
     void testGetWeatherByCityNull() {
        /**
         * Test with a null city
         */
        try {
            WeatherApiUser.getWeatherByCity(null);
            fail("should've thrown an exception!");
        } catch (Exception expected) {
            assertEquals(InvalidCityException.class,expected.getClass());
        }
    }

}