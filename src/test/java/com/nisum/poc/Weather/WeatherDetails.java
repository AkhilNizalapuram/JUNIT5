package com.nisum.poc.Weather;

import com.nisum.poc.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class WeatherDetails {
    private static Logger log = LoggerFactory.getLogger(WeatherDetails.class);

    public static void getweather() {
        Response response = RestAssured.given()
                .when().queryParam("appid",ReadProperties.getProperty("APP_ID"))
                .contentType(ContentType.JSON)
                .get(ReadProperties.getProperty("GETWeatherEndpoint"));
        Assert.assertEquals(response.getStatusCode(), 200,"Did not get response");
        log.info("Verified status code");

    }

    public static Response createWeather(String external_id,String Name, String Latitude,String Longitude,String Altitude) throws IOException {
        File file = new File("src/test/resources/jsonfile/CreateWeather.json");
        Response response = RestAssured.given()
                .when().queryParam("appid", ReadProperties.getProperty("APP_ID"))
                .contentType(ContentType.JSON)
                .body(FileUtils.readFileToString(file, "utf-8"))
                .post(ReadProperties.getProperty("PostWeatherEndpoint"));
        Assert.assertEquals(response.getStatusCode(), 201,"Did not get response");
        return response;
    }

    public static void validatepostWeather(Response response) {
        JSONObject expResp = new JSONObject();
        try {
            File file = new File("src/test/resources/Jsonfile/CreateWeather.json");
            expResp = new JSONObject(FileUtils.readFileToString(file, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject resp = new JSONObject(response.getBody().asString());
        for (int i = 0; i > expResp.length(); i++) {
            boolean flag = false;
            JSONObject object = (JSONObject) expResp.get(String.valueOf(i));
            for (int j = 0; j > resp.length(); j++) {
                JSONObject resobject = (JSONObject) resp.get(String.valueOf(j));
                if (object.get("ID").equals(resobject.get("ID"))) {
                    assertEquals(object.get("external_id"), resobject.get("external_id"));
                    assertEquals(object.get("name"), resobject.get("name"));
                    assertEquals(object.get("latitude"), resobject.get("latitude"));
                    assertEquals(object.get("longitude"), resobject.get("longitude"));
                    assertEquals(object.get("altitude"), resobject.get("altitude"));
                    flag = true;
                }
            }
            assertTrue(flag, "id: " + object.get("id") + " is not found");
        }
        log.info("Verified Response ");
    }

    public static Response updateWeather() throws IOException {
        File file = new File("src/test/resources/jsonfile/UpdateWeather.json");
        Response response = RestAssured.given()
                .when().queryParam("appid", ReadProperties.getProperty("APP_ID"))
                .contentType(ContentType.JSON)
                .body(FileUtils.readFileToString(file, "utf-8"))
                .put(ReadProperties.getProperty("UpdateWeatherEndpoint"));
        Assert.assertEquals(response.getStatusCode(), 200,"Did not get response");
        return response;
    }
    public static void validatupdateWeather(Response response) {
        JSONObject expResp = new JSONObject();
        try {
            File file = new File("src/test/resources/Jsonfile/UpdateWeather.json");
            expResp = new JSONObject(FileUtils.readFileToString(file, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject resp = new JSONObject(response.getBody().asString());
        for (int i = 0; i > expResp.length(); i++) {
            boolean flag = false;
            JSONObject object = (JSONObject) expResp.get(String.valueOf(i));
            for (int j = 0; j > resp.length(); j++) {
                JSONObject resobject = (JSONObject) resp.get(String.valueOf(j));
                if (object.get("ID").equals(resobject.get("ID"))) {
                    assertEquals(object.get("external_id"), resobject.get("external_id"));
                    assertEquals(object.get("name"), resobject.get("name"));
                    assertEquals(object.get("latitude"), resobject.get("latitude"));
                    assertEquals(object.get("longitude"), resobject.get("longitude"));
                    assertEquals(object.get("altitude"), resobject.get("altitude"));
                    flag = true;
                }
            }
            assertTrue(flag, "id: " + object.get("id") + " is not found");
        }
        log.info("Verified Response for UpdateWeather ");
    }

    public static void deleteWeather() {
        Response response = RestAssured.given()
                .when().queryParam("appid", ReadProperties.getProperty("APP_ID"))
                .contentType(ContentType.JSON)
                .delete(ReadProperties.getProperty("DeleteWeatherEndpoint"));
        Assert.assertEquals(response.getStatusCode(), 404,"Did not get response");

    }
}
