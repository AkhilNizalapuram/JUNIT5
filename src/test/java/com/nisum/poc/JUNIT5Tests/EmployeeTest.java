package com.nisum.poc.JUNIT5Tests;


import com.nisum.poc.Employee.EmployeeDetails;
import com.nisum.poc.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class EmployeeTest {

    @DisplayName("Initialises base URI ")
    @BeforeEach
    public void setUp(){
        RestAssured.baseURI = ReadProperties.getProperty("EmployeeURI");
    }

    @DisplayName("Validates all GET employee details")
    @Test
    public void validateGetEmployees() {
        Response response = EmployeeDetails.retriveEmployees();
        EmployeeDetails.validateEmployees(response);
    }

    @DisplayName("Validates all POST employee details")
    @Test
    public void validatecreateEmployee() throws IOException {
        Response response= EmployeeDetails.createemployee();
        EmployeeDetails.validatepostEmployees(response);
    }

    @Disabled("Do not run UPDATE employee details")
    @Test
    public void validateupdateEmployee(String name,int salary, int age){
        Response response= EmployeeDetails.updateEmployee(name, salary,age);
        EmployeeDetails.validatePatchEmployees(response);
    }

    @DisplayName("Validates all DELETE employee details")
    @Test
    public void validateDeleteEmployee() {
        EmployeeDetails.deleteEmployee(4);

    }

}
