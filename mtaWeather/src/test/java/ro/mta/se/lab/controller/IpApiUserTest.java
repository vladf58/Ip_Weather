package ro.mta.se.lab.controller;

import ro.mta.se.lab.model.CitiesData;
import ro.mta.se.lab.model.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

public class IpApiUserTest {

    CitiesData citiesDB = mock(CitiesData.class);
    City bucharestMock=mock(City.class);
    City notBucharest=mock(City.class);

    /**
     * Test class with Mockito for checking the location by Ip API.
     * The test should pass if the current ip is in Bucharest.
     */

    @BeforeEach
     void initMocks(){
        citiesDB = mock(CitiesData.class);
        bucharestMock=mock(City.class);
        notBucharest=mock(City.class);
    }

    @Test
     void testForCurrentCity(){
        when(citiesDB.getCity("Romania","Bucharest")).thenReturn(bucharestMock);

        assertSame(IpApiUser.getCityByCrtIp(citiesDB),bucharestMock);
        assertNotSame(IpApiUser.getCityByCrtIp(citiesDB),notBucharest);
    }

}