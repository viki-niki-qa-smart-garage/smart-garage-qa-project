package tests.api;

import com.api.Endpoints;
import com.api.Vehicles;
import core.BaseApiTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.TestDataGeneration;

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class ClientCarServicesApiTests extends BaseApiTest {
    Vehicles vehicles;

    @Test
    void filterClientCarsByOwner() {
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("name", "er")
                        .get(Endpoints.FILTER_BY_OWNER_ASCENDING)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();
    }

    @Test
    void verifyUserHasTheKeywordInTheUsername() {
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("id", 1)
                        .get(Endpoints.GET_USER_BY_ID)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        String username = response.jsonPath().getString("username");
        Assertions.assertTrue(username.contains("er"));
    }

    @Test
    void addAServiceToAClientCar() {
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("clientCarId", 9)
                        .pathParam("serviceId", 11)
                        .post(Endpoints.ADD_SERVICE)
                        .then()
                        .log().all()
                        .statusCode(SC_CREATED)
                        .extract().response();
        //The Response code in Swagger Docs is 200!!!
        //Can not make assert with GET request, because there is a defect in the response code!
    }

    @Test
    void createNewClientCar() {
          String randomVin = TestDataGeneration.randomVin();
          String randomPlate = TestDataGeneration.randomPlate();
         vehicles = new Vehicles("Audi", "Volkswagen", 2021, "3.0 TDI", randomVin, randomPlate);
        Response response =
                given()
                        .contentType("application/json")
                        .body(vehicles)
                        .when()
                        .pathParam("userId", 10)
                        .post(Endpoints.CREATE_NEW_CLIENT_CAR)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals("Audi", json.getString("vehicle.brand.name"), "Brand does not match");
        Assertions.assertEquals("Volkswagen", json.getString("vehicle.model.name"), "Model does not match");
        Assertions.assertEquals(2021, json.getInt("vehicle.year.year"), "Year does not match");
        Assertions.assertEquals("3.0 TDI", json.getString("vehicle.engineType.name"), "Engine type does not match");
        Assertions.assertEquals(randomVin, json.getString("vin"), "VIN does not match");
        Assertions.assertEquals(randomPlate, json.getString("licensePlate"), "License plate does not match");
    }

    @Test
    void updateClientCarDetails() {
        String randomVin = TestDataGeneration.randomVin();
        String randomPlate = TestDataGeneration.randomPlate();
        vehicles = new Vehicles(randomVin, randomPlate);
        Response response =
                given()
                        .contentType("application/json")
                        .body(vehicles)
                        .when()
                        .pathParam("clientCarId", 5)
                        .put(Endpoints.UPDATE_CAR_DETAILS)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();
    }
}