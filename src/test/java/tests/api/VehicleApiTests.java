package tests.api;

import com.api.Endpoints;
import com.api.Vehicles;
import core.BaseApiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

import static org.apache.http.HttpStatus.SC_OK;


public class VehicleApiTests extends BaseApiTest {


    @Test
    void getAllVehicles() {

        Response response =
                given()
                        .auth().preemptive().basic("user", "Qwertyuiop1!")
                        .contentType("application/json")
                        .when()
                        .get(Endpoints.GET_ALL_VEHICLES)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();


        java.util.List<?> items = response.jsonPath().getList("$");
        Assertions.assertNotNull(items, "Response is not an array");
        Assertions.assertFalse(items.isEmpty(), "Expected at least one vehicle");


        int firstId = response.jsonPath().getInt("[0].id");
        String firstBrand = response.jsonPath().getString("[0].brand.name");
        String firstModel = response.jsonPath().getString("[0].model.name");
        int firstYear = response.jsonPath().getInt("[0].year.year");
        String firstEngine = response.jsonPath().getString("[0].engineType.name");
        boolean firstDeleted = response.jsonPath().getBoolean("[0].deleted");

        Assertions.assertAll("first vehicle details",
                () -> Assertions.assertTrue(firstId == 1, "id must be 1"),
                () -> Assertions.assertEquals("Audi", firstBrand, "Incorrect brand name"),
                () -> Assertions.assertEquals("A4", firstModel, "Incorrect model name"),
                () -> Assertions.assertEquals(2010, firstYear, "Incorrect year"),
                () -> Assertions.assertEquals("2.0 TDI", firstEngine, "Incorrect engineType name"),
                () -> Assertions.assertFalse(firstDeleted, "deleted should be false")
        );

    }

    @Test
    void getConcreteVehicle() {

        final int vehicleId = 34;


        Response response =
                given()
                        .auth().preemptive().basic("user", "Qwertyuiop1!")
                        .contentType("application/json")
                        .pathParam("vehicleId", vehicleId)
                        .when()
                        .get(Endpoints.GET_VEHICLE_BY_ID)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        Assertions.assertEquals(vehicleId, response.jsonPath().getInt("id"), "Incorrect id");


    }

    @Test
    void updateVehicle() {

        Vehicles vehicles = new Vehicles("Porsche", "GT3", 2010, "2.0 Boxer");

        final int vehicleId = 34;

        Response response =
                given()
                        .auth().preemptive().basic("user", "Qwertyuiop1!")
                        .contentType("application/json")
                        .pathParam("vehicleId", vehicleId)
                        .body(vehicles)
                        .when()
                        .put(Endpoints.UPDATE_VEHICLE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

    }




    @Test
    void createVehicle() {
        Vehicles vehicles = new Vehicles("Volkswagen", "Polo", 2008, "1.4 TSI");

        Response response =
                given()
                        .auth().preemptive().basic("user", "Qwertyuiop1!")
                        .contentType("application/json")
                        .body(vehicles)
                        .when()
                        .post(Endpoints.CREATE_VEHICLE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();


        String brandName = response.jsonPath().getString("brand.name");
        String modelName = response.jsonPath().getString("model.name");
        Integer year = response.jsonPath().getInt("year.year");
        String engineType = response.jsonPath().getString("engineType.name");
        Integer id = response.jsonPath().getInt("id");
        boolean deleted = response.jsonPath().getBoolean("deleted");


        Assertions.assertEquals("Volkswagen", brandName, "Incorrect brandName");
        Assertions.assertEquals("Polo", modelName, "Incorrect modelName");
        Assertions.assertEquals(2008, year, "Incorrect year");
        Assertions.assertEquals("1.4 TSI", engineType, "Incorrect engine type");
        Assertions.assertTrue(id != null && id > 0, "Generated id should be > 0");
        Assertions.assertFalse(deleted, "New vehicle should not be deleted");


    }

    @Test
    void getCreatedVehicle() {

        final int vehicleId = 50;

        Response response =
                given()
                        .auth().preemptive().basic("user", "Qwertyuiop1!")
                        .contentType("application/json")
                        .pathParam("vehicleId", vehicleId)
                        .when()
                        .get(Endpoints.GET_CREATED_VEHICLE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        Assertions.assertEquals(vehicleId, response.jsonPath().getInt("id"), "Incorrect id");

    }



    @Test
    void deleteVehicle() {

        final int vehicleId = 49;

        Response response =
                given()
                        .auth().preemptive().basic("user", "Qwertyuiop1!")
                        .contentType("application/json")
                        .pathParam("vehicleId", vehicleId)
                        .when()
                        .delete(Endpoints.DELETE_VEHICLE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

    }



}


