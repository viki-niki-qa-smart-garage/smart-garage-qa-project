package tests.api;

import com.api.Endpoints;
import core.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class ClientCarServicesApiTests extends BaseApiTest {

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
}
