package tests.api;

import com.api.Endpoints;
import com.api.Vehicles;
import core.BaseApiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import java.util.List;
import static org.apache.http.HttpStatus.SC_OK;

public class VehicleApiTests extends BaseApiTest {
    private static Integer vehicleId;

    @Test
    void getAllVehicles() {

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .get(Endpoints.GET_ALL_VEHICLES)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        List<?> items = response.jsonPath().getList("$");
        Assertions.assertNotNull(items, "Response is not an array");
        Assertions.assertFalse(items.isEmpty(), "Expected at least one vehicle");

        int firstVehicleId = response.jsonPath().getInt("[0].id");
        String brandName = response.jsonPath().getString("[0].brand.name");
        String modelName = response.jsonPath().getString("[0].model.name");
        int yearValue = response.jsonPath().getInt("[0].year.year");
        String engineTypeName = response.jsonPath().getString("[0].engineType.name");
        boolean deleted = response.jsonPath().getBoolean("[0].deleted");

        Assertions.assertAll("first vehicle details",
                () -> Assertions.assertNotNull(firstVehicleId, "Vehicle id must not be null"),
                () -> Assertions.assertNotNull(brandName, "Brand name must not be null"),
                () -> Assertions.assertNotNull(modelName, "Model name must not be null"),
                () -> Assertions.assertNotNull(yearValue, "Year must not be null"),
                () -> Assertions.assertNotNull(engineTypeName, "Engine type must not be null"),
                () -> Assertions.assertNotNull(deleted, "Deleted flag must not be null")
        );
    }

    @Test
    void getConcreteVehicle() {
        final int vehicleId = 34;

        Response response =
                given()
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
    void createVehicle() {
        Vehicles vehicles = new Vehicles("Volkswagen", "Polo", 2008, "1.4 TSI");

        Response response =
                given()
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
        vehicleId = response.jsonPath().getInt("id");
        boolean deleted = response.jsonPath().getBoolean("deleted");


        Assertions.assertEquals("Volkswagen", brandName, "Incorrect brandName");
        Assertions.assertEquals("Polo", modelName, "Incorrect modelName");
        Assertions.assertEquals(2008, year, "Incorrect year");
        Assertions.assertEquals("1.4 TSI", engineType, "Incorrect engine type");
        Assertions.assertTrue(vehicleId != null && vehicleId > 0, "Generated id should be > 0");
        Assertions.assertFalse(deleted, "New vehicle should not be deleted");


    }

    @Test
    void getCreatedVehicle() {

//        final int vehicleId = 50;
        Response response =
                given()
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
    void updateVehicle() {
        Vehicles vehicles = new Vehicles("Porsche", "GT3", 2010, "2.0 Boxer");
        final int id = 38;

        Response response =
                given()
                        .contentType("application/json")
                        .pathParam("vehicleId", id)
                        .body(vehicles)
                        .when()
                        .put(Endpoints.UPDATE_VEHICLE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

    }

    @Test
    void deleteVehicle() {
        final int id = 40;

        Response response =
                given()
                        .contentType("application/json")
                        .pathParam("vehicleId", id)
                        .when()
                        .delete(Endpoints.DELETE_VEHICLE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

    }
}


