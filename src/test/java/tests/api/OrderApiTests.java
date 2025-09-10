package tests.api;

import com.api.Endpoints;
import core.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class OrderApiTests extends BaseApiTest {

    @BeforeEach
    public void before() {
        RestAssured.authentication = RestAssured.preemptive().basic("user", "Qwertyuiop1!");
    }

    @Test
    void getAllOrders() {

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .get(Endpoints.GET_ALL_ORDERS)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        // Array checks
        java.util.List<?> items = response.jsonPath().getList("$");
        Assertions.assertNotNull(items, "Response is not an array");
        Assertions.assertFalse(items.isEmpty(), "Expected at least one order");


        for (int i = 0; i < items.size(); i++) {
            String p = "[" + i + "]";


            Integer orderId = response.jsonPath().getObject(p + ".id", Integer.class);

            Integer clientCarId = response.jsonPath().getObject(p + ".clientCar.id", Integer.class);
            String vin = response.jsonPath().getString(p + ".clientCar.vin");
            String licensePlate = response.jsonPath().getString(p + ".clientCar.licensePlate");

            Integer vehicleId = response.jsonPath().getObject(p + ".clientCar.vehicle.id", Integer.class);
            Integer brandId = response.jsonPath().getObject(p + ".clientCar.vehicle.brand.id", Integer.class);
            String brandName = response.jsonPath().getString(p + ".clientCar.vehicle.brand.name");
            Integer modelId = response.jsonPath().getObject(p + ".clientCar.vehicle.model.id", Integer.class);
            String modelName = response.jsonPath().getString(p + ".clientCar.vehicle.model.name");
            Integer yearId = response.jsonPath().getObject(p + ".clientCar.vehicle.year.id", Integer.class);
            Integer year = response.jsonPath().getObject(p + ".clientCar.vehicle.year.year", Integer.class);
            Integer engineTypeId = response.jsonPath().getObject(p + ".clientCar.vehicle.engineType.id", Integer.class);
            String engineTypeName = response.jsonPath().getString(p + ".clientCar.vehicle.engineType.name");
            Boolean vehicleDeleted = response.jsonPath().getObject(p + ".clientCar.vehicle.deleted", Boolean.class);
            Boolean clientCarDeleted = response.jsonPath().getObject(p + ".clientCar.deleted", Boolean.class);

            String status = response.jsonPath().getString(p + ".status");
            String orderDate = response.jsonPath().getString(p + ".orderDate");

            int idx = i;

            Assertions.assertAll("order[" + idx + "] presence-only",

                    () -> Assertions.assertNotNull(orderId, p + ".id is null"),
                    () -> Assertions.assertNotNull(clientCarId, p + ".clientCar.id is null"),
                    () -> Assertions.assertNotNull(vehicleId, p + ".clientCar.vehicle.id is null"),
                    () -> Assertions.assertNotNull(brandId, p + ".clientCar.vehicle.brand.id is null"),
                    () -> Assertions.assertNotNull(modelId, p + ".clientCar.vehicle.model.id is null"),
                    () -> Assertions.assertNotNull(yearId, p + ".clientCar.vehicle.year.id is null"),
                    () -> Assertions.assertNotNull(year, p + ".clientCar.vehicle.year.year is null"),
                    () -> Assertions.assertNotNull(engineTypeId, p + ".clientCar.vehicle.engineType.id is null"),
                    () -> Assertions.assertNotNull(vehicleDeleted, p + ".clientCar.vehicle.deleted is null"),
                    () -> Assertions.assertNotNull(clientCarDeleted, p + ".clientCar.deleted is null"),


                    () -> assertPresent(vin, p + ".clientCar.vin"),
                    () -> assertPresent(licensePlate, p + ".clientCar.licensePlate"),
                    () -> assertPresent(brandName, p + ".clientCar.vehicle.brand.name"),
                    () -> assertPresent(modelName, p + ".clientCar.vehicle.model.name"),
                    () -> assertPresent(engineTypeName, p + ".clientCar.vehicle.engineType.name"),
                    () -> assertPresent(status, p + ".status"),
                    () -> assertPresent(orderDate, p + ".orderDate")
            );
        }
    }


    private static void assertPresent(String value, String fieldPath) {
        Assertions.assertNotNull(value, fieldPath + " is null");
        Assertions.assertFalse(value.trim().isEmpty(), fieldPath + " is blank");
    }

    @Test
    void getOrderById() {


    }

}
