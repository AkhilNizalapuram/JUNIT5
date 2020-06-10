package com.nisum.poc.Employee;

import com.nisum.poc.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class EmployeeDetails {
    private static Logger log = LoggerFactory.getLogger(EmployeeDetails.class);
    private static FileWriter file;

    public static Response retriveEmployees() {
        Response response = RestAssured.given()
                .when()
                .get(ReadProperties.getProperty("GETEmployeeEndpoints"));
        Assert.assertEquals(response.getStatusCode(), 200, "Did not get response");
        log.info("Retrived Employees information");
        return response;
    }

    //Assertion method for validating GetEmployee details
    public static void validateEmployees(Response response) {
        JSONArray expResp = new JSONArray();
        try {
            File file = new File("src/test/resources/Jsonfile/employees.json");
            expResp = new JSONArray(FileUtils.readFileToString(file, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject resp = new JSONObject(response.getBody().asString());
        JSONArray jsonArray = resp.getJSONArray("data");
        for (int i = 0; i < expResp.length(); i++) {
            boolean flag = false;
            JSONObject object = (JSONObject) expResp.get(i);
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject resobject = (JSONObject) jsonArray.get(j);
                if (object.get("id").equals(resobject.get("id"))) {
                    assertEquals(object.get("id"), resobject.get("id"));
                    assertEquals(object.get("employee_name"), resobject.get("employee_name"));
                    assertEquals(object.get("employee_salary"), resobject.get("employee_salary"));
                    assertEquals(object.get("employee_age"), resobject.get("employee_age"));
                    flag = true;
                }
            }
            assertTrue(flag, "id: " + object.get("id") + " is not found");
        }
        log.info("Verified Response ");
    }

    public static Response createemployee() throws IOException {
        File file = new File("src/test/resources/Jsonfile/CreateEmployee.json");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(FileUtils.readFileToString(file, "utf-8"))
                .post(ReadProperties.getProperty("PostEmployeeEndpoint"));

        Assert.assertEquals(response.getStatusCode(), 200,"Did not get response");
        return response;
    }

    //Assertion method for validating CreateEmployee details
    public static void validatepostEmployees(Response response) {
        JSONObject expResp = new JSONObject();
        try {
            File file = new File("src/test/resources/Jsonfile/CreateEmployee.json");
            expResp = new JSONObject(FileUtils.readFileToString(file, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject resp = new JSONObject(response.getBody().asString());
        JSONObject obj = resp.getJSONObject("data");
                    assertEquals(expResp.get("name"), obj.get("name"));
                    assertEquals(expResp.get("salary"), obj.get("salary"));
                    assertEquals(expResp.get("age"), obj.get("age"));
        log.info("Verified Response ");
    }

    public static Response updateEmployee(String name,int salary,int age) {
        JSONObject employee = new JSONObject();
        employee.put("name", name);
        employee.put("salary", salary);
        employee.put("age", age);
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(employee.toString())
                .put(ReadProperties.getProperty("UpdateEmployeeEndpoint"));
        try {
            file = new FileWriter("src/test/resources/Jsonfile/UpdateEmployee.json");
            file.write(employee.toString());
        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(response.getStatusCode(), 200,"Did not get response");
        return response;

    }

    //Assertion method for validating CreateEmployee details
    public static void validatePatchEmployees(Response response) {
        JSONObject expResp = new JSONObject();
        try {
            File file = new File("src/test/resources/Jsonfile/UpdateEmployee.json");
            expResp = new JSONObject(FileUtils.readFileToString(file, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject resp = new JSONObject(response.getBody().asString());
        JSONObject obj = resp.getJSONObject("data");
        assertEquals(expResp.get("name"), obj.get("name"));
        assertEquals(expResp.get("salary"), obj.get("salary"));
        assertEquals(expResp.get("age"), obj.get("age"));
        log.info("Verified Response ");
    }

    public static void deleteEmployee(int userId) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .delete(ReadProperties.getProperty("DeleteEmployeeEndpoint")+userId);
        Assert.assertEquals(response.getStatusCode(), 200,"Did not get response");
        log.info("deleted employee records");
    }

}
