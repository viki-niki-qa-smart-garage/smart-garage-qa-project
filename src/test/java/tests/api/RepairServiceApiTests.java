package tests.api;

import com.api.Endpoints;
import com.api.Services;
import core.BaseApiTest;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.TestDataGeneration;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepairServiceApiTests extends BaseApiTest {
    Services services;

    @Test
    void createService() {
        String randomServiceName = RandomStringUtils.randomAlphabetic(15).toLowerCase();
        services = new Services(randomServiceName, 120);

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .body(services)
                        .post(Endpoints.CREATE_SERVICE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        String name = response.jsonPath().getString("name");
        double price = response.jsonPath().getDouble("price");

        Assertions.assertAll("user details changed",
                () -> assertEquals(randomServiceName, name, "First name was not updated"),
                () -> assertEquals(120, price, "Last name was not updated")
        );
    }

    @Test
    void updateService() {
        String randomServiceName = TestDataGeneration.randomServiceName();
        services = new Services(randomServiceName, 120);
        final int id = 34;
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("serviceId", id)
                        .body(services)
                        .put(Endpoints.UPDATE_SERVICE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();
    }

    @Test
    void deleteService() {
        final int id = 34;
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("serviceId", id)
                        .delete(Endpoints.DELETE_SERVICE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();
    }

    @Test
    void filterSortServices() {
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("name", "Oil Change and Inspection")
                        .pathParam("price", 70)
                        .get(Endpoints.GET_FILTER_SORT_SERVICE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();
    }
}