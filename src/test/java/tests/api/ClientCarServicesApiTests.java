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

        List<Integer> usersId = response.jsonPath().getList("id");
        System.out.println(usersId);
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
                        .pathParam("clientCarId", 8)
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
    void createANewClientCar() {

         vehicles = new Vehicles("Audi", "Volkswagen", 2021, "3.0 TDI", "804OP7B1Y6PHQKLRO", "А9122PВ" );

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
