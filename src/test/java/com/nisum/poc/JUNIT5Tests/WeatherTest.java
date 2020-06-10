package com.nisum.poc.JUNIT5Tests;

import com.nisum.poc.ReadProperties;
import com.nisum.poc.Weather.WeatherDetails;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.logging.Logger;

public class WeatherTest {

    private static Logger log = Logger.getLogger(String.valueOf(com.nisum.poc.TestNG.WeatherTest.class));

    @DisplayName("Initialises base URI ")
    @BeforeEach
    public void setUp(){
        RestAssured.baseURI = ReadProperties.getProperty("WeatherURI");
    }

    @DisplayName("Validates all GET weather details")
    @Test
    public void validategetWeather() {
        WeatherDetails.getweather();
    }

    @Disabled("Do not run UPDATE weather details")
    @Test
    public void validatecreateWeather(String external_id,String Name, String Latitude,String Longitude,String Altitude ) throws IOException {
        Response response= WeatherDetails.createWeather( external_id, Name, Latitude, Longitude, Altitude);
        WeatherDetails.validatepostWeather(response);
    }

    @DisplayName("Validates all Update weather details")
    @Test
    public void validateupdateWeather() throws IOException {
        Response response= WeatherDetails.updateWeather();
        WeatherDetails.validatupdateWeather(response);
    }

    @DisplayName("Validates all DELETE weather details")
    @Test
    public void validateDeleteWeather() {
        WeatherDetails.deleteWeather();
    }

    @Test
    public void complete(){
        log.info("Validated weather test class successfully");
    }
}

