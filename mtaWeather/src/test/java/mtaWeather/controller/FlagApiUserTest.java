package mtaWeather.controller;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlagApiUserTest {

    @Test
     void testForNull(){
        /**
         * If the iso2 is null the default image should be loaded
         */
        assertEquals(FlagApiUser.getImageOfFlagUrl("null"),this.getClass().getResource("/icons/compass.png").toString());
    }


    @Test
     void testForCountries(){
        String ISO2="RO";

        assertEquals(FlagApiUser.getImageOfFlagUrl(ISO2),"http://www.geognos.com/api/en/countries/flag/RO.png");

        ISO2="BG";

        assertEquals(FlagApiUser.getImageOfFlagUrl(ISO2),"http://www.geognos.com/api/en/countries/flag/BG.png");

        ISO2="RU";
        assertEquals(FlagApiUser.getImageOfFlagUrl(ISO2),"http://www.geognos.com/api/en/countries/flag/RU.png");

    }

}