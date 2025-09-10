package tests.api;

import com.api.Endpoints;
import com.api.Vehicles;
import core.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class ClientCarServicesApiTests extends BaseApiTest {

    Vehicles vehicles;

    @BeforeEach
    void setUp() {
        RestAssured.authentication = RestAssured.preemptive().basic("test", "Testing1@");
    }


    @Test
    void getClientCarServices() {

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .get(Endpoints.GET_CLIENT_CARS_SERVICES)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();
    }


    @Test
    void getConcreteClientCarServiceHistory() {

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("id", 30)
                        .get(Endpoints.GET_CLIENT_CARS_SERVICE_HISTORY)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        List<?> serviceHistory = response.jsonPath().getList("$");

        Assertions.assertFalse(serviceHistory.isEmpty(), "Expected the services array to have elements");
    }

    @Test
    void getConcreteCarService() {

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("clientCarId", 9)
                        .get(Endpoints.GET_CONCRETE_CAR_SERVICE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        List<?> service = response.jsonPath().getList("$");

        Assertions.assertFalse(service.isEmpty(), "Expected the services array to have elements");

    }


    @Test
    void filterClientCarsByOwner() {

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("name", "T")
                        .get(Endpoints.FILTER_BY_OWNER_ASCENDING)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

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

    }

    @Test
    void getAddedServiceToAClient() {

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("id", 9)
                        .get(Endpoints.GET_CLIENT_CARS_SERVICE_HISTORY)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

    }

    @Test
    void createANewClientCar() {

         vehicles = new Vehicles("Audi", "Volkswagen", 2021, "3.0 TDI", "0UK98WBA0K7VCNL6E", "A5912PP" );

        Response response =
                given()
                        .contentType("application/json")
                        .body(vehicles)
                        .when()
                        .pathParam("userId", 24)
                        .post(Endpoints.CREATE_NEW_CLIENT_CAR)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

    }
}
