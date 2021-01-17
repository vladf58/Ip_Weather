package ro.mta.se.lab.controller;

import ro.mta.se.lab.Exceptions.BadCSVException;
import ro.mta.se.lab.model.CitiesData;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class LocationFactoryTest {
    LocationFactory factory;
    CitiesData citiesDBMock;

    @BeforeEach
     void initMembers(){
        factory=new LocationFactory();
        citiesDBMock= mock(CitiesData.class);
    }

    @Test
     void testGenerateCitiesFromDefaultCsv() {
        /**
         * Check to see we generate a CitiesData object
         */
        try {
            Assertions.assertEquals(factory.generateCitiesFromDefaultCsv().getClass(), CitiesData.class);
        }
        catch (BadCSVException e){
            fail("The test threw an exception, when it shouldn't");
        }
    }

    /**
     * Check to see if we throw errors for a bad CSV
     */
    @Test
     void testGenerateCitiesFromCsv() {
        Assertions.assertThrows(BadCSVException.class, () -> {
            factory.generateCitiesFromCsv("BadCSV");
        });
    }

}